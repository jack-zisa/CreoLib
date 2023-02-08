package com.github.creoii.creolib.impl.mixin.entity;

import com.github.creoii.creolib.api.tag.CEntityTypeTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZoglinEntity.class)
public class ZoglinEntityMixin {
    @Inject(method = "shouldAttack", at = @At("RETURN"), cancellable = true)
    private void creo_lib_zoglinIgnoreEntities(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType().isIn(CEntityTypeTags.ZOGLIN_IGNORES)) cir.setReturnValue(false);
    }
}
