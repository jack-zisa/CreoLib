package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.api.tag.CBlockTags;
import com.github.creoii.creolib.api.util.registry.CBlockSettings;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {
    @Inject(method = "dripTick", at = @At(value = "INVOKE", target = "Ljava/util/Optional;get()Ljava/lang/Object;", ordinal = 1, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void creo_lib_dripBlocks(BlockState state, ServerWorld world, BlockPos pos, float dripChance, CallbackInfo ci, Optional<PointedDripstoneBlock.DrippingFluid> optional, Fluid fluid, float f, BlockPos blockPos) {
        BlockState sourceState = optional.get().sourceState();
        if (((AbstractBlockAccessor) sourceState.getBlock()).getSettings() instanceof CBlockSettings settings) {
            CBlockSettings.DripSettings dripSettings = settings.getDripSettings();
            if (fluid == dripSettings.fluid()) {
                BlockState blockState = dripSettings.drippedState();
                world.setBlockState(optional.get().pos(), blockState);
                Block.pushEntitiesUpBeforeBlockChange(sourceState, blockState, world, optional.get().pos());
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, optional.get().pos(), GameEvent.Emitter.of(blockState));
                world.syncWorldEvent(1504, blockPos, 0);
            }
        }
    }
    
    @Inject(method = "canDripThrough", at = @At("HEAD"), cancellable = true)
    private static void creo_lib_canDripThrough(BlockView world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(CBlockTags.CAN_DRIP_THROUGH)) cir.setReturnValue(true);
    }
}
