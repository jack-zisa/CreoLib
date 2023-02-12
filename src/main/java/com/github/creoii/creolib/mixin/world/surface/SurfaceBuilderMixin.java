package com.github.creoii.creolib.mixin.world.surface;

import com.github.creoii.creolib.api.world.BiomeSurfaceBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SurfaceBuilder.class)
public class SurfaceBuilderMixin {
    @Inject(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;sampleHeightmap(Lnet/minecraft/world/Heightmap$Type;II)I", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void creo_lib_applySurfaceBuilders(NoiseConfig noiseConfig, BiomeAccess biomeAccess, Registry<Biome> biomeRegistry, boolean useLegacyRandom, HeightContext heightContext, Chunk chunk, ChunkNoiseSampler chunkNoiseSampler, MaterialRules.MaterialRule materialRule, CallbackInfo ci, BlockPos.Mutable mutable, ChunkPos chunkPos, int i, int j, BlockColumn blockColumn, MaterialRules.MaterialRuleContext materialRuleContext, MaterialRules.BlockStateRule blockStateRule, BlockPos.Mutable mutable2, int k, int l, int m, int n, int o, RegistryEntry<Biome> registryEntry) {
        if (registryEntry.getKey().isPresent()) {
            RegistryKey<Biome> biomeKey = registryEntry.getKey().get();
            if (BiomeSurfaceBuilder.SURFACE_BUILDERS.containsKey(biomeKey)) {
                BiomeSurfaceBuilder.SURFACE_BUILDERS.get(biomeKey).buildSurface(noiseConfig, biomeAccess, registryEntry, m, n, blockColumn, materialRuleContext, useLegacyRandom, heightContext, chunk, chunkNoiseSampler, materialRule);
            }
        }
    }
}
