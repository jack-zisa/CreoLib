package com.github.creoii.creolib.impl.mixin.entity;

import com.github.creoii.creolib.api.tag.CBlockTags;
import com.github.creoii.creolib.api.tag.CEntityTypeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin extends Entity {
    public ProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onCollision", at = @At(value = "HEAD"), cancellable = true)
    private void creo_lib_projectilesIgnore(HitResult hitResult, CallbackInfo ci) {
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            if (((EntityHitResult) hitResult).getEntity().getType().isIn(CEntityTypeTags.PROJECTILES_IGNORE)) ci.cancel();
        } else if (hitResult.getType() == HitResult.Type.BLOCK) {
            if (world.getBlockState(((BlockHitResult) hitResult).getBlockPos()).isIn(CBlockTags.PROJECTILES_IGNORE)) ci.cancel();
        }
    }
}
