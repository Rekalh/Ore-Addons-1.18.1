package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.objects.blocks.AetherCrystalGeneratorBlock;
import com.divisionism.moores.objects.blocks.BlazingBlackstone;
import com.divisionism.moores.objects.blocks.CrystalMef;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			OreAddons.MOD_ID);

	// Register blocks
	public static final RegistryObject<Block> CRYSTAL_MEF = BLOCKS.register("crystal_mef",
			() -> new CrystalMef(Block.Properties.of(Material.STONE).strength(100f, 100f).requiresCorrectToolForDrops()
					.jumpFactor(1f).friction(0.5f).dynamicShape()));
	public static final RegistryObject<Block> YES_BLOCK = BLOCKS.register("yes_block",
			() -> new Block(Block.Properties.of(Material.STONE).strength(100f, 100f).requiresCorrectToolForDrops()
					.jumpFactor(1.5f).friction(0.5f).dynamicShape()));

	public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore",
			() -> new Block(Block.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore",
			() -> new Block(Block.Properties.copy(Blocks.GOLD_ORE)));
	public static final RegistryObject<Block> BLAZING_BLACKSTONE = BLOCKS.register("blazing_blackstone",
			() -> new BlazingBlackstone(Block.Properties.of(Material.STONE).strength(20f).lightLevel((state) -> {
				return 10;
			})));
	public static final RegistryObject<Block> YTTRIUM_ORE = BLOCKS.register("yttrium_ore",
			() -> new Block(Block.Properties.copy(Blocks.NETHER_QUARTZ_ORE)));
	public static final RegistryObject<Block> AETHER_CRYSTAL_BLOCK = BLOCKS.register("aether_block",
			() -> new Block(Block.Properties.of(Material.STONE).sound(SoundType.NETHERRACK)
					.requiresCorrectToolForDrops().strength(50, 50)));
	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
			() -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block",
			() -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block",
			() -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> BRONZE_BLOCK = BLOCKS.register("bronze_block",
			() -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> AETHER_CRYSTAL_GENERATOR = BLOCKS.register("aether_crystal_generator",
			() -> new AetherCrystalGeneratorBlock(Block.Properties.copy(Blocks.STONE)));
}
