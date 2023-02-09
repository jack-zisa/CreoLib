package com.github.creoii.creolib.mixin.client;

import com.github.creoii.creolib.api.util.ClientUtil;
import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow private int itemUseCooldown;
    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void creo_lib_gbwWindowTitle(CallbackInfoReturnable<String> cir) {
        if (ClientUtil.windowTitle != null) cir.setReturnValue(ClientUtil.windowTitle);
    }

    @Inject(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isItemEnabled(Lnet/minecraft/resource/featuretoggle/FeatureSet;)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_blockPlaceCooldown(CallbackInfo ci, Hand[] var1, int var2, int var3, Hand hand, ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem && player != null) {
            itemUseCooldown = (int) player.getAttributeValue(AttributeRegistry.PLAYER_BLOCK_PLACE_SPEED);
        }
    }
}
