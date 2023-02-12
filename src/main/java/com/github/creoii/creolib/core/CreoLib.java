package com.github.creoii.creolib.core;

import com.github.creoii.creolib.api.tag.CBiomeTags;
import com.github.creoii.creolib.core.registry.*;
import net.fabricmc.api.ModInitializer;

public final class CreoLib implements ModInitializer {
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
        CBiomeTags.loadVanillaFeatureTags();
    }
}
