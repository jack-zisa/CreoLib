package com.github.creoii.creolib.core.registry;

import com.github.creoii.creolib.core.CreoLib;
import com.github.creoii.creolib.api.world.feature.CompositeFeature;
import com.github.creoii.creolib.api.world.feature.config.CompositeFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public final class FeatureRegistry {
    public static final Feature<CompositeFeatureConfig> COMPOSITE = new CompositeFeature(CompositeFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registries.FEATURE, new Identifier(CreoLib.NAMESPACE, "composite"), COMPOSITE);
    }
}
