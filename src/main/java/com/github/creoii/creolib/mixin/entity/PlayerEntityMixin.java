package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.api.util.registry.CItemSettings;
import com.github.creoii.creolib.core.duck.ItemSettingsDuck;
import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "createPlayerAttributes", at = @At("RETURN"), cancellable = true)
    private static void creo_lib_playerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(cir.getReturnValue().add(AttributeRegistry.PLAYER_REACH_DISTANCE).add(AttributeRegistry.PLAYER_BLOCK_PLACE_SPEED).add(AttributeRegistry.PLAYER_BLOCK_BREAK_SPEED));
    }

    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;setPickupDelay(I)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_applyPickupDelays(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir, double d, ItemEntity itemEntity) {
        if (((ItemSettingsDuck) stack.getItem()).getItemSettings() instanceof CItemSettings settings) {
            itemEntity.setPickupDelay(settings.getPickupDelay());
        }
    }

    @ModifyConstant(method = "attack(Lnet/minecraft/entity/Entity;)V", constant = @Constant(doubleValue = 9.0))
    private double creo_lib_attackRange(double attackRange) {
        return getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE);
    }
}
