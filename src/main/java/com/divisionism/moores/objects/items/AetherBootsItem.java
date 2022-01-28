package com.divisionism.moores.objects.items;

import java.util.List;
import java.util.UUID;

import com.divisionism.moores.init.ModAttributes;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AetherBootsItem extends ArmorItem {

	public AetherBootsItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.moores.aether_boots"));
	}

	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {
		player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, true, false));
		player.addEffect(new MobEffectInstance(MobEffects.JUMP, 1, 0, true, false));
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		Builder<Attribute, AttributeModifier> attributes = ImmutableMultimap.builder();

		attributes.put(ModAttributes.FLOAT.get(), new AttributeModifier(
				UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Floats on water", 1, Operation.ADDITION));
		attributes.put(ModAttributes.JUMP_BOOST.get(), new AttributeModifier(
				UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Jump Boost", 1, Operation.ADDITION));
		attributes.put(ModAttributes.SLOW_FALLING.get(), new AttributeModifier(
				UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), "Slow Falling", 1, Operation.ADDITION));
		
		return this.slot == equipmentSlot ? attributes.build() : super.getDefaultAttributeModifiers(equipmentSlot);
	}
}
