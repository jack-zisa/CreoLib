package com.github.creoii.creolib.api.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;

import java.util.List;

/**
 * Extension of {@link FoodComponent} allowing custom eating speeds
 * Any use of this class won't be considered a snack
 */
public class CFoodComponent extends FoodComponent {
    private final int eatTime;

    public CFoodComponent(int hunger, float saturationModifier, boolean meat, boolean alwaysEdible, int eatTime, List<Pair<StatusEffectInstance, Float>> statusEffects) {
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

        public CFoodComponent.Builder hunger(int hunger) {
            this.hunger = hunger;
            return this;
        }

        public CFoodComponent.Builder saturationModifier(float saturationModifier) {
            this.saturationModifier = saturationModifier;
            return this;
        }

        public CFoodComponent.Builder meat() {
            meat = true;
            return this;
        }

        public CFoodComponent.Builder alwaysEdible() {
            alwaysEdible = true;
            return this;
        }

        public CFoodComponent.Builder eatTime(int eatTime) {
            this.eatTime = eatTime;
            return this;
        }

        public CFoodComponent build() {
            return new CFoodComponent(hunger, saturationModifier, meat, alwaysEdible, eatTime, statusEffects);
        }
    }
}
