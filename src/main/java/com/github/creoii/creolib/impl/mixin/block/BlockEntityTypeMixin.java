package com.github.creoii.creolib.impl.mixin.block;

import com.github.creoii.creolib.api.tag.CBlockTags;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    private static final ImmutableMap<BlockEntityType<?>, Predicate<BlockState>> SUPPORTING_BLOCK_ENTITIES = new ImmutableMap.Builder<BlockEntityType<?>, Predicate<BlockState>>()
            .put(BlockEntityType.SIGN, state -> state.isIn(BlockTags.SIGNS))
            .put(BlockEntityType.HANGING_SIGN, state -> state.isIn(BlockTags.ALL_HANGING_SIGNS))
            .put(BlockEntityType.CHEST, state -> state.isIn(CBlockTags.CHESTS))
            .put(BlockEntityType.TRAPPED_CHEST, state -> state.isIn(CBlockTags.TRAPPED_CHESTS))
            .put(BlockEntityType.FURNACE, state -> state.isIn(CBlockTags.FURNACES))
            .put(BlockEntityType.BANNER, state -> state.isIn(BlockTags.BANNERS))
            .put(BlockEntityType.BED, state -> state.isIn(BlockTags.BEDS))
            .put(BlockEntityType.CAMPFIRE, state -> state.isIn(BlockTags.CAMPFIRES))
            .put(BlockEntityType.SHULKER_BOX, state -> state.isIn(BlockTags.SHULKER_BOXES))
            .build();

    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void creo_lib_supportBlockEntities(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (SUPPORTING_BLOCK_ENTITIES.containsKey((BlockEntityType<?>) (Object) this)) {
            cir.setReturnValue(SUPPORTING_BLOCK_ENTITIES.get((BlockEntityType<?>) (Object) this).test(state));
        }
    }
}
