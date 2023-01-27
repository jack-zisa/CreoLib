package com.github.creoii.creolib.entity.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class VexMoveControl extends MoveControl {
    private final MobEntity mob;

    public VexMoveControl(MobEntity mob) {
        super(mob);
        this.mob = mob;
    }

    public void tick() {
        if (state == State.MOVE_TO) {
            Vec3d vec3d = new Vec3d(targetX - mob.getX(), targetY - mob.getY(), targetZ - mob.getZ());
            double d = vec3d.length();
            if (d < mob.getBoundingBox().getAverageSideLength()) {
                state = State.WAIT;
                mob.setVelocity(mob.getVelocity().multiply(.5d));
            } else {
                mob.setVelocity(mob.getVelocity().add(vec3d.multiply(speed * .05d / d)));
                if (mob.getTarget() == null) {
                    Vec3d vec3d2 = mob.getVelocity();
                    mob.setYaw(-((float) MathHelper.atan2(vec3d2.x, vec3d2.z)) * 57.295776f);
                } else {
                    double e = mob.getTarget().getX() - mob.getX();
                    double f = mob.getTarget().getZ() - mob.getZ();
                    mob.setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776f);
                }
                mob.bodyYaw = mob.getYaw();
            }
        }
    }
}