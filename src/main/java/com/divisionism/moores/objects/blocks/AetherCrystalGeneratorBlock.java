package com.divisionism.moores.objects.blocks;

import java.util.Random;

import com.divisionism.moores.init.ModBlockEntities;
import com.divisionism.moores.init.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AetherCrystalGeneratorBlock extends Block implements EntityBlock {

	Direction[] directions = Direction.values(); 
	
	public AetherCrystalGeneratorBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {
		
		Direction direction = directions[random.nextInt(directions.length)];
		BlockPos position = pos.relative(direction);
		BlockState blockstate = serverLevel.getBlockState(position);
		
		Block block = null;
		
		if (BuddingAmethystBlock.canClusterGrowAtState(blockstate)) {
			block = Blocks.SMALL_AMETHYST_BUD;
		} 
		else if (blockstate.is(Blocks.SMALL_AMETHYST_BUD)) {
			block = Blocks.MEDIUM_AMETHYST_BUD;
		}
		else if (blockstate.is(Blocks.MEDIUM_AMETHYST_BUD)) {
			block = Blocks.LARGE_AMETHYST_BUD;
		}
		
		if (block != null) {
			serverLevel.setBlockAndUpdate(position, block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction));
		}
	}
	
	public BlockState getNearbyBlockState(BlockPos pos, Level level) {
		
		if (isBlockValid(level.getBlockState(pos.below()).getBlock())) return level.getBlockState(pos.below());
		else if (isBlockValid(level.getBlockState(pos.above()).getBlock())) return level.getBlockState(pos.above());
		else if (isBlockValid(level.getBlockState(pos.west()).getBlock())) return level.getBlockState(pos.west());
		else if (isBlockValid(level.getBlockState(pos.east()).getBlock())) return level.getBlockState(pos.east());
		else if (isBlockValid(level.getBlockState(pos.north()).getBlock())) return level.getBlockState(pos.north());
		else if (isBlockValid(level.getBlockState(pos.south()).getBlock())) return level.getBlockState(pos.south());
		
		return Blocks.STONE.defaultBlockState();
	}
	
	public boolean isBlockValid(Block block) {
		return block != Blocks.AIR && block != ModBlocks.AETHER_CRYSTAL_GENERATOR.get() && block != Blocks.WATER && block != Blocks.SMALL_AMETHYST_BUD
				&& block != Blocks.MEDIUM_AMETHYST_BUD && block != Blocks.LARGE_AMETHYST_BUD;
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return ModBlockEntities.AETHER_CRYSTAL_GENERATOR_BE.get().create(pos, state);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
}
