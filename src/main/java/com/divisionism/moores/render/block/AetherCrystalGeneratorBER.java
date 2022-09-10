package com.divisionism.moores.render.block;

import com.divisionism.moores.blockentities.AetherCrystalGeneratorBE;
import com.divisionism.moores.objects.blocks.AetherCrystalGeneratorBlock;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.debug.LightDebugRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.model.data.EmptyModelData;

public class AetherCrystalGeneratorBER implements BlockEntityRenderer<AetherCrystalGeneratorBE> {

	private BlockEntityRendererProvider.Context context;

	public AetherCrystalGeneratorBER(BlockEntityRendererProvider.Context context) {
		this.context = context;
	}

	@Override
	public void render(AetherCrystalGeneratorBE blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int combinedOverlay, int packedLight) {

		AetherCrystalGeneratorBlock block = (AetherCrystalGeneratorBlock) blockEntity.getBlockState().getBlock();
		
		BlockPos pos = blockEntity.getBlockPos();
		Level level = blockEntity.getLevel();
		
		LightDebugRenderer debug = new LightDebugRenderer(Minecraft.getInstance());
		
		BlockRenderDispatcher dispatcher = context.getBlockRenderDispatcher();
		dispatcher.renderSingleBlock(block.getNearbyBlockState(pos, level), stack, buffer, combinedOverlay, packedLight, EmptyModelData.INSTANCE);
		debug.render(stack, buffer, blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ());
	}
}
