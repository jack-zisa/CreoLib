package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.VehicleInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VehicleInventory.class)
public interface VehicleInventoryMixin {
    @ModifyConstant(method = "canPlayerAccess", constant = @Constant(doubleValue = 8d))
    private double creo_lib_reachAccessVehicles(double constant, PlayerEntity playerEntity) {
        return playerEntity.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE);
    }
}
