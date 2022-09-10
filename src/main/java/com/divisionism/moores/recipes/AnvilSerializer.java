package com.divisionism.moores.recipes;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class AnvilSerializer implements RecipeSerializer<AnvilRecipe> {

	private Map<String, Integer> inputMap = new HashMap<String, Integer>();
	private Map<String, Integer> extraMap = new HashMap<String, Integer>();;
	
	@Override
	public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
		return new AnvilSerializer();
	}

	@Override
	public ResourceLocation getRegistryName() {
		return AnvilRecipe.RECIPE_TYPE_ID;
	}

	@Override
	public Class<RecipeSerializer<?>> getRegistryType() {
		return AnvilSerializer.castClass(RecipeSerializer.class);
	}

	@SuppressWarnings("unchecked")
	private static <G> Class<G> castClass(Class<?> cls) {
		return (Class<G>) cls;
	}

	@Override
	public AnvilRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
		
		ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
		ItemStack hammer = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "hammer"));
		ItemStack input;
		ItemStack extra;
		
		JsonObject inputJson = GsonHelper.getAsJsonObject(pSerializedRecipe, "input");
		input = ShapedRecipe.itemStackFromJson(inputJson);
		
		if (inputJson.has("count"))
			this.inputMap.put(input.getItem().toString(), inputJson.get("count").getAsInt());
		
		try {
			JsonObject extraJson = GsonHelper.getAsJsonObject(pSerializedRecipe, "extra");
			extra = ShapedRecipe.itemStackFromJson(extraJson);
			
			if (extraJson.has("count"))
				this.extraMap.put(extra.getItem().toString(), extraJson.get("count").getAsInt());
			
		} catch (JsonSyntaxException e) {
			//e.printStackTrace();
			extra = ItemStack.EMPTY;
		}

		return new AnvilRecipe(pRecipeId, Ingredient.of(input), Ingredient.of(hammer), Ingredient.of(extra), output);
	}

	@Override
	public AnvilRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
		Ingredient input = Ingredient.fromNetwork(pBuffer);
		Ingredient hammer = Ingredient.fromNetwork(pBuffer);
		Ingredient extra = Ingredient.fromNetwork(pBuffer);

		ItemStack output = pBuffer.readItem();

		return new AnvilRecipe(pRecipeId, input, hammer, extra, output);
	}

	@Override
	public void toNetwork(FriendlyByteBuf pBuffer, AnvilRecipe pRecipe) {
		pBuffer.writeInt(pRecipe.getIngredients().size());

		Ingredient input = pRecipe.getInput();
		Ingredient hammer = pRecipe.getHammer();
		Ingredient extra = pRecipe.getExtra();

		input.toNetwork(pBuffer);
		hammer.toNetwork(pBuffer);
		extra.toNetwork(pBuffer);

		pBuffer.writeItemStack(pRecipe.getResultItem(), false);
	}
	
	public Map<String, Integer> getExtraMap() {
		return this.extraMap;
	}
	
	public Map<String, Integer> getInputMap() {
		return this.inputMap;
	}
}
