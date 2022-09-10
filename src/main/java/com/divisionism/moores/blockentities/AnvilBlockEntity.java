package com.divisionism.moores.blockentities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.containers.AnvilContainer;
import com.divisionism.moores.init.ModBlockEntities;
import com.divisionism.moores.init.ModRecipeSerializers;
import com.divisionism.moores.recipes.AnvilRecipe;
import com.divisionism.moores.recipes.AnvilSerializer;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AnvilBlockEntity extends InventoryBlockEntity implements MenuProvider, Container {

	public static final TranslatableComponent TITLE = new TranslatableComponent(
			"container." + OreAddons.MOD_ID + ".anvil_title");

	protected ContainerData data;

	public int progress = 0;
	public int maxProgress = 100;

	public AnvilBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
		super(ModBlockEntities.FORGE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState, 4);
		this.data = new ContainerData() {

			@Override
			public void set(int pIndex, int pValue) {
				switch (pIndex) {
				case 0:
					AnvilBlockEntity.this.progress = pValue;
				case 1:
					AnvilBlockEntity.this.maxProgress = pValue;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public int get(int pIndex) {
				switch (pIndex) {
				case 0:
					return AnvilBlockEntity.this.progress;
				case 1:
					return AnvilBlockEntity.this.maxProgress;
				default:
					return 0;
				}
			}
		};
	}

	@Override
	public void tick() {
		super.tick();

		if (this.inventory.getStackInSlot(2).isDamageableItem())
			if (this.inventory.getStackInSlot(2).getDamageValue() >= this.inventory.getStackInSlot(2).getMaxDamage())
				this.inventory.getStackInSlot(2).shrink(1);

		AnvilRecipe recipe = this.getRecipe();

		if (recipe != null) {
			if (this.inventory.getStackInSlot(3).is(recipe.getResultItem().getItem())
					|| this.inventory.getStackInSlot(3).isEmpty()) {

				if (this.inventory.getStackInSlot(3).getCount() < this.inventory.getStackInSlot(3).getMaxStackSize()) {
					this.progress++;
					ItemStack output = recipe.getResultItem();

					if (this.progress == this.maxProgress) {

						Map<String, Integer> inputMap = ((AnvilSerializer) recipe.getSerializer()).getInputMap();
						Map<String, Integer> extraMap = ((AnvilSerializer) recipe.getSerializer()).getExtraMap();

						int inputCount = inputMap.getOrDefault(this.inventory.getStackInSlot(0).getItem().toString(),
								1);
						int extraCount = extraMap.getOrDefault(this.inventory.getStackInSlot(1).getItem().toString(),
								1);

						this.inventory.insertItem(3, output, false);

						for (int i = 0; i < inputCount; i++) {
							this.inventory.extractItem(0, 1, false);
						}
						for (int i = 0; i < extraCount; i++) {
							this.inventory.extractItem(1, 1, false);
						}

						this.inventory.getStackInSlot(2).hurt(100, new Random(), null);

						this.progress = 0;
					} else {
						if (this.timer % 40 == 0) {
							this.level.playSound(null, this.worldPosition, SoundEvents.ANVIL_USE, SoundSource.BLOCKS,
									0.6f, 0.8f);
						}
					}
				} else {
					this.progress = 0;
				}
			}
		} else {
			this.progress = 0;
		}
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack) {
		if (slot == 3)
			return ItemStack.EMPTY;
		return super.insertItem(slot, stack);
	}

	private AnvilRecipe getRecipe() {
		Set<Recipe<?>> recipes = this.findRecipesByType(this.level, ModRecipeSerializers.ANVIL_TYPE);

		for (Recipe<?> recipe : recipes) {
			AnvilRecipe fRecipe = (AnvilRecipe) recipe;

			if (fRecipe.matches(new RecipeWrapper(this.inventory), level)) {
				return fRecipe;
			}
		}

		return null;
	}

	private Set<Recipe<?>> findRecipesByType(Level level, RecipeType<?> type) {
		return level != null ? level.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == type)
				.collect(Collectors.toSet()) : Collections.emptySet();
	}

	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
		return new AnvilContainer(pContainerId, pInventory, this.inventory, this.getBlockPos(), this.data);
	}

	public int getProgress() {
		return this.progress;
	}

	@Override
	public Component getDisplayName() {
		return TITLE;
	}

	@Override
	public void clearContent() {
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			this.inventory.extractItem(i, this.inventory.getStackInSlot(i).getCount(), false);
		}
	}

	@Override
	public int getContainerSize() {
		return 4;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			if (!this.inventory.getStackInSlot(i).isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getItem(int pIndex) {
		return this.inventory.getStackInSlot(pIndex);
	}

	@Override
	public ItemStack removeItem(int pIndex, int pCount) {
		return this.inventory.extractItem(pIndex, pCount, false);
	}

	@Override
	public ItemStack removeItemNoUpdate(int pIndex) {
		List<ItemStack> stacks = new ArrayList<>();

		for (int i = 0; i < this.inventory.getSlots(); i++) {
			stacks.add(this.inventory.getStackInSlot(i));
		}

		return ContainerHelper.takeItem(stacks, pIndex);
	}

	@Override
	public void setItem(int pIndex, ItemStack pStack) {
		this.inventory.setStackInSlot(pIndex, pStack);
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return pPlayer.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
					(double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}
}
