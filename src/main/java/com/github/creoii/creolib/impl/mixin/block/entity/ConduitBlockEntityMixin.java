package com.github.creoii.creolib.impl.mixin.block.entity;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {
    @Redirect(method = "updateActivatingBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean creo_lib_conduitBaseBlocks(BlockState instance, Block block) {
        return instance.isIn(CBlockTags.CONDUIT_FRAME_BASE_BLOCKS) || instance.isOf(block);
    }
}
