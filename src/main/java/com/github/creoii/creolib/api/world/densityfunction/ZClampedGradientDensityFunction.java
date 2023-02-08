package com.github.creoii.creolib.api.world.densityfunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public record ZClampedGradientDensityFunction(int fromZ, int toZ, double fromValue, double toValue) implements DensityFunction.Base {
    private static final Codec<Double> CONSTANT_RANGE = Codec.doubleRange(-1000000d, 1000000d);
    public static final Codec<ZClampedGradientDensityFunction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(DimensionType.MIN_HEIGHT * 2, DimensionType.MAX_COLUMN_HEIGHT * 2).fieldOf("from_z").forGetter(ZClampedGradientDensityFunction::fromZ),
            Codec.intRange(DimensionType.MIN_HEIGHT * 2, DimensionType.MAX_COLUMN_HEIGHT * 2).fieldOf("to_z").forGetter(ZClampedGradientDensityFunction::toZ),
            CONSTANT_RANGE.fieldOf("from_value").forGetter(ZClampedGradientDensityFunction::fromValue),
            CONSTANT_RANGE.fieldOf("to_value").forGetter(ZClampedGradientDensityFunction::toValue)
    ).apply(instance, ZClampedGradientDensityFunction::new));
    public static final CodecHolder<ZClampedGradientDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);

    @Override
    public double sample(NoisePos pos) {
        return MathHelper.clampedMap(pos.blockZ(), fromZ, toZ, fromValue, toValue);
    }

    @Override
    public double minValue() {
        return Math.min(fromValue, toValue);
    }

    @Override
    public double maxValue() {
        return Math.max(fromValue, toValue);
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }
}
