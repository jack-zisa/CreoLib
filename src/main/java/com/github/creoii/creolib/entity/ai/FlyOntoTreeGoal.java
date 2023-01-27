package com.github.creoii.creolib.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class FlyOntoTreeGoal extends FlyGoal {
    private final BlockStatePredicate blockStatePredicate;

    public FlyOntoTreeGoal(PathAwareEntity pathAwareEntity, double speed, BlockStatePredicate blockStatePredicate) {
        super(pathAwareEntity, speed);
        this.blockStatePredicate = blockStatePredicate;
    }

    @Nullable
    protected Vec3d getWanderTarget() {
        Vec3d vec3d = null;
        if (mob.isTouchingWater()) {
            vec3d = FuzzyTargeting.find(mob, 15, 15);
        }

        if (mob.getRandom().nextFloat() >= probability) {
            vec3d = locateTree();
        }

        return vec3d == null ? super.getWanderTarget() : vec3d;
    }

    @Nullable
    private Vec3d locateTree() {
        BlockPos blockPos = mob.getBlockPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable mutable2 = new BlockPos.Mutable();
        Iterable<BlockPos> iterable = BlockPos.iterate(MathHelper.floor(mob.getX() - 3d), MathHelper.floor(mob.getY() - 6d), MathHelper.floor(mob.getZ() - 3d), MathHelper.floor(mob.getX() + 3d), MathHelper.floor(mob.getY() + 6d), MathHelper.floor(mob.getZ() + 3d));
        Iterator<BlockPos> iterator = iterable.iterator();

        BlockPos blockPos2;
        boolean bl;
        do {
            do {
                if (!iterator.hasNext()) {
                    return null;
                }
                blockPos2 = iterator.next();
            } while(blockPos.equals(blockPos2));
            BlockState blockState = mob.world.getBlockState(mutable2.set(blockPos2, Direction.DOWN));
            bl = blockStatePredicate.test(blockState);
        } while(!bl || !mob.world.isAir(blockPos2) || !mob.world.isAir(mutable.set(blockPos2, Direction.UP)));

        return Vec3d.ofBottomCenter(blockPos2);
    }
}