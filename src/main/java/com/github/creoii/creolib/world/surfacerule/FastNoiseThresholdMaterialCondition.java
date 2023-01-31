package com.github.creoii.creolib.world.surfacerule;

import com.github.creoii.creolib.util.WorldUtil;
import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.List;

public class FastNoiseThresholdMaterialCondition implements MaterialRules.MaterialCondition {
    public static final MapCodec<FastNoiseThresholdMaterialCondition> NOISE_CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(
                FastNoiseLite.CODEC.fieldOf("noise").forGetter(FastNoiseThresholdMaterialCondition::getNoise),
                WorldUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new WorldUtil.Range(-1d, 1d))).forGetter(FastNoiseThresholdMaterialCondition::getRanges),
                Codec.BOOL.optionalFieldOf("3d", false).forGetter(FastNoiseThresholdMaterialCondition::is3d)
        ).apply(instance, FastNoiseThresholdMaterialCondition::new);
    });
    public static final CodecHolder<FastNoiseThresholdMaterialCondition> CODEC_HOLDER = CodecHolder.of(NOISE_CODEC);
    private final FastNoiseLite noise;
    private final List<WorldUtil.Range> ranges;
    private final boolean threeDimensional;

    public FastNoiseThresholdMaterialCondition(FastNoiseLite noise, List<WorldUtil.Range> ranges, boolean threeDimensional) {
        this.noise = noise;
        this.ranges = ranges;
        this.threeDimensional = threeDimensional;
    }

    @Override
    public CodecHolder<? extends MaterialRules.MaterialCondition> codec() {
        return CODEC_HOLDER;
    }

    @Override
    public MaterialRules.BooleanSupplier apply(final MaterialRules.MaterialRuleContext materialRuleContext) {
        return () -> {
            double noiseValue = threeDimensional ? noise.GetNoise(materialRuleContext.blockX, materialRuleContext.blockY, materialRuleContext.blockX) : noise.GetNoise(materialRuleContext.blockX, 0, materialRuleContext.blockX);
            for (WorldUtil.Range range : ranges) {
                if (noiseValue >= range.min() && noiseValue < range.max()) {
                    return true;
                }
            }
            return false;
        };
    }

    public FastNoiseLite getNoise() {
        return noise;
    }

    public List<WorldUtil.Range> getRanges() {
        return ranges;
    }

    public boolean is3d() { return threeDimensional; }
}
