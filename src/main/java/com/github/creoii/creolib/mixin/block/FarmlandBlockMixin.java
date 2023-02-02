package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.tag.CBlockTags;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {
    @Inject(method = "isWaterNearby", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void creo_lib_keepsFarmlandMoist(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, Iterator<BlockPos> iterator, BlockPos blockPos) {
        if (world.getBlockState(blockPos).isIn(CBlockTags.KEEPS_FARMLAND_MOIST)) cir.setReturnValue(true);
    }
}
