package com.divisionism.moores.objects.items;

import java.util.List;

import com.divisionism.moores.init.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MagmatiteLeggingsItem extends ArmorItem {

	private int initialDamage;
	
	public MagmatiteLeggingsItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.moores.magmatite_armor_1"));
		tooltip.add(new TranslatableComponent("tooltip.moores.magmatite_armor_2"));
	}
	
	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
		this.initialDamage = this.getDamage(stack);
	}
	
	/*
	 * <Shitty code ahead. Proceed with caution!>
	 */
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof Player))
			return;
		Player player = (Player) entityIn;
		if (this.getDamage(stack) == this.getMaxDamage(stack) - 1) {
			ItemStack leggings = new ItemStack(Items.NETHERITE_LEGGINGS);
			leggings.setDamageValue(initialDamage);
			player.getInventory().setItem(37, leggings);
			worldIn.playSound(player, player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0f,
					1.0f);
			player.getInventory().add(new ItemStack(ModItems.DEPLETED_MAGMATITE.get()));
		}
	}
}
