package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.tag.CBlockTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
    @Redirect(method = "shouldLightPortalAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean creo_lib_lightNetherPortalFrameBaseBlocks(BlockState instance, Block block) {
        return instance.isOf(Blocks.OBSIDIAN) || instance.isIn(CBlockTags.NETHER_PORTAL_FRAME_BASE_BLOCKS);
    }
}
