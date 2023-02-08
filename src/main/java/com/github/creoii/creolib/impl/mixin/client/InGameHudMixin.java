package com.github.creoii.creolib.impl.mixin.client;

import com.github.creoii.creolib.api.client.OverlayRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public abstract class InGameHudMixin {
    @Shadow protected abstract void renderOverlay(Identifier texture, float opacity);
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getFrozenTicks()I"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_overlayRenderers(MatrixStack matrices, float tickDelta, CallbackInfo ci, Window window, TextRenderer textRenderer, float f, ItemStack itemStack) {
        OverlayRenderer.OVERLAY_RENDERERS.forEach(renderer -> {
            renderOverlay(renderer.getTexture(), renderer.getOpacity(client, client.player));
        });
    }
}
