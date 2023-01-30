package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.enchantment.EquippableEnchantment;
import com.github.creoii.creolib.registry.AttributeRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract double getAttributeValue(EntityAttribute attribute);
    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);
    @Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);
    @Shadow protected abstract int computeFallDamage(float fallDistance, float damageMultiplier);
    @Shadow public abstract Vec3d applyFluidMovingSpeed(double gravity, boolean falling, Vec3d motion);

    private static final EntityAttributeModifier SLOW_FALLING = new EntityAttributeModifier(UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA"), "Slow falling acceleration reduction", -0.07d, EntityAttributeModifier.Operation.ADDITION);
    private static EntityAttributeInstance gravity;

    private LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void creo_lib_createNewAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(AttributeRegistry.GENERIC_GRAVITY);
    }

    @Inject(method = "knockDownwards", at = @At("HEAD"), cancellable = true)
    private void creo_lib_applyKnockbackSwimSpeed(CallbackInfo ci) {
        setVelocity(getVelocity().add(0d, -.03999999910593033d * getAttributeValue(AttributeRegistry.GENERIC_GRAVITY), 0d));
        ci.cancel();
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyFluidMovingSpeed(DZLnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d creo_lib_applyGravity(LivingEntity entity, double d, boolean bl, Vec3d vec3d) {
        return applyFluidMovingSpeed(entity.getAttributeValue(AttributeRegistry.GENERIC_GRAVITY), bl, vec3d);
    }

    @ModifyVariable(method = "travel", at = @At("STORE"), ordinal = 0)
    private double creo_lib_modifyGravityVariable(double d) {
        gravity = getAttributeInstance(AttributeRegistry.GENERIC_GRAVITY);
        if (hasStatusEffect(StatusEffects.SLOW_FALLING) && !gravity.hasModifier(SLOW_FALLING)) gravity.addTemporaryModifier(SLOW_FALLING);
        return gravity.getValue();
    }

    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;", shift = At.Shift.BEFORE))
    private void creo_lib_removeSlowFallingModifier(Vec3d movementInput, CallbackInfo ci) {
        if (gravity.hasModifier(SLOW_FALLING)) gravity.removeModifier(SLOW_FALLING);
    }

    @Redirect(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;computeFallDamage(FF)I"))
    private int creo_lib_handleFallDamageGravity(LivingEntity entity, float fallDistance, float damageMultiplier) {
        return computeFallDamage(fallDistance * (float) (entity.getAttributeValue(AttributeRegistry.GENERIC_GRAVITY) * 12.5f), damageMultiplier);
    }

    @Inject(method = "onEquipStack", at = @At("TAIL"))
    private void creo_caves_applyGravityEnchantments(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        Map<Enchantment, Integer> oldSet = EnchantmentHelper.get(oldStack);
        oldSet.keySet().forEach(enchantment -> {
            if (enchantment instanceof EquippableEnchantment equippableEnchantment) {
                equippableEnchantment.onUnequip(world, this, oldStack, slot, oldSet.get(enchantment));
            }
        });
        Map<Enchantment, Integer> newSet = EnchantmentHelper.get(newStack);
        newSet.keySet().forEach(enchantment -> {
            if (enchantment instanceof EquippableEnchantment equippableEnchantment) {
                equippableEnchantment.onEquip(world, this, oldStack, slot, newSet.get(enchantment));
            }
        });
    }
}