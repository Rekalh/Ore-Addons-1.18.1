package com.divisionism.moores.objects.items;

import java.util.ArrayList;
import java.util.List;

import com.divisionism.moores.init.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MagmatiteChestplateItem extends ArmorItem {

	private int initialDamage;
	
	public MagmatiteChestplateItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builderIn) {
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
			ItemStack chestplate = new ItemStack(ModItems.STEEL_CHESTPLATE.get());
			chestplate.setDamageValue(initialDamage);
			player.getInventory().setItem(38, chestplate);
			worldIn.playSound(player, player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0f,
					1.0f);
			//player.inventory.add(0, new ItemStack(ModItems.DEPLETED_MAGMATITE.get()));
			player.getInventory().add(new ItemStack(ModItems.DEPLETED_MAGMATITE.get()));
		}
		if (this.getArmorWearing(player.getInventory(), itemSlot).contains(ModItems.MAGMATITE_CHESTPLATE.get())
				&& this.getArmorWearing(player.getInventory(), itemSlot).contains(ModItems.MAGMATITE_HELMET.get())
				&& this.getArmorWearing(player.getInventory(), itemSlot).contains(ModItems.MAGMATITE_LEGGINGS.get())
				&& this.getArmorWearing(player.getInventory(), itemSlot).contains(ModItems.MAGMATITE_BOOTS.get())) {
			player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, true, false));
		}
	}

	private ArrayList<Item> getArmorWearing(Inventory inv, int armorSlot) {
		ArrayList<Item> armor = new ArrayList<>();
		for (int i = -2; i < 2; i++) {
			try {
				armor.add(inv.getArmor(armorSlot + i).getItem());
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return armor;
	}
}
