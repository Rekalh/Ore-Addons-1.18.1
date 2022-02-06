package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.tileentities.AetherCrystalGeneratorBE;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITIES, OreAddons.MOD_ID);

	public static final RegistryObject<BlockEntityType<AetherCrystalGeneratorBE>> AETHER_CRYSTAL_GENERATOR_BE = TILE_ENTITIES
			.register("aether_crystal_generator_be", () -> BlockEntityType.Builder
					.of(AetherCrystalGeneratorBE::new, ModBlocks.AETHER_CRYSTAL_GENERATOR.get()).build(null));
}
