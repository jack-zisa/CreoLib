package com.github.creoii.creolib.api.entity.ai;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

class PickupItemGoal extends Goal {
    private final MobEntity mob;
    private final Predicate<MobEntity> wantsToPickupPredicate;
    private final Predicate<ItemEntity> pickableDropFilter;

    public PickupItemGoal(MobEntity mob, Predicate<MobEntity> wantsToPickupPredicate, Predicate<ItemEntity> pickableDropFilter) {
        this.mob = mob;
        this.wantsToPickupPredicate = wantsToPickupPredicate;
        this.pickableDropFilter = pickableDropFilter;
        setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        if (!mob.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
            return false;
        } else if (mob.getTarget() == null && mob.getAttacker() == null) {
            if (!wantsToPickupPredicate.test(mob)) {
                return false;
            } else if (mob.getRandom().nextInt(toGoalTicks(10)) != 0) {
                return false;
            } else {
                List<ItemEntity> list = mob.world.getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(8d, 8d, 8d), pickableDropFilter);
                return !list.isEmpty() && mob.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
            }
        } else {
            return false;
        }
    }

    public void tick() {
        List<ItemEntity> list = mob.world.getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(8d, 8d, 8d), pickableDropFilter);
        ItemStack itemStack = mob.getEquippedStack(EquipmentSlot.MAINHAND);
        if (itemStack.isEmpty() && !list.isEmpty()) {
            mob.getNavigation().startMovingTo(list.get(0), 1.2000000476837158d);
        }
    }

    public void start() {
        List<ItemEntity> list = mob.world.getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(8d, 8d, 8d), pickableDropFilter);
        if (!list.isEmpty()) {
            mob.getNavigation().startMovingTo(list.get(0), 1.2000000476837158d);
        }
    }
}