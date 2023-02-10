package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.api.tag.CEntityTypeTags;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {
    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void creo_lib_scaresCreepersEntities(CallbackInfo ci) {
        goalSelector.add(3, new FleeEntityGoal<>(this, LivingEntity.class, 6f, 1d, 1.2d, livingEntity -> {
            return livingEntity.getType().isIn(CEntityTypeTags.SCARES_CREEPERS);
        }));
    }
}
