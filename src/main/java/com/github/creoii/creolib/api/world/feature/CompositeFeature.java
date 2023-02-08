package com.github.creoii.creolib.api.world.feature;

import com.github.creoii.creolib.api.world.feature.config.CompositeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CompositeFeature extends Feature<CompositeFeatureConfig> {
    public CompositeFeature(Codec<CompositeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext context) {
        boolean generated = false;
        CompositeFeatureConfig config = (CompositeFeatureConfig) context.getConfig();
        for (RegistryEntry<PlacedFeature> entry : config.features()) {
            if (entry.value().generateUnregistered(context.getWorld(), context.getGenerator(), context.getRandom(), context.getOrigin())) {
                generated = true;
            } else if (config.failIfFirstFails()) break;
        }
        return generated;
    }
}
