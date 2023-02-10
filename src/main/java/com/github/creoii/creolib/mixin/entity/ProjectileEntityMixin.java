package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.api.tag.CBlockTags;
import com.github.creoii.creolib.api.tag.CEntityTypeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin extends Entity {
    public ProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onCollision", at = @At(value = "HEAD"), cancellable = true)
    private void creo_lib_projectilesIgnore(HitResult hitResult, CallbackInfo ci) {
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            if (world.getBlockState(((BlockHitResult) hitResult).getBlockPos()).isIn(CBlockTags.PROJECTILES_IGNORE)) ci.cancel();
        }
    }

    @Inject(method = "canHit", at = @At("HEAD"), cancellable = true)
    private void creo_lib_projectilesIgnoreEntities(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType().isIn(CEntityTypeTags.PROJECTILES_IGNORE)) cir.setReturnValue(false);
    }
}
