package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementRegistry;
import com.github.creoii.creolib.util.WorldUtil;
import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
        return instance.group(FastNoiseLite.CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), WorldUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new WorldUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        })).apply(instance, FastNoisePlacementModifier::new);
    });
    private final FastNoiseLite noise;
    private final List<WorldUtil.Range> ranges;

    private FastNoisePlacementModifier(FastNoiseLite noise, List<WorldUtil.Range> ranges) {
        this.noise = noise;
        this.ranges = ranges;
    }

    public static FastNoisePlacementModifier of(FastNoiseLite noise, List<WorldUtil.Range> ranges) {
        return new FastNoisePlacementModifier(noise, ranges);
    }

    @Override
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        double noiseValue = noise.GetNoise(pos.getX(), pos.getY(), pos.getZ());
        for (WorldUtil.Range range : ranges) {
            if (noiseValue >= range.min() && noiseValue < range.max()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementRegistry.FAST_NOISE;
    }
}