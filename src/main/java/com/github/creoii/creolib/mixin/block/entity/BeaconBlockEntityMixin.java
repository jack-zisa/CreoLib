package com.github.creoii.creolib.mixin.block.entity;

import com.github.creoii.creolib.tag.CBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean creo_lib_beaconBeamIgnores(BlockState instance, Block block) {
        return instance.isIn(CBlockTags.BEACON_BEAM_IGNORES) || instance.isOf(Blocks.BEDROCK);
    }
}
