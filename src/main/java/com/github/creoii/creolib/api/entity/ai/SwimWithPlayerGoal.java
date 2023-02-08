package com.github.creoii.creolib.api.entity.ai;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SwimWithPlayerGoal extends Goal {
    private final MobEntity mob;
    private final double speed;
    private final TargetPredicate closePlayerPredicate;
    private final boolean dolphinsGrace;
    @Nullable private PlayerEntity closestPlayer;

    public SwimWithPlayerGoal(MobEntity mob, double speed, TargetPredicate closePlayerPredicate, boolean dolphinsGrace) {
        this.mob = mob;
        this.speed = speed;
        this.closePlayerPredicate = closePlayerPredicate;
        this.dolphinsGrace = dolphinsGrace;
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    public boolean canStart() {
        closestPlayer = mob.world.getClosestPlayer(closePlayerPredicate, mob);
        if (closestPlayer == null) {
            return false;
        } else {
            return closestPlayer.isSwimming() && mob.getTarget() != closestPlayer;
        }
    }

    public boolean shouldContinue() {
        return closestPlayer != null && closestPlayer.isSwimming() && mob.squaredDistanceTo(closestPlayer) < 256d;
    }

    public void start() {
        if (dolphinsGrace) closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 100), mob);
    }

    public void stop() {
        closestPlayer = null;
        mob.getNavigation().stop();
    }

    public void tick() {
        mob.getLookControl().lookAt(closestPlayer, (float)(mob.getMaxHeadRotation() + 20), (float)mob.getMaxLookPitchChange());
        if (mob.squaredDistanceTo(closestPlayer) < 6.25d) {
            mob.getNavigation().stop();
        } else {
            mob.getNavigation().startMovingTo(closestPlayer, speed);
        }

        if (dolphinsGrace && closestPlayer.isSwimming() && closestPlayer.world.random.nextInt(6) == 0) {
            closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 100), mob);
        }
    }
}