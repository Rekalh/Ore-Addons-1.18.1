package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.tileentities.BlazingBlackstoneTE;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModTileEntities {

	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITIES, OreAddons.MOD_ID);

	public static final RegistryObject<BlockEntityType<BlazingBlackstoneTE>> BLAZING_BLACKSTONE = TILE_ENTITIES.register(
			"blazing_blackstone_tile",
			() -> BlockEntityType.Builder.of(BlazingBlackstoneTE::new, ModBlocks.BLAZING_BLACKSTONE.get()).build(null));

}
