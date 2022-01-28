package com.divisionism.moores.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class ModDamageSource extends DamageSource {

	public ModDamageSource(String damageTypeIn) {
		super(damageTypeIn);
	}
	
	@Override
	public Component getLocalizedDeathMessage(LivingEntity p_151519_1_) {
		return new TextComponent("death_message.moores.baba_booey");
	}
}
