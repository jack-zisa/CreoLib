package com.github.creoii.creolib.phase.behavior;

import com.github.creoii.creolib.phase.PhaseHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class Behavior {
    public void initialize(World world, BlockPos pos, PhaseHolder holder) {}

    public abstract void tick(World world, BlockPos pos, PhaseHolder holder, int duration);
}
