package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.tag.CEntityTypeTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.TripwireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TripwireBlock.class)
public class TripwireBlockMixin {
    @Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/TripwireBlock;updatePowered(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"), cancellable = true)
    private void creo_lib_tripwireIgnores(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (entity.getType().isIn(CEntityTypeTags.TRIPWIRE_IGNORES)) ci.cancel();
    }
}
