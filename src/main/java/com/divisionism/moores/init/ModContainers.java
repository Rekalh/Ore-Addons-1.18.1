package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;
import com.divisionism.moores.containers.AnvilContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			OreAddons.MOD_ID);

	public static final RegistryObject<MenuType<AnvilContainer>> ANVIL_CONTAINER = CONTAINERS
			.register("anvil_container", () -> new MenuType<>(AnvilContainer::new));

}
