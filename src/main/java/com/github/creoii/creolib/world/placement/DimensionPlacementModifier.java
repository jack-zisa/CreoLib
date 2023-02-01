package com.github.creoii.creolib.world.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

/**
 * First placement modifier in list
 */
public class DimensionPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<DimensionPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(DimensionType.REGISTRY_CODEC.fieldOf("dimension_type").forGetter(predicate -> {
            return predicate.dimensionType;
        })).apply(instance, DimensionPlacementModifier::new);
    });
    public final RegistryEntry<DimensionType> dimensionType;

    public DimensionPlacementModifier(RegistryEntry<DimensionType> dimensionType) {
        this.dimensionType = dimensionType;
    }

    @Override
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        return context.getWorld().getDimension() == dimensionType.value();
    }

    @Override
    public PlacementModifierType<?> getType() {
        return null;
    }
}
