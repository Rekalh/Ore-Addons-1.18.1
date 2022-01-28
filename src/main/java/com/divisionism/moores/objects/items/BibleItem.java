package com.divisionism.moores.objects.items;

import java.util.List;

import com.divisionism.moores.init.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BibleItem extends SwordItem {

	public BibleItem(Tier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
		super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip,
			TooltipFlag flag) {
		tooltip.add(new TranslatableComponent("tooltip.moores.the_bible"));
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		if (!(entity instanceof LivingEntity)) return false;
		
		if (!player.level.isClientSide) {
			LivingEntity livingEntity = (LivingEntity)entity;
			livingEntity.setHealth(0);
		}
		
		return true;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		int duration = 120;
		
		if (!world.isClientSide) {
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration * 20, 255, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration * 20, 1, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, duration * 20, 1, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.SATURATION, duration * 20, 255, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration * 20, 1, true, false));
			
			stack.hurtAndBreak(1, player, (plr) -> {
				plr.getInventory().removeItem(stack);
			});
			
			if (!player.hasItemInSlot(EquipmentSlot.FEET))
				player.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.AETHER_BOOTS.get()));
			else {
				player.addItem(player.getItemBySlot(EquipmentSlot.FEET));
				player.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.AETHER_BOOTS.get()));
			}
		}
		
		return InteractionResultHolder.success(stack);
	}
}
