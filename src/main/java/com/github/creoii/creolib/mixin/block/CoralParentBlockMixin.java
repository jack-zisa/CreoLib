package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralParentBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralParentBlock.class)
public class CoralParentBlockMixin {
    @Inject(method = "isInWater", at = @At(value = "RETURN", ordinal = 2), cancellable = true)
    private static void creo_lib_keepCoralAlive(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(pos.offset(direction)).isIn(CBlockTags.KEEPS_CORAL_ALIVE)) {
                System.out.println("stayin' alive");
                cir.setReturnValue(true);
            }
        }
    }
}
