package com.divisionism.moores.containers;

import com.divisionism.moores.init.ModBlocks;
import com.divisionism.moores.init.ModContainers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AnvilContainer extends AbstractContainerMenu {

	private final ContainerLevelAccess containerAccess;
	private ContainerData data;
	
	public AnvilContainer(int id, Inventory inventory) {
		this(id, inventory, new ItemStackHandler(4), BlockPos.ZERO, new SimpleContainerData(2));
	}
	
	public AnvilContainer(int id, Inventory inventory, IItemHandler slots, BlockPos pos, ContainerData data) {
		super(ModContainers.ANVIL_CONTAINER.get(), id);
		this.containerAccess = ContainerLevelAccess.create(inventory.player.level, pos);
		this.data = data;
		
		int slotSizePlus2 = 18, startX = 8, hotbarY = 142, inventoryY = 84;
		
		// Anvil Slots
		this.addSlot(new SlotItemHandler(slots, 0, 27, 47)); // Input 1
		this.addSlot(new SlotItemHandler(slots, 1, 76, 47)); // Input 2
		this.addSlot(new SlotItemHandler(slots, 2, 51, 25)); // Hammer
		this.addSlot(new SlotItemHandler(slots, 3, 134, 47)); // Output
		
		// Player Inventory
		for (int row = 0; row < 3; row++) {
		    for (int column = 0; column < 9; column++) {
		        this.addSlot(new Slot(inventory, 9 + row * 9 + column, startX + column * slotSizePlus2,
		                inventoryY + row * slotSizePlus2));
		    }
		}
		
		// Hotbar
		for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(inventory, column, startX + column * slotSizePlus2, hotbarY));
        }
		
		this.addDataSlots(data);
	}

	public boolean isCrafting() {
		return this.data.get(0) > 0;
	}
	
	public int getProgressScaled() {
		int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack retStack = ItemStack.EMPTY;
		Slot slot = this.getSlot(index);
		if (slot.hasItem()) {
			ItemStack item = slot.getItem();
			retStack = item.copy();
			if (index < 4) {
				if (!moveItemStackTo(item, 4, this.slots.size(), true))
					return ItemStack.EMPTY;
			} else if (!moveItemStackTo(item, 0, 4, false))
				return ItemStack.EMPTY;
	
			if (item.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return retStack;
	}
	
	@Override
	public boolean stillValid(Player pPlayer) {
		return AbstractContainerMenu.stillValid(this.containerAccess, pPlayer, ModBlocks.ANVIL_BLOCK.get());
	}
}
