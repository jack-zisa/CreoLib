package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralBlockBlock.class)
public class CoralBlockBlockMixin {
    @Inject(method = "isInWater", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void creo_lib_keepCoralAlive(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(pos.offset(direction)).isIn(CBlockTags.KEEPS_CORAL_ALIVE)) {
                System.out.println("stayin' alive");
                cir.setReturnValue(true);
            }
        }
    }
}
