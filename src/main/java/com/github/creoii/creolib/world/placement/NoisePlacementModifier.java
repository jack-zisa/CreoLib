package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementRegistry;
import com.github.creoii.creolib.util.WorldUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;


/**
 * Before square placement
 */
public class NoisePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NoisePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryKey.createCodec(RegistryKeys.NOISE_PARAMETERS).fieldOf("noise_parameters").forGetter(predicate -> {
            return predicate.noise;
        }), WorldUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new WorldUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        })).apply(instance, NoisePlacementModifier::new);
    });
    private final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise;
    private final List<WorldUtil.Range> ranges;

    private NoisePlacementModifier(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, List<WorldUtil.Range> ranges) {
        this.noise = noise;
        this.ranges = ranges;
    }

    public static NoisePlacementModifier of(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, List<WorldUtil.Range> ranges) {
        return new NoisePlacementModifier(noise, ranges);
    }

    @Override
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (context.getWorld().getChunkManager() instanceof ServerChunkManager chunkManager) {
            DoublePerlinNoiseSampler sampler = chunkManager.getNoiseConfig().getOrCreateSampler(noise);
            double noiseValue = sampler.sample(pos.getX(), pos.getY(), pos.getZ());
            for (WorldUtil.Range range : ranges) {
                if (noiseValue >= range.min() && noiseValue < range.max()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementRegistry.NOISE;
    }
}