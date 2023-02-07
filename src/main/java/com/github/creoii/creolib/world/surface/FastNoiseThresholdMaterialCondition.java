package com.github.creoii.creolib.world.surface;

import com.github.creoii.creolib.registry.FastNoiseParametersRegistry;
import com.github.creoii.creolib.util.MathUtil;
import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.List;

public class FastNoiseThresholdMaterialCondition implements MaterialRules.MaterialCondition {
    public static final MapCodec<FastNoiseThresholdMaterialCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(
                FastNoiseParametersRegistry.REGISTRY_CODEC.fieldOf("noise").forGetter(FastNoiseThresholdMaterialCondition::getNoise),
                MathUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new MathUtil.Range(-1d, 1d))).forGetter(FastNoiseThresholdMaterialCondition::getRanges),
                Codec.BOOL.optionalFieldOf("3d", false).forGetter(FastNoiseThresholdMaterialCondition::is3d)
        ).apply(instance, FastNoiseThresholdMaterialCondition::new);
    });
    public static final CodecHolder<FastNoiseThresholdMaterialCondition> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final RegistryEntry<FastNoiseLite> noise;
    private final List<MathUtil.Range> ranges;
    private final boolean threeDimensional;

    public FastNoiseThresholdMaterialCondition(RegistryEntry<FastNoiseLite> noise, List<MathUtil.Range> ranges, boolean threeDimensional) {
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
            double noiseValue = threeDimensional ? noise.value().GetNoise(materialRuleContext.blockX, materialRuleContext.blockY, materialRuleContext.blockX) : noise.value().GetNoise(materialRuleContext.blockX, 0, materialRuleContext.blockX);
            for (MathUtil.Range range : ranges) {
                if (noiseValue >= range.min() && noiseValue < range.max()) {
                    return true;
                }
            }
            return false;
        };
    }

    public RegistryEntry<FastNoiseLite> getNoise() {
        return noise;
    }

    public List<MathUtil.Range> getRanges() {
        return ranges;
    }

    public boolean is3d() { return threeDimensional; }
}
