package com.github.creoii.creolib.mixin.block;

import com.github.creoii.creolib.util.BlockUtil;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow public abstract Block getBlock();
    @Shadow protected abstract BlockState asBlockState();

    @Inject(method = "getMaterial", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceMaterial(CallbackInfoReturnable<Material> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).getMaterial());
        }
    }

    @Inject(method = "getLuminance", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceLuminance(CallbackInfoReturnable<Integer> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).getLuminance().applyAsInt(asBlockState()));
        }
    }

    @Inject(method = "allowsSpawning", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_replaceSpawning(BlockView world, BlockPos pos, EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).allowsSpawning().test(asBlockState(), world, pos, type));
        }
    }

    @Inject(method = "getHardness", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceHardness(CallbackInfoReturnable<Float> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).getHardness());
        }
    }

    @Inject(method = "getMapColor", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceMapColor(CallbackInfoReturnable<MapColor> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).getMaterial().getColor());
        }
    }

    @Inject(method = "hasEmissiveLighting", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_replaceEmissiveLighting(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).hasEmissiveLighting().test(asBlockState(), world, pos));
        }
    }

    @Inject(method = "isSolidBlock", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_replaceSolidBlock(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).isSolidBlock().test(asBlockState(), world, pos));
        }
    }

    @Inject(method = "isOpaque", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceOpaqueness(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).isOpaque());
        }
    }

    @Inject(method = "shouldSuffocate", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_replaceSuffocation(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).suffocates().test(asBlockState(), world, pos));
        }
    }

    @Inject(method = "shouldBlockVision", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_replaceBlocksVision(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).blocksVision().test(asBlockState(), world, pos));
        }
    }

    @Inject(method = "shouldPostProcess", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_replacePostProcessing(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).hasPostProcessing().test(asBlockState(), world, pos));
        }
    }

    @Inject(method = "getSoundGroup", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceSoundGroup(CallbackInfoReturnable<BlockSoundGroup> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).getSoundGroup());
        }
    }

    @Inject(method = "isToolRequired", at = @At("RETURN"), cancellable = true)
    private void creo_lib_replaceToolRequired(CallbackInfoReturnable<Boolean> cir) {
        if (BlockUtil.BLOCK_SETTINGS_REPLACED.containsKey(getBlock())) {
            cir.setReturnValue(((BlockSettingsAccessor) BlockUtil.getOrCreateSettings(getBlock())).isToolRequired());
        }
    }
}
