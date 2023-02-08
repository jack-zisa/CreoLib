package com.github.creoii.creolib.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

/**
 * Implement to allow your block to be crushed by an anvil
 */
public interface Crushable {
    /**
     * -1 allows for any fall distance
      */
    default int getMinimumFallDistance() {
        return -1;
    }

    default BlockState getCrushState() {
        return Blocks.AIR.getDefaultState();
    }

    default float getCrushChance() {
        return 1f;
    }

    default boolean shouldDropOnBreak() {
        return false;
    }
}
