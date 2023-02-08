package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.feature.CompositeFeature;
import com.github.creoii.creolib.world.feature.config.CompositeFeatureConfig;
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
