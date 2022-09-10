package com.divisionism.moores.recipes;

import java.util.HashMap;
import java.util.Map;

import com.divisionism.moores.init.ModRecipeSerializers;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AnvilRecipe implements IAnvilRecipe {

	private ResourceLocation id;
	private Ingredient input;
	private Ingredient hammer;
	private Ingredient extra;
	private ItemStack output;

	public AnvilRecipe(ResourceLocation id, Ingredient input, Ingredient hammer, Ingredient extra, ItemStack output) {
		this.id = id;
		this.input = input;
		this.hammer = hammer;
		this.extra = extra;
		this.output = output;
	}

	@Override
	public boolean matches(RecipeWrapper pContainer, Level pLevel) {
		return this.input.test(pContainer.getItem(0)) && this.hammer.test(pContainer.getItem(2)) && this.extra.test(pContainer.getItem(1));
	}
	
	public Map<String, Integer> getExtraCount() {
		if (this.getSerializer() instanceof AnvilSerializer serializer)
			return serializer.getExtraMap();
		return new HashMap<>();
	}
	
	public Map<String, Integer> getInputCount() {
		if (this.getSerializer() instanceof AnvilSerializer serializer)
			return serializer.getInputMap();
		return new HashMap<>();
	}
	
	@Override
	public ItemStack assemble(RecipeWrapper pContainer) {
		return this.output;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return this.output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.add(this.input);
		return ingredients;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.ANVIL_SERIALIZER.get();
	}

	@Override
	public Ingredient getInput() {
		return this.input;
	}

	public Ingredient getHammer() {
		return this.hammer;
	}

	public Ingredient getExtra() {
		return this.extra;
	}
}
