package com.github.creoii.creolib.phase.transition;

import com.github.creoii.creolib.phase.PhaseHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TimedTransition extends Transition {
    /* Distance from zero to execute the transition. */
    private final int threshold;

    public TimedTransition(Type type, int threshold) {
        super(type);
        this.threshold = threshold;
    }

    @Override
    public boolean satisfy(World world, BlockPos pos, PhaseHolder holder, int duration) {
        return duration <= threshold;
    }
}
