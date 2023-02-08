package com.github.creoii.creolib.impl.mixin.world;

import com.github.creoii.creolib.api.util.Tickable;
import com.github.creoii.creolib.api.world.ClientWorldTicker;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;tickTime()V", shift = At.Shift.AFTER))
    private void creo_lib_applyServerWorldTickers(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ClientWorldTicker.TICKERS.forEach(ticker -> {
            ticker.tick((ClientWorld) (Object) this);
        });
        Tickable.TICKERS.forEach(tickable -> {
            tickable.tick((World) (Object) this);
        });
    }
}
