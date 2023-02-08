package com.github.creoii.creolib.api.entity.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class GhastMoveControl extends MoveControl {
    private final MobEntity mob;
    private int collisionCheckCooldown;

    public GhastMoveControl(MobEntity mob) {
        super(mob);
        this.mob = mob;
    }

    public void tick() {
        if (state == State.MOVE_TO) {
            if (collisionCheckCooldown-- <= 0) {
                collisionCheckCooldown += mob.getRandom().nextInt(5) + 2;
                Vec3d vec3d = new Vec3d(targetX - mob.getX(), targetY - mob.getY(), targetZ - mob.getZ());
                double d = vec3d.length();
                vec3d = vec3d.normalize();
                if (willCollide(vec3d, MathHelper.ceil(d))) {
                    mob.setVelocity(mob.getVelocity().add(vec3d.multiply(.1d)));
                } else {
                    state = State.WAIT;
                }
            }
        }
    }

    private boolean willCollide(Vec3d direction, int steps) {
        Box box = mob.getBoundingBox();
        for (int i = 1; i < steps; ++i) {
            box = box.offset(direction);
            if (!mob.world.isSpaceEmpty(mob, box)) {
                return false;
            }
        }
        return true;
    }
}