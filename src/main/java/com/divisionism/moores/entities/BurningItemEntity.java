package com.divisionism.moores.entities;

import java.util.Random;

import com.divisionism.moores.utils.BurningItem;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class BurningItemEntity extends ItemEntity {

	public BurningItemEntity(EntityType<BurningItemEntity> p_31991_, Level p_31992_) {
		super(p_31991_, p_31992_);
	}

	public BurningItemEntity(Level p_32001_, double p_32002_, double p_32003_, double p_32004_, ItemStack p_32005_) {
		super(p_32001_, p_32002_, p_32003_, p_32004_, p_32005_);
	}

	public BurningItemEntity(EntityType<BurningItemEntity> p_31991_, Level p_31992_, double p_32002_, double p_32003_,
			double p_32004_, ItemStack stack) {
		super(p_31991_, p_31992_);
		this.setPos(p_32002_, p_32003_, p_32004_);
		this.setItem(stack);
		this.lifespan = (stack.getItem() == null ? 6000 : stack.getEntityLifespan(p_31992_));
	}

	public BurningItemEntity(Level p_149663_, double p_149664_, double p_149665_, double p_149666_, ItemStack p_149667_,
			double p_149668_, double p_149669_, double p_149670_) {
		super(p_149663_, p_149664_, p_149665_, p_149666_, p_149667_, p_149668_, p_149669_, p_149670_);
	}

	@Override
	public void onAddedToWorld() {

		super.onAddedToWorld();
		if (!this.getItem().getOrCreateTag().contains("temperature"))
			this.getItem().getOrCreateTag().putInt("temperature", 100);
	}

	@Override
	public void tick() {
		super.tick();

		Item cooledOffItem;

		if (this.getItem().getItem() instanceof BurningItem item)
			cooledOffItem = item.getCooledOffItem();
		else
			cooledOffItem = this.getItem().getItem();

		if (this.getItem().getOrCreateTag().getInt("temperature") > 0)
			this.getItem().getOrCreateTag().putInt("temperature",
					this.getItem().getOrCreateTag().getInt("temperature") - (this.isInWater() ? 2 : 1));
		else {
			ItemStack newStack = new ItemStack(cooledOffItem);
			newStack.setCount(this.getItem().getCount());
			this.setItem(newStack);
		}

		Random random = new Random();

		if (this.isInWater() && !this.getItem().is(cooledOffItem)) {

			if (this.tickCount % 3 == 0)
				this.level.addParticle(ParticleTypes.CLOUD, this.getPosition(1).x + random.nextDouble(-0.4, 0.4),
						this.getPosition(1).y + 0.5, this.getPosition(1).z + random.nextDouble(-0.4, 0.4), 0, 0.3, 0);

			this.level.addParticle(ParticleTypes.BUBBLE, this.getPosition(1).x + random.nextDouble(-0.4, 0.4),
					this.getPosition(1).y + 0.5, this.getPosition(1).z + random.nextDouble(-0.4, 0.4), 0, 0.6, 0);
				
			if (this.tickCount % 5 == 0)
				this.level.playLocalSound(this.getPosition(1).x, this.getPosition(1).y, this.getPosition(1).z,
						SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1, 1, false);
		}
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
