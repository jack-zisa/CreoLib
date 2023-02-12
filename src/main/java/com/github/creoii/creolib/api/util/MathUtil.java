package com.github.creoii.creolib.api.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.random.Random;

public final class MathUtil {
    public static final Random RANDOM = Random.create();

    /**
     * Repeatedly calling this method on a value will randomly linearly interpolate it
     * between the min and max range
     *
     * @param bounds The min & max value to stay between
     * @param n The return value
     * @param r The lerp value
     * @return Randomly lerped value of n
     */
    public static float randomLerp(Range bounds, int n, int r) {
        n = RANDOM.nextBoolean() ? n + r : n - r;
        if (n < (float) bounds.min()) return (float) bounds.min();
        else if (n > (float) bounds.max()) return (float) bounds.max();
        return n;
    }

    /**
     * Same as {@link MathUtil#randomLerp(Range, int, int)} except bounces with a value of @param n when hitting the bounds
     */
    public static float bounceRandomLerp(Range bounds, int n, int r) {
        n = RANDOM.nextBoolean() ? n + r : n - r;
        if (n < (float) bounds.min()) return (float) bounds.min() + n;
        else if (n > (float) bounds.max()) return (float) bounds.max() + n;
        return n;
    }

    public static double pow(double a, double b) {
        for (int i = 1; i < b; ++i) {
            a *= a;
        }
        return a;
    }

    public record Range(double min, double max) {
        public static final Codec<Range> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(Codec.DOUBLE.fieldOf("min_inclusive").forGetter(range -> {
                return range.min;
            }), Codec.DOUBLE.fieldOf("max_exclusive").forGetter(range -> {
                return range.max;
            })).apply(instance, Range::new);
        });
    }
}
