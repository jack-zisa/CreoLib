package com.github.creoii.creolib.mixin.client;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    private void creo_lib_extendReachDistance(CallbackInfoReturnable<Float> cir) {
        if (client.player != null) {
            if (client.player.isCreative()) {
                cir.setReturnValue((float) client.player.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE) * 1.11111111f);
            } else cir.setReturnValue((float) client.player.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE));
        }
    }

    @ModifyConstant(method = "attackBlock", constant = @Constant(intValue = 5))
    private int creo_lib_blockAttackCooldown(int constant) {
        if (client.player != null) {
            return (int) client.player.getAttributeValue(AttributeRegistry.PLAYER_BLOCK_BREAK_SPEED);
        } return constant;
    }

    @ModifyConstant(method = "updateBlockBreakingProgress", constant = @Constant(intValue = 5))
    private int creo_lib_blockCooldown(int constant) {
        if (client.player != null) {
            return (int) client.player.getAttributeValue(AttributeRegistry.PLAYER_BLOCK_BREAK_SPEED);
        } return constant;
    }
}
