package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.placement.DistanceFromZeroStructurePlacement;
import com.github.creoii.creolib.world.placement.FastNoiseStructurePlacement;
import com.github.creoii.creolib.world.placement.FixedStructurePlacement;
import com.github.creoii.creolib.world.placement.NoiseStructurePlacement;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public class StructurePlacementTypeRegistry {
    public static StructurePlacementType<FixedStructurePlacement> FIXED = () -> FixedStructurePlacement.CODEC;
    public static StructurePlacementType<DistanceFromZeroStructurePlacement> DISTANCE_FROM_ZERO = () -> DistanceFromZeroStructurePlacement.CODEC;
    public static StructurePlacementType<NoiseStructurePlacement> NOISE = () -> NoiseStructurePlacement.CODEC;
    public static StructurePlacementType<FastNoiseStructurePlacement> FAST_NOISE = () -> FastNoiseStructurePlacement.CODEC;

    public static void register() {
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoLib.NAMESPACE, "fixed"), FIXED);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoLib.NAMESPACE, "distance_from_zero"), DISTANCE_FROM_ZERO);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoLib.NAMESPACE, "noise"), NOISE);
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(CreoLib.NAMESPACE, "fast_noise"), FAST_NOISE);
    }
}
