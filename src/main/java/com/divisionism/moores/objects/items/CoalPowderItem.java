package com.divisionism.moores.objects.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class CoalPowderItem extends Item {

	public CoalPowderItem(Properties properties) {
		super(properties);
	}

	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return 200;
	}
}
