package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.placement.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class PlacementModifierRegistry {
    public static final PlacementModifierType<NoisePlacementModifier> NOISE = () -> NoisePlacementModifier.CODEC;
    public static final PlacementModifierType<FastNoisePlacementModifier> FAST_NOISE = () -> FastNoisePlacementModifier.CODEC;
    public static final PlacementModifierType<SteepPlacementModifier> STEEP = () -> SteepPlacementModifier.CODEC;
    public static final PlacementModifierType<DimensionPlacementModifier> DIMENSION = () -> DimensionPlacementModifier.CODEC;
    public static final PlacementModifierType<OffsetPlacementModifier> OFFSET = () -> OffsetPlacementModifier.CODEC;
    public static final PlacementModifierType<DensityFunctionPlacementModifier> DENSITY_FUNCTION = () -> DensityFunctionPlacementModifier.CODEC;

    public static void register() {
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "noise"), NOISE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "fast_noise"), FAST_NOISE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "steep"), STEEP);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "dimension"), DIMENSION);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "offset"), OFFSET);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "density_function"), DENSITY_FUNCTION);
    }
}
