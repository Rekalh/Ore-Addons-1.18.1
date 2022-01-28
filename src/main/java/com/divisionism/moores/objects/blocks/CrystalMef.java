package com.divisionism.moores.objects.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CrystalMef extends Block {

	private int state;
	private CompoundTag nbt = new CompoundTag();

	public CrystalMef(Properties properties) {
		super(properties);
		this.state = -1;
		if (this.nbt.isEmpty())
			this.write(this.nbt);
		else
			this.read(nbt);
	}

	public CrystalMef(Properties properties, int state) {
		super(properties);
		this.state = state;
		if (this.nbt.isEmpty())
			this.write(this.nbt);
		else
			this.read(nbt);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level LevelIn, BlockPos pos, Player player,
			InteractionHand handIn, BlockHitResult hit) {

		if (!LevelIn.isClientSide()) {
				state++;
			switch (this.state) {
				case 0:
					player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "gato ton"), player.getUUID());
					break;
				case 1:
					player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "estas bien?"),
							player.getUUID());
					break;
				case 2:
					player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "colapso."), player.getUUID());
					break;
				case 3:
					player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "coronavirus"),
							player.getUUID());
					break;
				case 4:
					player.sendMessage(new TextComponent(ChatFormatting.YELLOW + "abueno adios master"),
							player.getUUID());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					LevelIn.explode(null, pos.getX(), pos.getY() - 1, pos.getZ(), 15f, Explosion.BlockInteraction.DESTROY);
					LevelIn.destroyBlock(pos, false);
					state = -1;
					break;
			}
			this.write(nbt);
		}
		return InteractionResult.SUCCESS;
	}

	public void write(CompoundTag nbt) {
		nbt.putInt("state", this.state);
	}

	public void read(CompoundTag nbt) {
		this.state = nbt.getInt("state");
	}
}
