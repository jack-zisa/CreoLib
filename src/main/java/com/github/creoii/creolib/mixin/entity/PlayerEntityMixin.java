package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.mixin.item.ItemSettingsAccessor;
import com.github.creoii.creolib.util.registry.ItemRegistryHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;setPickupDelay(I)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_applyPickupDelays(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir, double d, ItemEntity itemEntity) {
        if (((ItemSettingsAccessor) stack.getItem()).getItemSettings() instanceof ItemRegistryHelper.CItemSettings settings) {
            itemEntity.setPickupDelay(settings.getPickupDelay());
        }
    }
}
