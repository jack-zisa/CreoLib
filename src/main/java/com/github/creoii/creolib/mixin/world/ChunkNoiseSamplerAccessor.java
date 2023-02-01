package com.github.creoii.creolib.mixin.world;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.noise.NoiseRouter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(ChunkNoiseSampler.class)
public interface ChunkNoiseSamplerAccessor {
    @Invoker("getHorizontalBlockSize")
    int horizontalBlockSize();

    @Invoker("getVerticalBlockSize")
    int verticalBlockSize();

    @Invoker("sampleBlockState")
    BlockState sampleBlockState();

    @Invoker("createMultiNoiseSampler")
    MultiNoiseUtil.MultiNoiseSampler createMultiNoiseSampler(NoiseRouter noiseRouter, List<MultiNoiseUtil.NoiseHypercube> spawnTarget);
}
