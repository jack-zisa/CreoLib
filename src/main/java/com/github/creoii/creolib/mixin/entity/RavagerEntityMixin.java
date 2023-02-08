package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(RavagerEntity.class)
public abstract class RavagerEntityMixin extends RaiderEntity {
    protected RavagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_ravagerBreakables(CallbackInfo ci, boolean bl, Box box, Iterator<Block> iterator, BlockPos blockPos, BlockState blockState) {
        if (blockState.isIn(CBlockTags.RAVAGER_BREAKABLE)) {
            bl = world.breakBlock(blockPos, true, this) || bl;
            onGround = false;
        }
    }
}
