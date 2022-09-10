package com.divisionism.moores.client.screen;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.containers.AnvilContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AnvilScreen extends AbstractContainerScreen<AnvilContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(OreAddons.MOD_ID, "textures/gui/anvil_screen.png");
	
	public AnvilScreen(AnvilContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	 @Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		this.font.draw(pPoseStack, this.title, this.leftPos + this.imageWidth / 2 - 25, this.topPos + 10, 0x404040);
		this.font.draw(pPoseStack, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 73, 0x404040);
		this.renderTooltip(pPoseStack, pMouseX, pMouseY);
	}
	
	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		this.renderBackground(pPoseStack);
		RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.setShaderTexture(0, TEXTURE);
		this.blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		
		if (this.menu.isCrafting()) {
			this.blit(pPoseStack, this.leftPos + 101, this.topPos + 47, 176, 0, this.menu.getProgressScaled(), 17);
		}
	}
	
	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
		
	}
}
