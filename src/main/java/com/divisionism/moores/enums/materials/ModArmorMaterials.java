package com.divisionism.moores.enums.materials;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.init.ModItems;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {

	STEEL(OreAddons.MOD_ID + ":steel", 1000, new int[] { 2, 6, 6, 3 }, 0.0f, 0.005f, 15,
			Ingredient.of(ModItems.STEEL_INGOT.get()), SoundEvents.ARMOR_EQUIP_IRON),
	BRONZE(OreAddons.MOD_ID + ":bronze", 2500, new int[] { 3, 6, 7, 4 }, 0.0f, 0.01f, 15,
			Ingredient.of(ModItems.BRONZE_INGOT.get()), SoundEvents.ARMOR_EQUIP_IRON),
	MAGMATITE(OreAddons.MOD_ID + ":magmatite", 1600, new int[] { 3, 7, 6, 4 }, 0.0f, 0.06f, 17, Ingredient.EMPTY,
			SoundEvents.ARMOR_EQUIP_NETHERITE),
	AETHER(OreAddons.MOD_ID + ":aether", 700, new int[] { 0, 0, 0, 3 }, 0.0f, 0.0f, 15,
			Ingredient.of(ModItems.AETHER_CRYSTAL.get()), SoundEvents.ARMOR_EQUIP_GOLD),
	FARADIUM(OreAddons.MOD_ID + ":faradium", 7000, new int[] { 5, 9, 9, 6 }, 0.0f, 0.1f, 20, Ingredient.EMPTY,
			SoundEvents.ARMOR_EQUIP_NETHERITE);

	private String name;
	private int durability;
	private int[] damageReduction;
	private float toughness;
	private float knockbackResistance;
	private int enchantability;
	private Ingredient repairMaterial;
	private SoundEvent soundEvent;

	ModArmorMaterials(String name, int durability, int[] damageReduction, float toughness, float knockbackResistance,
			int enchantability, Ingredient repairMaterial, SoundEvent soundEvent) {
		this.durability = durability;
		this.damageReduction = damageReduction;
		this.soundEvent = soundEvent;
		this.repairMaterial = repairMaterial;
		this.name = name;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot slotIn) {
		return this.durability;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slotIn) {
		return this.damageReduction[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
