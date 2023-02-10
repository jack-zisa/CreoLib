package com.github.creoii.creolib.mixin.screen;

import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {
    //@ModifyConstant(method = "canUse(Lnet/minecraft/screen/ScreenHandlerContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/Block;)Z", constant = @Constant(doubleValue = 64d))
    //private static double creo_lib_reachAccessScreens(double constant, ScreenHandlerContext context, PlayerEntity playerEntity, Block block) {
    //    return MathHelper.square(playerEntity.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
    //}
}
