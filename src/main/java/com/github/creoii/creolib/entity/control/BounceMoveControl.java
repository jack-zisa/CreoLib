package com.github.creoii.creolib.entity.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

public class BounceMoveControl extends MoveControl {
    private float targetYaw;
    private int ticksUntilJump;
    private final MobEntity mob;
    private final int jumpInterval;
    @Nullable private final SoundEvent jumpSound;
    private boolean jumpOften;

    public BounceMoveControl(MobEntity mob, int jumpInterval, @Nullable SoundEvent jumpSound) {
        super(mob);
        this.mob = mob;
        this.jumpInterval = jumpInterval;
        this.jumpSound = jumpSound;
        targetYaw = 180f * mob.getYaw() / 3.1415927f;
    }

    public void look(float targetYaw, boolean jumpOften) {
        this.targetYaw = targetYaw;
        this.jumpOften = jumpOften;
    }

    public void move(double speed) {
        this.speed = speed;
        state = State.MOVE_TO;
    }

    public void tick() {
        entity.setYaw(wrapDegrees(entity.getYaw(), targetYaw, 90f));
        entity.headYaw = entity.getYaw();
        entity.bodyYaw = entity.getYaw();
        if (state != State.MOVE_TO) {
            entity.setForwardSpeed(0f);
        } else {
            state = State.WAIT;
            if (entity.isOnGround()) {
                entity.setMovementSpeed((float)(speed * entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                if (ticksUntilJump-- <= 0) {
                    ticksUntilJump = jumpInterval;
                    if (jumpOften) {
                        ticksUntilJump /= 3;
                    }

                    mob.getJumpControl().setActive();
                    if (jumpSound != null)
                        mob.playSound(jumpSound, 1f, 1f);
                } else {
                    mob.sidewaysSpeed = 0f;
                    mob.forwardSpeed = 0f;
                    entity.setMovementSpeed(0f);
                }
            } else {
                entity.setMovementSpeed((float)(speed * entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
            }
        }
    }
}