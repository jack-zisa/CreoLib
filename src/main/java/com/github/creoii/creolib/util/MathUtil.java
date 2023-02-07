package com.github.creoii.creolib.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.random.Random;

public class MathUtil {
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
    public float randomLerp(Range bounds, int n, int r) {
        n = RANDOM.nextBoolean() ? n + r : n - r;
        if (n < (float) bounds.min()) return (float) bounds.min();
        else if (n > (float) bounds.max()) return (float) bounds.max();
        return n;
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