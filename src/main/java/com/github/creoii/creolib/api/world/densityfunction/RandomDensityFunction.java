package com.github.creoii.creolib.api.world.densityfunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public class RandomDensityFunction implements DensityFunction {
    public static Codec<RandomDensityFunction> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("min").forGetter(function -> {
            return function.min;
        }), Codec.INT.fieldOf("max").forGetter(function -> {
            return function.max;
        })).apply(instance, RandomDensityFunction::new);
    });
    private static final Random RANDOM = Random.create();
    public static CodecHolder<RandomDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final int min;
    private final int max;

    public RandomDensityFunction(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double sample(NoisePos pos) {
        return RANDOM.nextBetween(min, max);
    }

    @Override
    public void applyEach(double[] densities, EachApplier applier) {
        applier.applyEach(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunctionVisitor visitor) {
        return visitor.apply(new RandomDensityFunction(min, max));
    }

    @Override
    public double minValue() {
        return min;
    }

    @Override
    public double maxValue() {
        return max;
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }
}
