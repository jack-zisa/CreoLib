package com.github.creoii.creolib.mixin.entity.ai;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

@Mixin(EatGrassGoal.class)
public class EatGrassGoalMixin {
    @Shadow @Final @Mutable
    private static Predicate<BlockState> GRASS_PREDICATE;

    static {
        GRASS_PREDICATE = state -> {
            return state.isIn(CBlockTags.EATEN_BY_SHEEP) || state.isOf(Blocks.GRASS);
        };
    }
}
