package com.github.creoii.creolib.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;

public abstract class CastSpellGoal extends Goal {
    private final SoundEvent castSpellSound;
    private final MobEntity mob;
    protected int spellCooldown;
    protected int startTime;
    protected int spellTicks;

    protected CastSpellGoal(MobEntity mob, SoundEvent castSpellSound) {
        this.mob = mob;
        this.castSpellSound = castSpellSound;
    }

    public MobEntity getMob() {
        return mob;
    }

    public boolean canStart() {
        LivingEntity target = mob.getTarget();
        if (target != null && target.isAlive()) {
            if (spellTicks > 0) {
                return false;
            } else {
                return mob.age >= startTime;
            }
        } else {
            return false;
        }
    }

    public boolean shouldContinue() {
        LivingEntity target = mob.getTarget();
        return target != null && target.isAlive() && spellCooldown > 0;
    }

    public void start() {
        spellCooldown = getTickCount(getInitialCooldown());
        spellTicks = getSpellTicks();
        startTime = mob.age + startTimeDelay();
    }

    public void tick() {
        if (spellTicks > 0) {
            --spellTicks;
        }
        --spellCooldown;
        if (spellCooldown == 0) {
            castSpell();
            mob.playSound(castSpellSound, 1f, 1f);
        }
    }

    protected abstract void castSpell();

    protected int getInitialCooldown() {
        return 20;
    }

    protected abstract int getSpellTicks();

    protected abstract int startTimeDelay();
}