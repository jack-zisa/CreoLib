package com.github.creoii.creolib.world.densityfunction;

import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public class FastNoiseDensityFunction implements DensityFunction {
    public static final MapCodec<FastNoiseDensityFunction> NOISE_CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(
                (FastNoiseLite.CODEC.fieldOf("noise")).forGetter(FastNoiseDensityFunction::getNoise),
                (Codec.DOUBLE.fieldOf("x_scale")).forGetter(FastNoiseDensityFunction::getXScale),
                (Codec.DOUBLE.fieldOf("y_scale")).forGetter(FastNoiseDensityFunction::getYScale),
                (Codec.DOUBLE.fieldOf("z_scale")).forGetter(FastNoiseDensityFunction::getZScale)
        ).apply(instance, FastNoiseDensityFunction::new);
    });
    public static final CodecHolder<FastNoiseDensityFunction> CODEC_HOLDER = CodecHolder.of(NOISE_CODEC);

    private final FastNoiseLite noise;
    private final double xScale;
    private final double yScale;
    private final double zScale;

    public FastNoiseDensityFunction(FastNoiseLite noise, double xScale, double yScale, double zScale) {
        this.noise = noise;
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
    }

    @Override
    public double sample(DensityFunction.NoisePos pos) {
        return noise.GetNoise((float) (pos.blockX() * xScale), (float) (pos.blockY() * yScale), (float) (pos.blockZ() * zScale));
    }

    @Override
    public void applyEach(double[] densities, DensityFunction.EachApplier applier) {
        applier.applyEach(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunction.DensityFunctionVisitor visitor) {
        return visitor.apply(new FastNoiseDensityFunction(noise, xScale, yScale, zScale));
    }

    @Override
    public double minValue() {
        return -1d;
    }

    @Override
    public double maxValue() {
        return 1d;
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }

    public FastNoiseLite getNoise() {
        return noise;
    }

    public double getXScale() {
        return xScale;
    }

    public double getYScale() {
        return yScale;
    }

    public double getZScale() {
        return zScale;
    }
}
