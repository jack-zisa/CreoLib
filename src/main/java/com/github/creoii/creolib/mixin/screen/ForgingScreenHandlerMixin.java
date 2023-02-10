package com.github.creoii.creolib.mixin.screen;

import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ForgingScreenHandler.class)
public abstract class ForgingScreenHandlerMixin {
    //@ModifyConstant(method = "canUse(Lnet/minecraft/entity/player/PlayerEntity;)Z", constant = @Constant(doubleValue = 64d))
    //private static double creo_lib_reachAccessForgingScreens(double constant, PlayerEntity playerEntity) {
    //    return MathHelper.square(playerEntity.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
    //}
}
