package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.tag.CBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @Redirect(method = "canPlaceAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1))
    private boolean creo_lib_cactusPlaceables(BlockState instance, Block block) {
        return instance.isIn(CBlockTags.CACTUS_PLANTABLE_ON) || instance.isOf(block);
    }
}
