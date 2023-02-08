package com.github.creoii.creolib.impl.mixin.block;

import com.github.creoii.creolib.api.block.Crushable;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
    @Inject(method = "onLanding", at = @At("TAIL"))
    private void creo_lib_destroyCrushables(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo ci) {
        if (world.getBlockState(pos.down()).getBlock() instanceof Crushable crushable) {
            if (world.getRandom().nextFloat() > crushable.getCrushChance()) return;

            if (fallingBlockEntity.fallDistance > crushable.getMinimumFallDistance() || crushable.getMinimumFallDistance() == -1) {
                BlockState state = crushable.getCrushState();
                if (crushable.shouldDropOnBreak()) {
                    BlockEntity blockEntity = currentStateInPos.hasBlockEntity() ? world.getBlockEntity(pos.down()) : null;
                    Block.dropStacks(currentStateInPos, world, pos, blockEntity, fallingBlockEntity, ItemStack.EMPTY);
                }
                if (world.setBlockState(pos, state)) {
                    world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(fallingBlockEntity, state));
                }
            }
        }
    }
}
