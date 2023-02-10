package com.github.creoii.creolib.api.entity.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.MathHelper;

public class FishMoveControl extends MoveControl {
    private final MobEntity mob;

    public FishMoveControl(MobEntity mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void tick() {
        if (mob.isSubmergedIn(FluidTags.WATER)) {
            mob.setVelocity(mob.getVelocity().add(0d, .005d, 0d));
        }
        if (state != MoveControl.State.MOVE_TO || mob.getNavigation().isIdle()) {
            mob.setMovementSpeed(0f);
            return;
        }
        float f = (float)(speed * mob.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
        mob.setMovementSpeed(MathHelper.lerp(.125f, mob.getMovementSpeed(), f));
        double d = targetX - mob.getX();
        double e = targetY - mob.getY();
        double g = targetZ - mob.getZ();
        if (e != 0d) {
            double h = Math.sqrt(d * d + e * e + g * g);
            mob.setVelocity(mob.getVelocity().add(0d, mob.getMovementSpeed() * (e / h) * .1d, 0d));
        }
        if (d != 0d || g != 0d) {
            float i = (float)(MathHelper.atan2(g, d) * 57.2957763671875d) - 90f;
            mob.setYaw(wrapDegrees(mob.getYaw(), i, 90f));
            mob.bodyYaw = mob.getYaw();
        }
    }
}