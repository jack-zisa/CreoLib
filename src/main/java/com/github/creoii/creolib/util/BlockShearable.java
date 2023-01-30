package com.github.creoii.creolib.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public interface BlockShearable {
    boolean isShearable(World world, BlockState state, BlockPos pos, PlayerEntity player, Random random, Direction side);

    ActionResult onShear(World world, BlockState state, BlockPos pos, PlayerEntity player, Random random, Direction side);
}
