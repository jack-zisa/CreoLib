package com.github.creoii.creolib.api.tag;

import com.github.creoii.creolib.core.CreoLib;
import com.google.common.collect.Maps;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

import java.util.Arrays;
import java.util.Map;

public final class CBiomeTags {
    public static final Map<RegistryKey<PlacedFeature>, TagKey<Biome>> HAS_FEATURE_TAGS = Maps.newHashMap();

    @SuppressWarnings("unchecked")
    private static void loadTags(Class<?> clazz) {
        Arrays.stream(clazz.getFields()).forEach(field -> {
            if (field.getType() == RegistryKey.class) {
                try {
                    RegistryKey<PlacedFeature> key = (RegistryKey<PlacedFeature>) field.get(null);
                    HAS_FEATURE_TAGS.put(key, TagKey.of(RegistryKeys.BIOME, new Identifier(CreoLib.COMMON, "has_" + field.getName().toLowerCase())));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void loadVanillaFeatureTags() {
        loadTags(OceanPlacedFeatures.class);
        loadTags(UndergroundPlacedFeatures.class);
        loadTags(EndPlacedFeatures.class);
        loadTags(MiscPlacedFeatures.class);
        loadTags(NetherPlacedFeatures.class);
        loadTags(OrePlacedFeatures.class);
        loadTags(TreePlacedFeatures.class);
        loadTags(VegetationPlacedFeatures.class);
        loadTags(VillagePlacedFeatures.class);
    }
}
