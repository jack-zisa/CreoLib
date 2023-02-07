package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementModifierRegistry;
import com.github.creoii.creolib.util.MathUtil;
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
        }), MathUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new MathUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        }), Codec.BOOL.optionalFieldOf("3d", false).forGetter(predicate -> {
            return predicate.threeDimensional;
        })).apply(instance, NoisePlacementModifier::new);
    });
    private final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise;
    private final List<MathUtil.Range> ranges;
    private final boolean threeDimensional;

    public NoisePlacementModifier(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, List<MathUtil.Range> ranges, boolean threeDimensional) {
        this.noise = noise;
        this.ranges = ranges;
        this.threeDimensional = threeDimensional;
    }

    public NoisePlacementModifier(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, List<MathUtil.Range> ranges) {
        this(noise, ranges, false);
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (context.getWorld().getChunkManager() instanceof ServerChunkManager chunkManager) {
            DoublePerlinNoiseSampler sampler = chunkManager.getNoiseConfig().getOrCreateSampler(noise);
            double noiseValue = threeDimensional ? sampler.sample(pos.getX(), pos.getY(), pos.getZ()) : sampler.sample(pos.getX(), 0d, pos.getZ());
            for (MathUtil.Range range : ranges) {
                if (noiseValue >= range.min() && noiseValue < range.max()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.NOISE;
    }
}