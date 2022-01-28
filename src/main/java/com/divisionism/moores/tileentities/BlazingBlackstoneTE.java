package com.divisionism.moores.tileentities;

import java.util.Random;

import com.divisionism.moores.init.ModTileEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlazingBlackstoneTE extends BlockEntity {
	
	private Random random = new Random();
	
	public BlazingBlackstoneTE(BlockPos pos, BlockState state) {
		super(ModTileEntities.BLAZING_BLACKSTONE.get(), pos, state);
	}
	
	int ticks = 0;
	public void tick() {
		ticks++;

		BlockPos position = this.getBlockPos();
		
		if (ticks == 5) {
			
			this.level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY() + 1,
					position.getZ() + random.nextDouble(), 0, 0.01, 0);
			this.level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY(),
					position.getZ() + random.nextDouble(), 0, -0.01, 0);
			this.level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY() + random.nextDouble(),
					position.getZ() + 1, 0, 0, 0.01);
			this.level.addParticle(ParticleTypes.FLAME, position.getX() + random.nextDouble(), position.getY() + random.nextDouble(),
					position.getZ(), 0, 0, -0.01);
			this.level.addParticle(ParticleTypes.FLAME, position.getX() + 1, position.getY() + random.nextDouble(),
					position.getZ() + random.nextDouble(), 0.01, 0, 0);
			this.level.addParticle(ParticleTypes.FLAME, position.getX(), position.getY() + random.nextDouble(),
					position.getZ() + random.nextDouble(), -0.01, 0, 0);
			
			ticks = 0;
		}
	}
}
