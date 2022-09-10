package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.recipes.AnvilRecipe;
import com.divisionism.moores.recipes.AnvilSerializer;
import com.divisionism.moores.recipes.IAnvilRecipe;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {

	public static final RecipeSerializer<AnvilRecipe> ANVIL_RECIPE_SERIALIZER = new AnvilSerializer();
	public static final RecipeType<AnvilRecipe> ANVIL_TYPE = registerType(IAnvilRecipe.RECIPE_TYPE_ID);
	
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, OreAddons.MOD_ID);

	public static final RegistryObject<RecipeSerializer<AnvilRecipe>> ANVIL_SERIALIZER = RECIPE_SERIALIZERS.register("anvil", () -> ANVIL_RECIPE_SERIALIZER);

	private static RecipeType<AnvilRecipe> registerType(ResourceLocation recipeTypeId) {
		return Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<AnvilRecipe>() {
			@Override
			public String toString() {
				return Registry.RECIPE_TYPE.getKey(this).toString();
			}
		});
	}
}
