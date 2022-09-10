package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.creativetabs.ModCreativeTabs;
import com.divisionism.moores.enums.itemtiers.ModItemTiers;
import com.divisionism.moores.enums.materials.ModArmorMaterials;
import com.divisionism.moores.objects.items.AetherBootsItem;
import com.divisionism.moores.objects.items.BibleItem;
import com.divisionism.moores.objects.items.BlazingBlackstoneBlockItem;
import com.divisionism.moores.objects.items.CoalPowderItem;
import com.divisionism.moores.objects.items.MagmatiteBootsItem;
import com.divisionism.moores.objects.items.MagmatiteChestplateItem;
import com.divisionism.moores.objects.items.MagmatiteHelmetItem;
import com.divisionism.moores.objects.items.MagmatiteLeggingsItem;
import com.divisionism.moores.objects.items.YttriumPickaxe;
import com.divisionism.moores.utils.BurningItem;
import com.divisionism.moores.utils.HammerItem;
import com.divisionism.moores.utils.HotItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OreAddons.MOD_ID);

	// Register items
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> MAGMATITE = ITEMS.register("magmatite",
			() -> new HotItem(new Item.Properties().tab(ModCreativeTabs.INGOTS).fireResistant(), 9));
	public static final RegistryObject<Item> AETHER_CRYSTAL = ITEMS.register("aether_crystal",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> IRON_POWDER = ITEMS.register("iron_powder",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_powder",
			() -> new CoalPowderItem(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> TIN_POWDER = ITEMS.register("tin_powder",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> COPPER_POWDER = ITEMS.register("copper_powder",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> BRONZE_POWDER = ITEMS.register("bronze_powder",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> STEEL_POWDER = ITEMS.register("steel_powder",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> MAGMATITE_FRAGMENT = ITEMS.register("magmatite_fragment",
			() -> new HotItem(new Item.Properties().tab(ModCreativeTabs.INGOTS).fireResistant(), 1));
	public static final RegistryObject<Item> DEPLETED_MAGMATITE = ITEMS.register("depleted_magmatite",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS).fireResistant()));
	public static final RegistryObject<Item> AETHER_SHARD = ITEMS.register("aether_shard",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> YTTRIUM_INGOT = ITEMS.register("yttrium_ingot",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> HOT_YTTRIUM_INGOT = ITEMS.register("hot_yttrium_ingot",
			() -> new BurningItem(new Item.Properties(), YTTRIUM_INGOT.get(), 120, 100));
	public static final RegistryObject<Item> YTTRIUM_SHEET = ITEMS.register("yttrium_sheet",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> HOT_YTTRIUM_SHEET = ITEMS.register("hot_yttrium_sheet",
			() -> new BurningItem(new Item.Properties(), YTTRIUM_SHEET.get(), 60, 25));
	public static final RegistryObject<Item> YTTRIUM_CHUNK = ITEMS.register("yttrium_chunk",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> OXIDIZED_YTTRIUM_FRAGMENT = ITEMS.register("oxidized_yttrium_fragment",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> YTTRIUM_FRAGMENT = ITEMS.register("yttrium_fragment",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> HOT_YTTRIUM_FRAGMENT = ITEMS.register("hot_yttrium_fragment",
			() -> new BurningItem(new Item.Properties(), YTTRIUM_FRAGMENT.get(), 60, 50));
	public static final RegistryObject<Item> YTTRIUM_NUGGET = ITEMS.register("yttrium_nugget",
			() -> new Item(new Item.Properties().tab(ModCreativeTabs.INGOTS)));
	public static final RegistryObject<Item> HOT_YTTRIUM_NUGGET = ITEMS.register("hot_yttrium_nugget",
			() -> new BurningItem(new Item.Properties(), YTTRIUM_NUGGET.get(), 60, 25));
	
	public static final RegistryObject<SwordItem> THE_BIBLE = ITEMS.register("the_bible",
			() -> new BibleItem(ModItemTiers.THE_BIBLE, 0, 5,
					new Item.Properties().fireResistant().stacksTo(1).rarity(Rarity.EPIC)));

	// Tools
	public static final RegistryObject<PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
			() -> new PickaxeItem(ModItemTiers.STEEL, 1, -2.8f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword",
			() -> new SwordItem(ModItemTiers.STEEL, 8, -2.4f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<AxeItem> STEEL_AXE = ITEMS.register("steel_axe",
			() -> new AxeItem(ModItemTiers.STEEL, 10, -3f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ShovelItem> STEEL_SHOVEL = ITEMS.register("steel_shovel",
			() -> new ShovelItem(ModItemTiers.STEEL, 4, -3f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<HoeItem> STEEL_HOE = ITEMS.register("steel_hoe",
			() -> new HoeItem(ModItemTiers.STEEL, 0, 0, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<PickaxeItem> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe",
			() -> new PickaxeItem(ModItemTiers.BRONZE, 2, -2.8f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<SwordItem> BRONZE_SWORD = ITEMS.register("bronze_sword",
			() -> new SwordItem(ModItemTiers.BRONZE, 9, -2.4f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<AxeItem> BRONZE_AXE = ITEMS.register("bronze_axe",
			() -> new AxeItem(ModItemTiers.BRONZE, 12, -3f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ShovelItem> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
			() -> new ShovelItem(ModItemTiers.BRONZE, 5, -3f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<HoeItem> BRONZE_HOE = ITEMS.register("bronze_hoe",
			() -> new HoeItem(ModItemTiers.BRONZE, 0, 0, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<PickaxeItem> YTTRIUM_PICKAXE = ITEMS.register("yttrium_pickaxe",
			() -> new YttriumPickaxe(ModItemTiers.YTTRIUM, 2, -2.8f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<HammerItem> STEEL_HAMMER = ITEMS.register("steel_hammer",
			() -> new HammerItem(ModItemTiers.STEEL, 20, -3.5f, new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<BurningItem> HOT_YTTRIUM_PICKAXE = ITEMS.register("hot_yttrium_pickaxe",
			() -> new BurningItem(new Item.Properties().stacksTo(1), YTTRIUM_PICKAXE.get(), 120, 300));

	// Armor
	public static final RegistryObject<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
			() -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.HEAD,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
			() -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.CHEST,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
			() -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.LEGS,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
			() -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.FEET,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> BRONZE_HELMET = ITEMS.register("bronze_helmet",
			() -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
			() -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
			() -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<ArmorItem> BRONZE_BOOTS = ITEMS.register("bronze_boots",
			() -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.FEET,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));
	public static final RegistryObject<MagmatiteHelmetItem> MAGMATITE_HELMET = ITEMS.register("magmatite_helmet",
			() -> new MagmatiteHelmetItem(ModArmorMaterials.MAGMATITE, EquipmentSlot.HEAD,
					new Item.Properties().tab(ModCreativeTabs.TOOLS).fireResistant()));
	public static final RegistryObject<MagmatiteChestplateItem> MAGMATITE_CHESTPLATE = ITEMS
			.register("magmatite_chestplate", () -> new MagmatiteChestplateItem(ModArmorMaterials.MAGMATITE,
					EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeTabs.TOOLS).fireResistant()));
	public static final RegistryObject<MagmatiteLeggingsItem> MAGMATITE_LEGGINGS = ITEMS.register("magmatite_leggings",
			() -> new MagmatiteLeggingsItem(ModArmorMaterials.MAGMATITE, EquipmentSlot.LEGS,
					new Item.Properties().tab(ModCreativeTabs.TOOLS).fireResistant()));
	public static final RegistryObject<MagmatiteBootsItem> MAGMATITE_BOOTS = ITEMS.register("magmatite_boots",
			() -> new MagmatiteBootsItem(ModArmorMaterials.MAGMATITE, EquipmentSlot.FEET,
					new Item.Properties().tab(ModCreativeTabs.TOOLS).fireResistant()));
	public static final RegistryObject<AetherBootsItem> AETHER_BOOTS = ITEMS.register("aether_boots",
			() -> new AetherBootsItem(ModArmorMaterials.AETHER, EquipmentSlot.FEET,
					new Item.Properties().tab(ModCreativeTabs.TOOLS)));

	// Register block-items
	public static final RegistryObject<BlockItem> CRYSTAL_MEF_ITEM = ITEMS.register("crystal_mef",
			() -> new BlockItem(ModBlocks.CRYSTAL_MEF.get(), new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<BlockItem> YES_BLOCK_ITEM = ITEMS.register("yes_block",
			() -> new BlockItem(ModBlocks.YES_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC)));

	public static final RegistryObject<BlockItem> COPPER_ORE_ITEM = ITEMS.register("copper_ore",
			() -> new BlockItem(ModBlocks.COPPER_ORE.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> TIN_ORE_ITEM = ITEMS.register("tin_ore",
			() -> new BlockItem(ModBlocks.TIN_ORE.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> BLAZING_BLACKSTONE_ITEM = ITEMS.register("blazing_blackstone",
			() -> new BlazingBlackstoneBlockItem(ModBlocks.BLAZING_BLACKSTONE.get(),
					new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> AETHER_CRYSTAL_BLOCK_ITEM = ITEMS.register("aether_block",
			() -> new BlockItem(ModBlocks.AETHER_CRYSTAL_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> YTTRIUM_ORE_ITEM = ITEMS.register("yttrium_ore",
			() -> new BlockItem(ModBlocks.YTTRIUM_ORE.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> STEEL_BLOCK_ITEM = ITEMS.register("steel_block",
			() -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> COPPER_BLOCK_ITEM = ITEMS.register("copper_block",
			() -> new BlockItem(ModBlocks.COPPER_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> TIN_BLOCK_ITEM = ITEMS.register("tin_block",
			() -> new BlockItem(ModBlocks.TIN_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> BRONZE_BLOCK_ITEM = ITEMS.register("bronze_block",
			() -> new BlockItem(ModBlocks.BRONZE_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
	public static final RegistryObject<BlockItem> AETHER_CRYSTAL_GENERATOR_ITEM = ITEMS.register(
			"aether_crystal_generator",
			() -> new BlockItem(ModBlocks.AETHER_CRYSTAL_GENERATOR.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ANVIL_BLOCK_ITEM = ITEMS.register("anvil_block",
			() -> new BlockItem(ModBlocks.ANVIL_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.ORES)));
}
