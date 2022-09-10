package com.divisionism.moores.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class InventoryBlockEntity extends BlockEntity {

	protected final int size;
	protected int timer;
	protected boolean requiresUpdate;
	
	public final ItemStackHandler inventory;
	private final LazyOptional<ItemStackHandler> handler;
	
	public InventoryBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState, int size) {
		super(pType, pWorldPosition, pBlockState);
		this.size = size;
		this.inventory = this.createInventory();
		this.handler = LazyOptional.of(() -> inventory);
	}
	
	private ItemStackHandler createInventory() {
		return new ItemStackHandler(size) {
			
			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				InventoryBlockEntity.this.update();
				return super.insertItem(slot, stack, simulate);
			}
			
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				InventoryBlockEntity.this.update();
				return super.extractItem(slot, amount, simulate);
			}
		};
	}
	
	public ItemStack insertItem(int slot, ItemStack stack) {
		ItemStack copy = stack.copy();
		stack.shrink(copy.getCount());
		this.requiresUpdate = true;
		return handler.map(inv -> inv.insertItem(slot, copy, false)).orElse(ItemStack.EMPTY);
	}
	
	public ItemStack extractItem(int slot) {
		int count = this.getStackInSlot(slot).getCount();
		this.requiresUpdate = true;
		return handler.map(inv -> inv.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
	}
	
	public ItemStack getStackInSlot(int slot) {
		return this.handler.map(inv -> inv.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
	}
	
	public void update() {
		this.requestModelDataUpdate();
		this.setChanged();
		this.level.setBlockAndUpdate(this.worldPosition, this.getBlockState());
	}
	
	public void tick() {
		timer++;
		if (this.requiresUpdate) {
			this.update();
			this.requiresUpdate = false;
		}
	}
	
	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		this.inventory.deserializeNBT(pTag.getCompound("Inventory"));
	}
	
	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.put("Inventory", this.inventory.serializeNBT());
	}
	
	@Override
	public CompoundTag getUpdateTag() {
		return this.serializeNBT();
	}
	
	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);
		this.load(tag);
	}
	
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		this.handler.invalidate();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? handler.cast() : super.getCapability(cap, side);
	}
}
