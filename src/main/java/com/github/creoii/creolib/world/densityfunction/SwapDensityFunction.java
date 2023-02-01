package com.github.creoii.creolib.world.densityfunction;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public record SwapDensityFunction(DensityFunction input, SwapType swapType) implements DensityFunction {
    public static final MapCodec<SwapDensityFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(
                DensityFunction.CODEC.fieldOf("input").forGetter(SwapDensityFunction::input),
                SwapType.CODEC.fieldOf("swap_type").forGetter(SwapDensityFunction::swapType)
        ).apply(instance, SwapDensityFunction::new);
    });
    public static final CodecHolder<SwapDensityFunction> CODEC_HOLDER = CodecHolder.of(CODEC);

    @Override
    public double sample(NoisePos pos) {
        int x = 0;
        int y = 0;
        int z = 0;
        switch (swapType) {
            case X_AND_Y -> {
                x = pos.blockY();
                y = pos.blockX();
                z = pos.blockZ();
            }
            case X_AND_Z -> {
                x = pos.blockZ();
                y = pos.blockY();
                z = pos.blockX();
            }
            case Z_AND_Y -> {
                x = pos.blockX();
                y = pos.blockZ();
                z = pos.blockY();
            }
        }
        return input.sample(new UnblendedNoisePos(x, y, z));
    }

    @Override
    public void applyEach(double[] densities, EachApplier applier) {
        applier.applyEach(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunctionVisitor visitor) {
        return visitor.apply(new SwapDensityFunction(input, swapType));
    }

    @Override
    public double minValue() {
        return -maxValue();
    }

    @Override
    public double maxValue() {
        return input.maxValue();
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }

    public enum SwapType implements StringIdentifiable {
        X_AND_Y("x_and_y"),
        X_AND_Z("x_and_z"),
        Z_AND_Y("z_and_y");

        public static final Codec<SwapType> CODEC = StringIdentifiable.createCodec(SwapType::values);
        private final String name;

        SwapType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }
    }
}
