package com.github.creoii.creolib.impl.mixin.block;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {
    @Inject(method = "isSignalFireBaseBlock", at = @At("HEAD"), cancellable = true)
    private void creo_lib_signalFireBaseBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CBlockTags.SIGNAL_FIRE_BASE_BLOCKS)) cir.setReturnValue(true);
    }
}
