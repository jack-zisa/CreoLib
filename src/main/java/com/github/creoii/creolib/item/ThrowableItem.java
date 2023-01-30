package com.github.creoii.creolib.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThrowableItem extends Item {
    private final SoundEvent throwSound;
    private final EntityType<ThrownItemEntity> entityType;
    private final float speed;
    private final float divergence;

    private ThrowableItem(FabricItemSettings settings, SoundEvent throwSound, EntityType<ThrownItemEntity> entityType, float speed, float divergence) {
        super(settings);
        this.throwSound = throwSound;
        this.entityType = entityType;
        this.speed = speed;
        this.divergence = divergence;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack held = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), throwSound, SoundCategory.NEUTRAL, .5f, .4f / (world.getRandom().nextFloat() * .4f + .8f));
        if (!world.isClient) {
            ThrownItemEntity thrownItemEntity = entityType.create(world);
            if (thrownItemEntity != null) {
                thrownItemEntity.setItem(held);
                thrownItemEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, speed, divergence);
                world.spawnEntity(thrownItemEntity);
            } else return TypedActionResult.fail(held);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            held.decrement(1);
        }

        return TypedActionResult.success(held, world.isClient());
    }

    public static class Builder {
        private final FabricItemSettings settings;
        private SoundEvent throwSound;
        private EntityType<ThrownItemEntity> entityType;
        private float speed;
        private float divergence;

        public Builder(FabricItemSettings settings) {
            this.settings = settings;
        }

        public Builder throwSound(SoundEvent throwSound) {
            this.throwSound = throwSound;
            return this;
        }

        public Builder entityType(EntityType<ThrownItemEntity> entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder speed(float speed) {
            this.speed = speed;
            return this;
        }

        public Builder divergence(float divergence) {
            this.divergence = divergence;
            return this;
        }

        public ThrowableItem build() {
            return new ThrowableItem(settings, throwSound, entityType, speed, divergence);
        }
    }
}
