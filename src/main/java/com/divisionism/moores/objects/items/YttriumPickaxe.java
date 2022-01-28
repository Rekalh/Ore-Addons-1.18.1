package com.divisionism.moores.objects.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class YttriumPickaxe extends PickaxeItem {
	
	private Mode mode = Mode.NONE;
	private boolean isNone = (mode.getName() == "none");
	
	private ArrayList<Block> veinMinerBlackList = new ArrayList<>();
	
	public YttriumPickaxe(Tier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
		super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
		
		this.veinMinerBlackList.add(Blocks.STONE);
		this.veinMinerBlackList.add(Blocks.GRANITE);
		this.veinMinerBlackList.add(Blocks.DIORITE);
		this.veinMinerBlackList.add(Blocks.ANDESITE);
		this.veinMinerBlackList.add(Blocks.COBBLESTONE);
		this.veinMinerBlackList.add(Blocks.DEEPSLATE);
		this.veinMinerBlackList.add(Blocks.COBBLED_DEEPSLATE);
		this.veinMinerBlackList.add(Blocks.CALCITE);
		this.veinMinerBlackList.add(Blocks.POLISHED_BASALT);
		this.veinMinerBlackList.add(Blocks.TUFF);
		this.veinMinerBlackList.add(Blocks.NETHERRACK);
		this.veinMinerBlackList.add(Blocks.BLACKSTONE);
		this.veinMinerBlackList.add(Blocks.BASALT);
		this.veinMinerBlackList.add(Blocks.END_STONE);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip,
			TooltipFlag p_77624_4_) {
		tooltip.add(new TranslatableComponent("tooltip.moores.yttrium_pickaxe_1"));
		tooltip.add(new TranslatableComponent("tooltip.moores.yttrium_pickaxe_2")
				.append(new TextComponent("\u00A72 " + mode.getDisplayName())));
	}
	
	int counter = 0;
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		if (!world.isClientSide) {
			
			counter++;
			if (counter > 2) counter = 0;
			
			this.mode = Mode.values()[counter]; // Gets the next mode
			this.isNone = (mode.getName() == "none");
			
			ItemStack stack = player.getItemInHand(hand);
			CompoundTag nbt = stack.getOrCreateTag(); // Creates new tag or gets existing one
			nbt.putString("mode", this.mode.getName()); // Appends the new mode to the nbt
			stack.setTag(nbt); // Sets the tag to the modified nbt
			
			player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "Changed mode to: " + ChatFormatting.GREEN + mode.getDisplayName()), player.getUUID());
		}
		
		if (!this.mode.getName().equals("aoe")) world.playSound(player, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 1f, 1f);
		else world.playSound(player, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 1f, 0.7f);
		
		return InteractionResultHolder.success(player.getItemInHand(hand));
	}
	
	@Override
	public boolean isFoil(ItemStack p_77636_1_) {
		return !this.isNone;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		
		Level world = player.level;
		PickaxeItem self = (PickaxeItem)itemstack.getItem();
		
		if (!self.isCorrectToolForDrops(itemstack, world.getBlockState(pos)))
			return false;
	
		if (!world.isClientSide) {
			
			int blocksBroken = 0;
			// AoE
			if (itemstack.getTag().getString("mode").equals("aoe")) {
				world.playSound(player, new BlockPos(player.position()), SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 1.0f, 1.0f);
				
				for (int x = -1; x < 2; x++) {
					for (int z = -1; z < 2; z++) {
						for (int y = -1; y < 2; y++) {
							if (!self.isCorrectToolForDrops(itemstack, world.getBlockState(pos.offset(x, y, z)))) continue;
							world.destroyBlock(pos.offset(x, y, z), !player.isCreative());
							blocksBroken++;
						}
					}
				}
			}
			
			//Vein Miner
			if (itemstack.getTag().getString("mode").equals("veinminer")) {
				
				int radius = 2;
				Block block = world.getBlockState(pos).getBlock();
				BlockPos bPos = pos;
				
				if (veinMinerBlackList.contains(block)) {
					super.onBlockStartBreak(itemstack, bPos, player);
					return false;
				}
				
				// Actual vein mining code
				for (int x = -radius; x <= radius; x++) {
					for (int y = -radius; y <= radius; y++) {
						for (int z = -radius; z <= radius; z++) {
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
	
	private enum Mode {
		
		NONE("none", "None"),
		VEINMINER("veinminer", "Vein Miner"),
		AOE("aoe", "Area of Effect");
		
		private String name;
		private String displayName;
		Mode(String name, String displayName) {
			this.name = name;
			this.displayName = displayName;
		}
		
		String getName() {
			return this.name;
		}
		
		String getDisplayName() {
			return this.displayName;
		}
	}
}
