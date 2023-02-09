package com.github.creoii.creolib.mixin.block.entity;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {
    @ModifyConstant(method = "canPlayerUse", constant = @Constant(doubleValue = 64d))
    private double creo_lib_reachAccessBrewingStands(double constant, PlayerEntity playerEntity) {
        return MathHelper.square(playerEntity.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
    }
}
