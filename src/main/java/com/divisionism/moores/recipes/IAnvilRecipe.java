package com.divisionism.moores.recipes;

import javax.annotation.Nonnull;

import com.divisionism.moores.OreAddons;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IAnvilRecipe extends Recipe<RecipeWrapper>{

	ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(OreAddons.MOD_ID, "anvil");
	
	@Nonnull
	@Override
	default RecipeType<?> getType() {
		return Registry.RECIPE_TYPE.get(RECIPE_TYPE_ID);
	}
	
	Ingredient getInput();
}
