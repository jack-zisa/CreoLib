package com.github.creoii.creolib.api.world.placement;

import com.github.creoii.creolib.api.util.MathUtil;
import com.github.creoii.creolib.core.noise.FastNoiseLite;
import com.github.creoii.creolib.core.registry.FastNoiseParametersRegistry;
import com.github.creoii.creolib.core.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

/**
 * Before square placement
 */
public class FastNoisePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<FastNoisePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(FastNoiseParametersRegistry.REGISTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), MathUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new MathUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        }), Codec.BOOL.optionalFieldOf("3d", false).forGetter(predicate -> {
            return predicate.threeDimensional;
        })).apply(instance, FastNoisePlacementModifier::new);
    });
    private final RegistryEntry<FastNoiseLite> noise;
    private final List<MathUtil.Range> ranges;
    private final boolean threeDimensional;

    public FastNoisePlacementModifier(RegistryEntry<FastNoiseLite> noise, List<MathUtil.Range> ranges, boolean threeDimensional) {
        this.noise = noise;
        this.ranges = ranges;
        this.threeDimensional = threeDimensional;
    }

    public FastNoisePlacementModifier(RegistryEntry<FastNoiseLite> noise, List<MathUtil.Range> ranges) {
        this(noise, ranges, false);
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        double noiseValue = threeDimensional ? noise.value().GetNoise(pos.getX(), pos.getY(), pos.getZ()) : noise.value().GetNoise(pos.getX(), 0f, pos.getZ());
        for (MathUtil.Range range : ranges) {
            if (noiseValue >= range.min() && noiseValue < range.max()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.FAST_NOISE;
    }
}