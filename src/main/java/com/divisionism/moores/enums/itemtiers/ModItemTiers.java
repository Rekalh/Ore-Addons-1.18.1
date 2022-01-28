package com.divisionism.moores.enums.itemtiers;

import com.divisionism.moores.init.ModItems;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModItemTiers implements Tier {
	BRONZE(2000, 10, 5, 5, 15, Ingredient.of(ModItems.COPPER_INGOT.get())),
	STEEL(1000, 7, 5, 4, 10, Ingredient.of(ModItems.STEEL_INGOT.get())),
	YTTRIUM(600, 6, 3, 4, 10, Ingredient.of(Items.IRON_INGOT)),
	THE_BIBLE(5, 100, 0, 10, 20, Ingredient.of(Items.PAPER));

	private int durability;
	private int efficiency;
	private int damage;
	private int harvestLevel;
	private int enchantability;
	private Ingredient repairMaterial;
	
	ModItemTiers(int durability, int efficiency, int damage, int harvestLevel, int enchantability,
			Ingredient repairMaterial) {
		this.durability = durability;
		this.efficiency = efficiency;
		this.damage = damage;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getUses() {
		return this.durability;
	}

	@Override
	public float getSpeed() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.damage;
	}

	@Override
	public int getLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial;
	}
}
