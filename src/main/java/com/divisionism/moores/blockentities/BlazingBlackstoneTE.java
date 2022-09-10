package com.divisionism.moores.blockentities;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

// Not used anywhere
@Deprecated
public class BlazingBlackstoneTE extends BlockEntity {

	private Random random = new Random();
	
	public BlazingBlackstoneTE(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
	}
	
	int ticks = 0;
	public void tick() {
		ticks++;

		BlockPos position = this.getBlockPos();
		
		if (ticks == 5) {
			
			System.out.println("Ticking!");
			
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
