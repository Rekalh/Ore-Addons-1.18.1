package com.divisionism.moores.init;

import com.divisionism.moores.OreAddons;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {

	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES,
			OreAddons.MOD_ID);

	// Register attributes
	public static final RegistryObject<Attribute> FLOAT = ATTRIBUTES.register("generic.float",
			() -> new RangedAttribute("attribute.name.generic.float", 0, 0, 1).setSyncable(true));
	public static final RegistryObject<Attribute> SLOW_FALLING = ATTRIBUTES.register("generic.slow_falling",
			() -> new RangedAttribute("attribute.name.generic.slow_falling", 0, 0, 1).setSyncable(true));
	public static final RegistryObject<Attribute> JUMP_BOOST = ATTRIBUTES.register("generic.jump_boost",
			() -> new RangedAttribute("attribute.name.generic.jump_boost", 0, 0, 1).setSyncable(true));

}
