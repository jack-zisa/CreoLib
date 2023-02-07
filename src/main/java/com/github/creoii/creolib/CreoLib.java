package com.github.creoii.creolib;

import com.github.creoii.creolib.registry.*;
import net.fabricmc.api.ModInitializer;

public class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo";
    public static final String COMMON = "c";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        FeatureRegistry.register();
        PlacementModifierRegistry.register();
        StructurePlacementTypeRegistry.register();
        MaterialConditionRegistry.register();
        DensityFunctionTypeRegistry.register();
    }
}
