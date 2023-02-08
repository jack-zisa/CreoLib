package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Redirect(method = "canPlaceAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean creo_lib_sugarCanePlaceables(BlockState instance, TagKey<Block> tagKey) {
        return instance.isIn(CBlockTags.SUGAR_CANE_PLANTABLE_ON) || instance.isIn(tagKey);
    }
}
