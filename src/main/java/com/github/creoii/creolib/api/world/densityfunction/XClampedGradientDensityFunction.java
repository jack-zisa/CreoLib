package com.github.creoii.creolib.api.world.densityfunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public record XClampedGradientDensityFunction(int fromX, int toX, double fromValue, double toValue) implements DensityFunction.Base {
    private static final Codec<Double> CONSTANT_RANGE = Codec.doubleRange(-1000000d, 1000000d);
    public static final Codec<XClampedGradientDensityFunction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(DimensionType.MIN_HEIGHT * 2, DimensionType.MAX_COLUMN_HEIGHT * 2).fieldOf("from_x").forGetter(XClampedGradientDensityFunction::fromX),
            Codec.intRange(DimensionType.MIN_HEIGHT * 2, DimensionType.MAX_COLUMN_HEIGHT * 2).fieldOf("to_x").forGetter(XClampedGradientDensityFunction::toX),
            CONSTANT_RANGE.fieldOf("from_value").forGetter(XClampedGradientDensityFunction::fromValue),
            CONSTANT_RANGE.fieldOf("to_value").forGetter(XClampedGradientDensityFunction::toValue)
    ).apply(instance, XClampedGradientDensityFunction::new));
    public static final CodecHolder<XClampedGradientDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);

    @Override
    public double sample(DensityFunction.NoisePos pos) {
        return MathHelper.clampedMap(pos.blockX(), fromX, toX, fromValue, toValue);
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
