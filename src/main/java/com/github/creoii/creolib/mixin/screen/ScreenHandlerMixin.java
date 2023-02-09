package com.github.creoii.creolib.mixin.screen;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {
    //@ModifyConstant(method = "canUse(Lnet/minecraft/screen/ScreenHandlerContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/Block;)Z", constant = @Constant(doubleValue = 64d))
    //private static double creo_lib_reachAccessScreens(double constant, ScreenHandlerContext context, PlayerEntity playerEntity, Block block) {
    //    return MathHelper.square(playerEntity.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
    //}
}
