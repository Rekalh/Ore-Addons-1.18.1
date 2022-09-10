package com.divisionism.moores.utils;

import java.util.List;

import com.divisionism.moores.entities.BurningItemEntity;
import com.divisionism.moores.init.ModEntityTypes;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BurningItem extends HotItem {

	protected Item cooledOffItem;
	protected final int maxTemperature;

	public BurningItem(Properties pProperties, Item cooledOffItem, int burningDuration, int maxTemperature) {
		super(pProperties, burningDuration);

		this.cooledOffItem = cooledOffItem;
		this.maxTemperature = maxTemperature;
	}

	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
		tooltip.add(new TranslatableComponent("tooltip.moores.burning_item"));
		tooltip.add(new TextComponent("\u00A77Temperature: " + (pStack.getOrCreateTag().contains("temperature")
				? pStack.getOrCreateTag().getInt("temperature")
				: this.maxTemperature)));
	}

	@Override
	public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
		pStack.getOrCreateTag().putInt("temperature", this.maxTemperature);
	}

	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
		super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

		if (!pStack.getOrCreateTag().contains("temperature"))
			pStack.getOrCreateTag().putInt("temperature", this.maxTemperature);

		if (pStack.getOrCreateTag().getInt("temperature") > 0)
			pStack.getOrCreateTag().putInt("temperature", pStack.getOrCreateTag().getInt("temperature") - 1);
		else {
			if (pEntity instanceof Player player) {
				player.getInventory().removeItem(pStack);
				ItemStack stack = new ItemStack(this.cooledOffItem);
				stack.setCount(pStack.getCount());
				player.getInventory().add(pSlotId, stack);
			}
		}
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(Level world, Entity location, ItemStack itemstack) {
		BurningItemEntity itemEntity = new BurningItemEntity(ModEntityTypes.BURNING_ITEM_ENTITY.get(), world,
				location.getX(), location.getY(), location.getZ(), itemstack);
		itemEntity.setPickUpDelay(40);
		itemEntity.setDeltaMovement(location.getDeltaMovement());

		return itemEntity;
	}

	public Item getCooledOffItem() {
		return this.cooledOffItem;
	}
}
