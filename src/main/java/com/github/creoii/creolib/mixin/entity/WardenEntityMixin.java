package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.api.tag.CEntityTypeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WardenEntity.class)
public class WardenEntityMixin {
    @Inject(method = "isValidTarget", at = @At("RETURN"), cancellable = true)
    private void creo_lib_wardenIgnores(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType().isIn(CEntityTypeTags.WARDEN_IGNORES)) cir.setReturnValue(false);
    }
}
