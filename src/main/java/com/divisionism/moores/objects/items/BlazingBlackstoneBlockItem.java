package com.divisionism.moores.objects.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BlazingBlackstoneBlockItem extends BlockItem {

	public BlazingBlackstoneBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof Player))
			return;
		Player player = (Player)entityIn;
		if (!player.isCreative()) player.setSecondsOnFire(1);
	}
}
