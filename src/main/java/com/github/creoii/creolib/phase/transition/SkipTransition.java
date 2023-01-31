package com.github.creoii.creolib.phase.transition;

import com.github.creoii.creolib.phase.PhaseHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SkipTransition extends Transition {
    public SkipTransition(Type type) {
        super(type);
    }

    @Override
    public boolean satisfy(World world, BlockPos pos, PhaseHolder holder, int duration) {
        return true;
    }
}
