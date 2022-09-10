package com.divisionism.moores.utils;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HotItem extends Item {

	protected int burningDuration;
	
	public HotItem(Properties pProperties, int burningDuration) {
		super(pProperties.rarity(Rarity.UNCOMMON));
		this.burningDuration = burningDuration;
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> tooltip,
			TooltipFlag pIsAdvanced) {
		tooltip.add(new TranslatableComponent("tooltip.moores.hot_item"));
	}
	
	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
		pEntity.setSecondsOnFire(this.burningDuration);
	}
}
