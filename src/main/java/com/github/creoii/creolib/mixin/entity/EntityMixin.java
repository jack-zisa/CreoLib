package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.tag.CBlockTags;
import com.github.creoii.creolib.tag.CEntityTypeTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public boolean noClip;
    @Shadow public abstract BlockState getBlockStateAtPos();

    @Shadow public abstract EntityType<?> getType();

    @Inject(method = "tick", at = @At("HEAD"))
    private void creo_lib_blockNoClipTick(CallbackInfo ci) {
        if (getType().isIn(CEntityTypeTags.NO_CLIPPING_ENTITIES)) {
            noClip = !getBlockStateAtPos().isIn(CBlockTags.BLOCKS_NO_CLIPPING);
        }
    }

    @Inject(method = "move", at = @At("HEAD"))
    private void creo_lib_blockNoClipMove(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        if (getType().isIn(CEntityTypeTags.NO_CLIPPING_ENTITIES)) {
            noClip = !getBlockStateAtPos().isIn(CBlockTags.BLOCKS_NO_CLIPPING);
        }
    }

    @Inject(method = "isInsideWall", at = @At("HEAD"))
    private void creo_lib_blockNoClipInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if (getType().isIn(CEntityTypeTags.NO_CLIPPING_ENTITIES)) {
            noClip = !getBlockStateAtPos().isIn(CBlockTags.BLOCKS_NO_CLIPPING);
        }
    }
}
