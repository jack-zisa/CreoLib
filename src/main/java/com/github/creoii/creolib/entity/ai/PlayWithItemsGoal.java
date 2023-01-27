package com.github.creoii.creolib.entity.ai;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.function.Predicate;

public class PlayWithItemsGoal extends Goal {
    private final MobEntity mob;
    private final Predicate<ItemEntity> canTakePredicate;
    private int nextPlayingTime;

    public PlayWithItemsGoal(MobEntity mob, Predicate<ItemEntity> canTakePredicate) {
        this.mob = mob;
        this.canTakePredicate = canTakePredicate;
    }

    public boolean canStart() {
        if (nextPlayingTime > mob.age) {
            return false;
        } else {
            List<ItemEntity> list = mob.world.getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(8d, 8d, 8d), canTakePredicate);
            return !list.isEmpty() || !mob.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
        }
    }

    public void start() {
        List<ItemEntity> list = mob.world.getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(8d, 8d, 8d), canTakePredicate);
        if (!list.isEmpty()) {
            mob.getNavigation().startMovingTo(list.get(0), 1.2000000476837158d);
            mob.playSound(SoundEvents.ENTITY_DOLPHIN_PLAY, 1f, 1f);
        }
        nextPlayingTime = 0;
    }

    public void stop() {
        ItemStack itemStack = mob.getEquippedStack(EquipmentSlot.MAINHAND);
        if (!itemStack.isEmpty()) {
            spitOutItem(itemStack);
            mob.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            nextPlayingTime = mob.age + mob.getRandom().nextInt(100);
        }
    }

    public void tick() {
        List<ItemEntity> list = mob.world.getEntitiesByClass(ItemEntity.class, mob.getBoundingBox().expand(8d, 8d, 8d), canTakePredicate);
        ItemStack itemStack = mob.getEquippedStack(EquipmentSlot.MAINHAND);
        if (!itemStack.isEmpty()) {
            spitOutItem(itemStack);
            mob.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        } else if (!list.isEmpty()) {
            mob.getNavigation().startMovingTo(list.get(0), 1.2000000476837158d);
        }
    }

    private void spitOutItem(ItemStack stack) {
        if (!stack.isEmpty()) {
            double d = mob.getEyeY() - .30000001192092896d;
            ItemEntity itemEntity = new ItemEntity(mob.world, mob.getX(), d, mob.getZ(), stack);
            itemEntity.setPickupDelay(40);
            itemEntity.setThrower(mob.getUuid());
            float g = mob.getRandom().nextFloat() * 6.2831855f;
            float h = .02f * mob.getRandom().nextFloat();
            itemEntity.setVelocity((.3f * -MathHelper.sin(mob.getYaw() * .017453292f) * MathHelper.cos(mob.getPitch() * .017453292f) + MathHelper.cos(g) * h), (.3f * MathHelper.sin(mob.getPitch() * .017453292f) * 1.5f), (.3f * MathHelper.cos(mob.getYaw() * .017453292f) * MathHelper.cos(mob.getPitch() * .017453292f) + MathHelper.sin(g) * h));
            mob.world.spawnEntity(itemEntity);
        }
    }
}