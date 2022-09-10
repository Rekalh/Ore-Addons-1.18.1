package com.divisionism.moores.objects.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BlazingBlackstone extends Block {

	private Random random = new Random();

	public BlazingBlackstone(Properties properties) {
		super(properties);
	}
	
	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		if (!(placer instanceof Player)) return;
		
		if (!level.dimensionType().ultraWarm()) {
			for (int i = 0; i < 5; i++) {
				level.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + random.nextFloat(), pos.getY() + 1,
						pos.getZ() + random.nextFloat(), 0f, 0.1f, 0f);
			}
			level.playSound((Player) placer, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1f,
					1f);
		}

		if (getSurroundingBlocks(level, pos).contains(Fluids.WATER.defaultFluidState().createLegacyBlock().getBlock())
				|| getSurroundingBlocks(level, pos)
						.contains(Fluids.FLOWING_WATER.defaultFluidState().createLegacyBlock().getBlock())) {
			level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + random.nextFloat(), pos.getY() + 1,
					pos.getZ() + random.nextFloat(), 0f, 0.1f, 0f);
			level.playSound((Player) placer, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1f,
					1f);
			level.setBlock(pos, Blocks.BLACKSTONE.defaultBlockState(), 0);

		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
		super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
		//p_60516_.setBlock(p_60517_, Blocks.WATER.defaultBlockState(), UPDATE_NONE);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> tooltip,
			TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.moores.blazing_blackstone"));
	}
	
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			((LivingEntity) entityIn).setSecondsOnFire(60);;
		}
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos position, Random random) {

		level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY() + 1,
				position.getZ() + random.nextDouble(), 0, 0.01, 0);
		level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY(),
				position.getZ() + random.nextDouble(), 0, -0.01, 0);
		level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY() + random.nextDouble(),
				position.getZ() + 1, 0, 0, 0.01);
		level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY() + random.nextDouble(),
				position.getZ(), 0, 0, -0.01);
		level.addParticle(ParticleTypes.FLAME, position.getX() + 1, position.getY() + random.nextDouble(),
				position.getZ() + random.nextDouble(), 0.01, 0, 0);
		level.addParticle(ParticleTypes.FLAME, position.getX(), position.getY() + random.nextDouble(),
				position.getZ() + random.nextDouble(), -0.01, 0, 0);
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		if (getSurroundingBlocks(level, pos).contains(Blocks.AIR)) {
			for (int i = 0; i < 50; i++) {
				level.sendParticles(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f,
						random.nextInt(5), 0.1f, 0, 0.1f, 0.1f);
				level.sendParticles(ParticleTypes.FLAME, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f,
						random.nextInt(5), 0.1f, 0, 0.1f, 0.1f);
				level.sendParticles(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5f, pos.getY() + 0.5f,
						pos.getZ() + 0.5f, 1, 0.1f, 0, 0.1f, 0.1f);
			}
		}
	}
	
	private ArrayList<Block> getSurroundingBlocks(Level level, BlockPos pos) {
		ArrayList<Block> surroundingBlocks = new ArrayList<>();
		for (int x = -1; x < 2; x++) {
			if (x == 0)
				continue;
			surroundingBlocks.add(level.getBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ())).getBlock());
		}
		for (int z = -1; z < 2; z++) {
			if (z == 0)
				continue;
			surroundingBlocks.add(level.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + z)).getBlock());
		}
		for (int y = -1; y < 2; y++) {
			if (y == 0)
				continue;
			surroundingBlocks.add(level.getBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ())).getBlock());
		}
		return surroundingBlocks;
	}
}
