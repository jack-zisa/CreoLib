package com.github.creoii.creolib.mixin.client;

import com.github.creoii.creolib.util.ClientUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void great_big_world_gbwWindowTitle(CallbackInfoReturnable<String> cir) {
        if (ClientUtil.windowTitle != null) cir.setReturnValue(ClientUtil.windowTitle);
    }
}
