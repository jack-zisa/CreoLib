package com.github.creoii.creolib.mixin.world.dimension;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetherPortal.class)
public class NetherPortalMixin {
    @Shadow @Final @Mutable
    private static AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK;

    static {
        IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
            return state.isIn(CBlockTags.NETHER_PORTAL_FRAME_BASE_BLOCKS) || state.isOf(Blocks.OBSIDIAN);
        };
    }
}
