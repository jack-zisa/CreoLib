package com.github.creoii.creolib.api.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.AnimalEntity;

import java.util.List;
import java.util.function.Predicate;

public class ProtectBabiesGoal extends ActiveTargetGoal<LivingEntity> {
    private final AnimalEntity animal;

    public ProtectBabiesGoal(AnimalEntity animal, Predicate<LivingEntity> predicate) {
        super(animal, LivingEntity.class, 20, true, true, predicate);
        this.animal = animal;
    }

    public boolean canStart() {
        if (!animal.isBaby()) {
            if (super.canStart()) {
                List<AnimalEntity> list = animal.world.getNonSpectatingEntities(AnimalEntity.class, animal.getBoundingBox().expand(8d, 4d, 8d));
                for (AnimalEntity animalEntity : list) {
                    if (animalEntity.isBaby()) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    protected double getFollowRange() {
        return super.getFollowRange() * .5d;
    }
}