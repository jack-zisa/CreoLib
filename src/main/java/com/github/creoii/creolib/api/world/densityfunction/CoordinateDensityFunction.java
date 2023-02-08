package com.github.creoii.creolib.api.world.densityfunction;

import com.mojang.serialization.Codec;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

import java.util.function.Function;

public abstract class CoordinateDensityFunction implements DensityFunction {
    public Function<NoisePos, Double> sampleFunction;

    protected CoordinateDensityFunction(Function<NoisePos, Double> sampleFunction) {
        this.sampleFunction = sampleFunction;
    }

    @Override
    public double sample(NoisePos pos) {
        return sampleFunction.apply(pos);
    }

    @Override
    public void applyEach(double[] densities, EachApplier applier) {
        applier.applyEach(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunctionVisitor visitor) {
        return visitor.apply(this);
    }

    @Override
    public double minValue() {
        return -maxValue();
    }

    @Override
    public double maxValue() {
        return 1000000d;
    }

    public static class GetX extends CoordinateDensityFunction {
        private static final GetX INSTANCE = new GetX();
        public static Codec<GetX> CODEC = Codec.unit(() -> INSTANCE);
        public static CodecHolder<GetX> CODEC_HOLDER = CodecHolder.of(CODEC);

        public GetX() {
            super(noisePos -> (double) noisePos.blockX());
        }

        @Override
        public CodecHolder<? extends DensityFunction> getCodecHolder() {
            return CODEC_HOLDER;
        }
    }

    public static class GetY extends CoordinateDensityFunction {
        private static final GetY INSTANCE = new GetY();
        public static Codec<GetY> CODEC = Codec.unit(() -> INSTANCE);
        public static CodecHolder<GetY> CODEC_HOLDER = CodecHolder.of(CODEC);

        public GetY() {
            super(noisePos -> (double) noisePos.blockY());
        }

        @Override
        public CodecHolder<? extends DensityFunction> getCodecHolder() {
            return CODEC_HOLDER;
        }
    }

    public static class GetZ extends CoordinateDensityFunction {
        private static final GetZ INSTANCE = new GetZ();
        public static Codec<GetZ> CODEC = Codec.unit(() -> INSTANCE);
        public static CodecHolder<GetZ> CODEC_HOLDER = CodecHolder.of(CODEC);

        public GetZ() {
            super(noisePos -> (double) noisePos.blockZ());
        }

        @Override
        public CodecHolder<? extends DensityFunction> getCodecHolder() {
            return CODEC_HOLDER;
        }
    }
}
