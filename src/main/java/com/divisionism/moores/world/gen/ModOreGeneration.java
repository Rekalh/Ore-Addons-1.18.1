package com.divisionism.moores.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.init.ModBlocks;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class ModOreGeneration {

	public static final ArrayList<PlacedFeature> OVERWORLD_ORES = new ArrayList<>();
	public static final ArrayList<PlacedFeature> NETHER_ORES = new ArrayList<>();

	public static final RuleTest STONE = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
	public static final RuleTest NETHERRACK = new BlockMatchTest(Blocks.NETHERRACK);
	public static final RuleTest BLACKSTONE = new BlockMatchTest(Blocks.BLACKSTONE);

	// Add ores here
	public static final ConfiguredFeature<?, ?> COPPER_ORE_CF = addOreConf(STONE, ModBlocks.COPPER_ORE.get().defaultBlockState(), 10, 0.0f);
	public static final PlacedFeature COPPER_ORE_PF = addOrePlaced(COPPER_ORE_CF, 3, 10, 30);
	
	public static final ConfiguredFeature<?, ?> TIN_ORE_CF = addOreConf(STONE, ModBlocks.TIN_ORE.get().defaultBlockState(), 6, 0.0f);
	public static final PlacedFeature TIN_ORE_PF = addOrePlaced(TIN_ORE_CF, 4, 5, 50);
	
	public static final ConfiguredFeature<?, ?> YTTRIUM_ORE_CF = addOreConf(NETHERRACK, ModBlocks.YTTRIUM_ORE.get().defaultBlockState(), 5, 0.0f);
	public static final PlacedFeature YTTRIUM_ORE_PF = addOrePlaced(YTTRIUM_ORE_CF, 6, 35, 100);
	
	public static final ConfiguredFeature<?, ?> BLAZING_BLACKSTONE_CF = addOreConf(BLACKSTONE, ModBlocks.BLAZING_BLACKSTONE.get().defaultBlockState(), 15, 0.0f);
	public static final PlacedFeature BLAZING_BLACKSTONE_PF = addOrePlaced(BLAZING_BLACKSTONE_CF, 20, 0, 100);
	
	private static ConfiguredFeature<?, ?> addOreConf(RuleTest placement, BlockState ore, int veinSize, float exposure) {
		return Feature.ORE.configured(new OreConfiguration(placement, ore, veinSize, exposure));
	}

	private static PlacedFeature addOrePlaced(ConfiguredFeature<?, ?> oreFeature, int spawnRate, int minHeight, int maxHeight) {
		return oreFeature.placed(CountPlacement.of(spawnRate), InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
				BiomeFilter.biome());
	}

	@Mod.EventBusSubscriber(modid = OreAddons.MOD_ID, bus = Bus.FORGE)
	public static class ForgeBusSubscriber {
		
		@SubscribeEvent
		public static void onBiomeLoad(BiomeLoadingEvent event) {
			final List<Supplier<PlacedFeature>> features = event.getGeneration().getFeatures(Decoration.UNDERGROUND_ORES);
			
			switch(event.getCategory()) {
			
				case NETHER: ModOreGeneration.NETHER_ORES.forEach(ore -> features.add(() -> ore));
				default: ModOreGeneration.OVERWORLD_ORES.forEach(ore -> features.add(() -> ore));
			}
		}
	}
	
	public static void registerPlaced() {
		Registry<PlacedFeature> registry = BuiltinRegistries.PLACED_FEATURE;
		
		// Here
		OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID, "copper_ore"), COPPER_ORE_PF));
		OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID, "tin_ore"), TIN_ORE_PF));
		NETHER_ORES.add(Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID, "yttrium_ore"), YTTRIUM_ORE_PF));
		NETHER_ORES.add(Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID, "blazing_blackstone"), BLAZING_BLACKSTONE_PF));
	}
	
	public static void registerConfigured() {
		Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
		
		// And here
		Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID + "copper_ore"), COPPER_ORE_CF);
		Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID + "tin_ore"), TIN_ORE_CF);
		Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID + "yttium_ore"), YTTRIUM_ORE_CF);
		Registry.register(registry, new ResourceLocation(OreAddons.MOD_ID + "blazing_blackstone"), BLAZING_BLACKSTONE_CF);
	}
}
