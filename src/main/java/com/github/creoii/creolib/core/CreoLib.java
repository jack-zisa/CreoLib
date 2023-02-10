package com.github.creoii.creolib.core;

import com.github.creoii.creolib.api.tag.CBiomeTags;
import com.github.creoii.creolib.api.util.registry.RegistrySets;
import com.github.creoii.creolib.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.MapColor;

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

        RegistrySets.createColorSet(NAMESPACE, "teal", 1, MapColor.TEAL, 1, 1, MapColor.TERRACOTTA_BLUE).register();
    }
}
