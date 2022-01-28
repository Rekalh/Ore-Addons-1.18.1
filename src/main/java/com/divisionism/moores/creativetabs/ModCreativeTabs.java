package com.divisionism.moores.creativetabs;

import com.divisionism.moores.init.ModItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {

	public static final CreativeModeTab INGOTS = new CreativeModeTab("ingots") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.STEEL_INGOT.get());
		}
	};
	
	public static final CreativeModeTab ORES = new CreativeModeTab("ores") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.COPPER_ORE_ITEM.get());
		}
	};
	
	public static final CreativeModeTab TOOLS = new CreativeModeTab("tools") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.STEEL_PICKAXE.get());
		}
	};
}
