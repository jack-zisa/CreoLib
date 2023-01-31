package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.placement.NoisePlacementModifier;
import com.github.creoii.creolib.world.placement.SteepPlacementModifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class PlacementRegistry {
    public static final PlacementModifierType<NoisePlacementModifier> NOISE = () -> NoisePlacementModifier.CODEC;
    public static final PlacementModifierType<SteepPlacementModifier> STEEP = () -> SteepPlacementModifier.CODEC;

    public static void register() {
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "noise"), NOISE);
        Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, new Identifier(CreoLib.NAMESPACE, "steep"), STEEP);
    }
}
