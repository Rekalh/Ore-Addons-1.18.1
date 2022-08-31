package com.divisionism.moores.objects.items;

import java.util.List;

import com.divisionism.moores.utils.SpecialPickaxeItem;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class YttriumPickaxe extends SpecialPickaxeItem {
	
	public YttriumPickaxe(Tier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
		super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_, 1, 2);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip,
			TooltipFlag p_77624_4_) {
		tooltip.add(new TranslatableComponent("tooltip.moores.yttrium_pickaxe_1"));
		tooltip.add(new TranslatableComponent("tooltip.moores.yttrium_pickaxe_3"));
		tooltip.add(new TranslatableComponent("tooltip.moores.yttrium_pickaxe_2")
				.append(new TextComponent("\u00A72 " + (stack.getTag().contains("mode") ? Mode.values()[stack.getTag().getInt("mode")].getDisplayName() : "None"))));
	}
}
