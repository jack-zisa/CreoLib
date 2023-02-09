package com.github.creoii.creolib.mixin.screen;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ForgingScreenHandler.class)
public abstract class ForgingScreenHandlerMixin {
    //@ModifyConstant(method = "canUse(Lnet/minecraft/entity/player/PlayerEntity;)Z", constant = @Constant(doubleValue = 64d))
    //private static double creo_lib_reachAccessForgingScreens(double constant, PlayerEntity playerEntity) {
    //    return MathHelper.square(playerEntity.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
    //}
}
