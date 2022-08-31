package com.divisionism.moores.utils;

import java.util.Arrays;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SpecialPickaxeItem extends PickaxeItem {

	protected int aoeSize;
	protected int veinMinerSize;
	protected Block[] veinMinerBlacklist;
	
	public SpecialPickaxeItem(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_, int aoeSize,
			int veinMinerSize, Block[] veinMinerBlacklist) {
		super(p_42961_, p_42962_, p_42963_, p_42964_);
		this.aoeSize = aoeSize;
		this.veinMinerSize = veinMinerSize;
		this.veinMinerBlacklist = veinMinerBlacklist;
	}

	public SpecialPickaxeItem(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_, int aoeSize,
			int veinMinerSize) {
		this(p_42961_, p_42962_, p_42963_, p_42964_, aoeSize, veinMinerSize,
				new Block[] { Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.COBBLESTONE,
						Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.CALCITE, Blocks.POLISHED_BASALT, Blocks.TUFF,
						Blocks.NETHERRACK, Blocks.BLACKSTONE, Blocks.BASALT, Blocks.END_STONE, Blocks.SANDSTONE });
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		ItemStack pickaxe = player.getItemInHand(hand);
		CompoundTag nbt = pickaxe.getOrCreateTag();
		pickaxe.setTag(nbt);		
		
		if (!player.isCrouching()) {
			if (!world.isClientSide) {

				nbt.putInt("mode", nbt.getInt("mode") == 2 ? 0 : nbt.getInt("mode") + 1);

				player.displayClientMessage(new TextComponent(
						ChatFormatting.YELLOW + "Changed mode to: " + ChatFormatting.GREEN + Mode.values()[nbt.getInt("mode")].getDisplayName()),
						true);
			}

			if (nbt.getInt("mode") != 2)
				world.playSound(player, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL,
						1f, 1f);
			else
				world.playSound(player, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL,
						1f, 0.7f);
		} else if (nbt.getInt("mode") != 0) {
			nbt.putInt("mode", 0);
			player.displayClientMessage(
					new TextComponent(ChatFormatting.RED + "Reset the mode to: " + ChatFormatting.GREEN + "None"),
					true);
			world.playSound(player, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 1f,
					0.7f);
		}

		return InteractionResultHolder.success(player.getItemInHand(hand));
	}

	@Override
	public boolean isFoil(ItemStack p_77636_1_) {
		
		if (!p_77636_1_.getTag().contains("mode")) return false;
		
		return p_77636_1_.getTag().getInt("mode") != 0 || p_77636_1_.isEnchanted();
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {

		if (!itemstack.getTag().contains("mode")) super.onBlockStartBreak(itemstack, pos, player);
		
		Level world = player.level;
		PickaxeItem self = (PickaxeItem) itemstack.getItem();
		
		if (world.getBlockState(pos).is(Blocks.BEDROCK))
			return true;

		if (!self.isCorrectToolForDrops(itemstack, world.getBlockState(pos)))
			return false;

		if (!world.isClientSide) {

			int blocksBroken = 0;
			// AoE
			if (itemstack.getTag().getInt("mode") == 2) {
				world.playSound(player, new BlockPos(player.position()), SoundEvents.PLAYER_LEVELUP,
						SoundSource.NEUTRAL, 1.0f, 1.0f);

				for (int x = -aoeSize; x < aoeSize + 1; x++) {
					for (int z = -aoeSize; z < aoeSize + 1; z++) {
						for (int y = -aoeSize; y < aoeSize + 1; y++) {
							if (!self.isCorrectToolForDrops(itemstack, world.getBlockState(pos.offset(x, y, z))))
								continue;
							world.destroyBlock(pos.offset(x, y, z), !player.isCreative());
							blocksBroken++;
						}
					}
				}
			}

			// Vein Miner
			if (itemstack.getTag().getInt("mode") == 1) {

				Block block = world.getBlockState(pos).getBlock();
				BlockPos bPos = pos;

				if (Arrays.asList(veinMinerBlacklist).contains(block)) {
					super.onBlockStartBreak(itemstack, bPos, player);
					return false;
				}

				// Actual vein mining code
				for (int x = -veinMinerSize; x <= veinMinerSize; x++) {
					for (int y = -veinMinerSize; y <= veinMinerSize; y++) {
						for (int z = -veinMinerSize; z <= veinMinerSize; z++) {
							if (world.getBlockState(bPos.offset(x, y, z)).getBlock() == block) {
								
								world.destroyBlock(bPos.offset(x, y, z), !player.isCreative());

								blocksBroken += 4; // Used for calculating the damage
							}
						}
					}
				}
			}

			itemstack.hurtAndBreak(blocksBroken, player, (plr) -> {
				plr.awardStat(Stats.ITEM_BROKEN.get(itemstack.getItem()));
			});
		}

		return false;
	}

	protected enum Mode {

		NONE("none", "None", 0), VEINMINER("veinminer", "Vein Miner", 1), AOE("aoe", "Area of Effect", 2);

		private String name;
		private String displayName;
		private int id;

		Mode(String name, String displayName, int id) {
			this.name = name;
			this.displayName = displayName;
			this.id = id;
		}

		public String getName() {
			return this.name;
		}

		public String getDisplayName() {
			return this.displayName;
		}

		public int getId() {
			return this.id;
		}
	}
}
