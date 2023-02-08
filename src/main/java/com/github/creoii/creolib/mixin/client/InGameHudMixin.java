package com.github.creoii.creolib.mixin.client;

import com.github.creoii.creolib.api.client.render.OverlayRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public abstract class InGameHudMixin {
    @Shadow protected abstract void renderOverlay(Identifier texture, float opacity);
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F", ordinal = 0, shift = At.Shift.AFTER))
    private void creo_lib_overlayRenderers(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        OverlayRenderer.OVERLAY_RENDERERS.forEach(renderer -> {
            if (renderer.condition(client, client.player))
                renderOverlay(renderer.getTexture(), renderer.getOpacity(client, client.player));
        });
    }
}
