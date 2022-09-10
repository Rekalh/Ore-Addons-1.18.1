package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.entities.BurningItemEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			OreAddons.MOD_ID);

	public static final RegistryObject<EntityType<BurningItemEntity>> BURNING_ITEM_ENTITY = ENTITY_TYPES
			.register("burning_item_entity",
					() -> EntityType.Builder.<BurningItemEntity>of(BurningItemEntity::new, MobCategory.MISC)
							.build(new ResourceLocation(OreAddons.MOD_ID, "burning_item_entity").toString()));

}
