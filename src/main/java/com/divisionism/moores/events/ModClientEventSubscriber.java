package com.divisionism.moores.events;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.init.ModBlockEntities;
import com.divisionism.moores.render.block.AetherCrystalGeneratorBER;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OreAddons.MOD_ID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModClientEventSubscriber {
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(ModBlockEntities.AETHER_CRYSTAL_GENERATOR_BE.get(), AetherCrystalGeneratorBER::new);
	}
}
