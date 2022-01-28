package com.divisionism.moores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.divisionism.moores.creativetabs.ModCreativeTabs;
import com.divisionism.moores.init.ModAttributes;
import com.divisionism.moores.init.ModBlocks;
import com.divisionism.moores.init.ModItems;
import com.divisionism.moores.init.ModTileEntities;
import com.divisionism.moores.world.gen.ModOreGeneration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/*
 * TODO: Make an aether crystal generator
 * TODO: Make it generate only on mountains
 */

@Mod("moores")
public class OreAddons {
	
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "moores";

    public OreAddons() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTileEntities.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModAttributes.ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
        
        new ModCreativeTabs();
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	event.enqueueWork(() -> {
    		ModOreGeneration.registerConfigured();
    		ModOreGeneration.registerPlaced();
    	});
    }
}
