package com.github.creoii.creolib.mixin.item;

import com.github.creoii.creolib.api.block.BlockShearable;
import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void creo_lib_blockShearables(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        Direction side = context.getSide();
        if (state.getBlock() instanceof BlockShearable shearable && shearable.isShearable(world, state, pos, player, world.getRandom(), side)) {
            cir.setReturnValue(shearable.onShear(world, state, pos, player, world.getRandom(), side));
        }
    }

    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    private void creo_lib_shearsMineables(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        if (state.isIn(CBlockTags.SHEARS_QUICK_MINEABLE)) cir.setReturnValue(15f);
        else if (state.isIn(CBlockTags.SHEARS_SLOW_MINEABLE)) cir.setReturnValue(5f);
    }
}
