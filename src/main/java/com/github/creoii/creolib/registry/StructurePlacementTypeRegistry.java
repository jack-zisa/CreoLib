package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.placement.FixedStructurePlacement;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public class StructurePlacementTypeRegistry {
    public static StructurePlacementType<FixedStructurePlacement> FIXED = () -> FixedStructurePlacement.CODEC;

    public static void register() {
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoLib.NAMESPACE, "fixed"), FIXED);
    }
}
