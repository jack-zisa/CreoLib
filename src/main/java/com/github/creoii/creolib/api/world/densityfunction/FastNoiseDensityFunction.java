package com.github.creoii.creolib.api.world.densityfunction;

import com.github.creoii.creolib.core.registry.FastNoiseParametersRegistry;
import com.github.creoii.creolib.core.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public class FastNoiseDensityFunction implements DensityFunction {
    public static final Codec<FastNoiseDensityFunction> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                FastNoiseParametersRegistry.REGISTRY_CODEC.fieldOf("noise").forGetter(FastNoiseDensityFunction::getNoise),
                Codec.DOUBLE.fieldOf("x_scale").forGetter(FastNoiseDensityFunction::getXScale),
                Codec.DOUBLE.fieldOf("y_scale").forGetter(FastNoiseDensityFunction::getYScale),
                Codec.DOUBLE.fieldOf("z_scale").forGetter(FastNoiseDensityFunction::getZScale),
                Codec.BOOL.optionalFieldOf("3d", false).forGetter(FastNoiseDensityFunction::is3d)
        ).apply(instance, FastNoiseDensityFunction::new);
    });
    public static final CodecHolder<FastNoiseDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);
    private final RegistryEntry<FastNoiseLite> noise;
    private final double xScale;
    private final double yScale;
    private final double zScale;
    private final boolean threeDimensional;

    public FastNoiseDensityFunction(RegistryEntry<FastNoiseLite> noise, double xScale, double yScale, double zScale, boolean threeDimensional) {
        this.noise = noise;
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
        this.threeDimensional = threeDimensional;
    }

    public FastNoiseDensityFunction(RegistryEntry<FastNoiseLite> noise, double xScale, double yScale, double zScale) {
        this(noise, xScale, yScale, zScale, false);
    }

    @Override
    public double sample(DensityFunction.NoisePos pos) {
        return noise.value().GetNoise((float) (pos.blockX() * xScale), (float) (pos.blockY() * yScale), (float) (pos.blockZ() * zScale));
    }

    @Override
    public void applyEach(double[] densities, DensityFunction.EachApplier applier) {
        applier.applyEach(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunction.DensityFunctionVisitor visitor) {
        return visitor.apply(new FastNoiseDensityFunction(noise, xScale, yScale, zScale, threeDimensional));
    }

    @Override
    public double minValue() {
        return -2d;
    }

    @Override
    public double maxValue() {
        return 2d;
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }

    public RegistryEntry<FastNoiseLite> getNoise() {
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

    public boolean is3d() {
        return threeDimensional;
    }
}
