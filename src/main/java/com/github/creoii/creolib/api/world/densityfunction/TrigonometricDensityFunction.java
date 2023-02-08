package com.github.creoii.creolib.api.world.densityfunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public abstract class TrigonometricDensityFunction implements DensityFunction {
    protected final DensityFunction input;
    private final Type type;

    public TrigonometricDensityFunction(DensityFunction input, Type type) {
        this.input = input;
        this.type = type;
    }

    @Override
    public double sample(NoisePos pos) {
        double d = input.sample(pos);
        return switch (type) {
            case COS -> Math.cos(d);
            case SIN -> Math.sin(d);
            case TAN -> Math.tan(d);
        };
    }

    @Override
    public void applyEach(double[] densities, EachApplier applier) {
        input.applyEach(densities, applier);
        switch (this.type) {
            case SIN -> {
                for (int i = 0; i < densities.length; ++i) {
                    densities[i] = Math.sin(densities[i]);
                }
            }
            case COS -> {
                for (int i = 0; i < densities.length; ++i) {
                    densities[i] = Math.cos(densities[i]);
                }
            }
            case TAN -> {
                for (int i = 0; i < densities.length; ++i) {
                    densities[i] = Math.tan(densities[i]);
                }
            }
        }
    }

    @Override
    public double minValue() {
        return input.minValue();
    }

    @Override
    public double maxValue() {
        return input.maxValue();
    }

    public DensityFunction getInput() {
        return input;
    }

    public enum Type implements StringIdentifiable {
        SIN("sin"),
        COS("cos"),
        TAN("tan");

        public static final Codec<Type> CODEC = StringIdentifiable.createCodec(Type::values);
        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }
    }

    public static class Sin extends TrigonometricDensityFunction {
        public static final Codec<Sin> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(
                    DensityFunction.CODEC.fieldOf("input").forGetter(Sin::getInput)
            ).apply(instance, Sin::new);
        });
        public static final CodecHolder<Sin> CODEC_HOLDER = CodecHolder.of(CODEC);

        public Sin(DensityFunction input) {
            super(input, Type.SIN);
        }

        @Override
        public DensityFunction apply(DensityFunctionVisitor visitor) {
            return visitor.apply(new Sin(input));
        }

        @Override
        public CodecHolder<? extends DensityFunction> getCodecHolder() {
            return CODEC_HOLDER;
        }
    }

    public static class Cos extends TrigonometricDensityFunction {
        public static final Codec<Cos> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(
                    DensityFunction.CODEC.fieldOf("input").forGetter(Cos::getInput)
            ).apply(instance, Cos::new);
        });
        public static final CodecHolder<Cos> CODEC_HOLDER = CodecHolder.of(CODEC);

        public Cos(DensityFunction input) {
            super(input, Type.COS);
        }

        @Override
        public DensityFunction apply(DensityFunctionVisitor visitor) {
            return visitor.apply(new Cos(input));
        }

        @Override
        public CodecHolder<? extends DensityFunction> getCodecHolder() {
            return CODEC_HOLDER;
        }
    }

    public static class Tan extends TrigonometricDensityFunction {
        public static final Codec<Tan> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(
                    DensityFunction.CODEC.fieldOf("input").forGetter(Tan::getInput)
            ).apply(instance, Tan::new);
        });
        public static final CodecHolder<Tan> CODEC_HOLDER = CodecHolder.of(CODEC);

        public Tan(DensityFunction input) {
            super(input, Type.TAN);
        }

        @Override
        public DensityFunction apply(DensityFunctionVisitor visitor) {
            return visitor.apply(new Tan(input));
        }

        @Override
        public CodecHolder<? extends DensityFunction> getCodecHolder() {
            return CODEC_HOLDER;
        }
    }
}
