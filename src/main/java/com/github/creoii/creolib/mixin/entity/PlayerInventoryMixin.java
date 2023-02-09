package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @ModifyConstant(method = "canPlayerUse", constant = @Constant(doubleValue = 64d))
    private double creo_lib_reachAccessPlayerInventory(double reach, PlayerEntity player) {
        return MathHelper.square(player.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
    }
}
