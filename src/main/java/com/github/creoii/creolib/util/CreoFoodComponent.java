package com.github.creoii.creolib.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;

import java.util.List;

public class CreoFoodComponent extends FoodComponent {
    private final int eatTime;

    public CreoFoodComponent(int hunger, float saturationModifier, boolean meat, boolean alwaysEdible, int eatTime, List<Pair<StatusEffectInstance, Float>> statusEffects) {
        super(hunger, saturationModifier, meat, alwaysEdible, false, statusEffects);
        this.eatTime = eatTime;
    }

    public int getEatTime() {
        return eatTime;
    }

    public static class Builder {
        private int hunger;
        private float saturationModifier;
        private boolean meat;
        private boolean alwaysEdible;
        private int eatTime;
        private final List<Pair<StatusEffectInstance, Float>> statusEffects = Lists.newArrayList();

        public CreoFoodComponent.Builder hunger(int hunger) {
            this.hunger = hunger;
            return this;
        }

        public CreoFoodComponent.Builder saturationModifier(float saturationModifier) {
            this.saturationModifier = saturationModifier;
            return this;
        }

        public CreoFoodComponent.Builder meat() {
            meat = true;
            return this;
        }

        public CreoFoodComponent.Builder alwaysEdible() {
            alwaysEdible = true;
            return this;
        }

        public CreoFoodComponent.Builder eatTime(int eatTime) {
            this.eatTime = eatTime;
            return this;
        }

        public CreoFoodComponent build() {
            return new CreoFoodComponent(hunger, saturationModifier, meat, alwaysEdible, eatTime, statusEffects);
        }
    }
}
