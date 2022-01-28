package com.divisionism.moores.events;

import java.util.UUID;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.init.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OreAddons.MOD_ID, value = Dist.CLIENT, bus = Bus.FORGE)
public class ForgeClientEventSubscriber {

	public static final UUID REKALH = UUID.fromString("b0374e3b-88d6-4843-9906-ea49dc2a59b5");
	public static final UUID NICKMANEA = UUID.fromString("c4761e89-7416-47c5-aa69-59740f5e5eff");

	@SubscribeEvent
	public static void onPlayerJoin(PlayerLoggedInEvent event) {
		Player player = event.getPlayer();
		if (player.getUUID().equals(REKALH))
			player.addItem(new ItemStack(ModItems.MAGMATITE.get(), 64));
		else if (player.getUUID().equals(NICKMANEA)) {
			player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "Welcome NickManEA!"), NICKMANEA);
			ItemStack stack = new ItemStack(Items.BOOK);
			stack.enchant(Enchantments.UNBREAKING, 10);
			stack.setHoverName(new TextComponent(ChatFormatting.DARK_RED + "Tataros"));
			player.addItem(stack);
		}
	}

	// Float when wearing aether boots
	static double damage = 0;
	@SubscribeEvent
	public static void playerTickEvent(PlayerTickEvent event) {
		Player player = event.player;
		double amplifier = 1d;
		
		damage += 0.001;
		
		if (player.getItemBySlot(EquipmentSlot.FEET) != null) {
			if (player.getItemBySlot(EquipmentSlot.FEET).sameItem(new ItemStack(ModItems.AETHER_BOOTS.get()))) {
				ItemStack item = player.getItemBySlot(EquipmentSlot.FEET);
				
				if (player.level.getFluidState(player.blockPosition().offset(0, -0.06, 0)).isSource()
						|| player.level.getFluidState(player.blockPosition()).isSource()) {
					player.setDeltaMovement(player.getDeltaMovement().x * amplifier, player.getDeltaMovement().y + 0.02,
							player.getDeltaMovement().z * amplifier);

					if (!player.isCreative()) {
						item.hurtAndBreak((int)Math.floor(damage), player, (plr) -> {
							if (!plr.level.isClientSide())
								plr.level.playSound(null, plr.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL, 1, 1);
							plr.awardStat(Stats.ITEM_BROKEN.get(item.getItem()));
							item.shrink(1);
						});
					}
					
					if (damage >= 1.001) damage = 0;
				}
			}
		}
	}
}
