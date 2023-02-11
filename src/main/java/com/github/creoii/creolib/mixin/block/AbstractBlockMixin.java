package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.api.util.registry.CBlockSettings;
import com.github.creoii.creolib.api.util.registry.DripSettings;
import com.github.creoii.creolib.api.util.registry.FireSettings;
import com.github.creoii.creolib.core.duck.AbstractBlockDuck;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin implements AbstractBlockDuck {
    private FireSettings fireSettings;
    private DripSettings dripSettings;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void creo_lib_initCSettings(AbstractBlock.Settings settings, CallbackInfo ci) {
        if (settings instanceof CBlockSettings cBlockSettings) {
            this.fireSettings = cBlockSettings.getFireSettings();
            this.dripSettings = cBlockSettings.getDripSettings();
        }
    }

    @Override
    public FireSettings getFireSettings() {
        return fireSettings;
    }

    @Override
    public DripSettings getDripSettings() {
        return dripSettings;
    }
}
