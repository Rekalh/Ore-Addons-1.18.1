package com.divisionism.moores.events;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.client.screen.AnvilScreen;
import com.divisionism.moores.init.ModBlockEntities;
import com.divisionism.moores.init.ModContainers;
import com.divisionism.moores.init.ModEntityTypes;
import com.divisionism.moores.render.block.AetherCrystalGeneratorBER;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OreAddons.MOD_ID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModClientEventSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(ModContainers.ANVIL_CONTAINER.get(), AnvilScreen::new);
	}
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(ModBlockEntities.AETHER_CRYSTAL_GENERATOR_BE.get(), AetherCrystalGeneratorBER::new);
		event.registerEntityRenderer(ModEntityTypes.BURNING_ITEM_ENTITY.get(), ItemEntityRenderer::new);
	}
}
