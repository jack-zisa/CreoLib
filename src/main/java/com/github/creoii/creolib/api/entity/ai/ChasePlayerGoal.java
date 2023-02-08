package com.github.creoii.creolib.api.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ChasePlayerGoal extends Goal {
    private final MobEntity mob;
    private final TargetPredicate targetPredicate;
    @Nullable private LivingEntity target;

    public ChasePlayerGoal(MobEntity mob, TargetPredicate targetPredicate) {
        this.mob = mob;
        this.targetPredicate = targetPredicate;
        setControls(EnumSet.of(Control.JUMP, Control.MOVE));
    }

    public boolean canStart() {
        target = mob.getTarget();
        if (!targetPredicate.test(mob, target)) {
            return false;
        } else {
            double d = target.squaredDistanceTo(mob);
            return !(d > 256d);
        }
    }

    public void start() {
        mob.getNavigation().stop();
    }

    public void tick() {
        mob.getLookControl().lookAt(target.getX(), target.getEyeY(), target.getZ());
    }
}