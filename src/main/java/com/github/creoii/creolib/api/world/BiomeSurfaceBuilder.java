package com.github.creoii.creolib.api.world;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
public interface BiomeSurfaceBuilder {
    Map<RegistryKey<Biome>, BiomeSurfaceBuilder> SURFACE_BUILDERS = new HashMap<>();

    void buildSurface(NoiseConfig noiseConfig, BiomeAccess biomeAccess, RegistryEntry<Biome> registryEntry, int x, int z, BlockColumn blockColumn, MaterialRules.MaterialRuleContext materialRuleContext, boolean useLegacyRandom, HeightContext heightContext, Chunk chunk, ChunkNoiseSampler chunkNoiseSampler, MaterialRules.MaterialRule materialRule);
}
