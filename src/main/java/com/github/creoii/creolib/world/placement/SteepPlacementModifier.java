package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

/**
 * After square placement
 * This code was taken from {@link net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules}
 */
public class SteepPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<SteepPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.intRange(1, 16).fieldOf("steepness").forGetter(predicate -> {
            return predicate.steepness;
        })).apply(instance, SteepPlacementModifier::new);
    });
    private final int steepness;

    public SteepPlacementModifier(int steepness) {
        this.steepness = steepness;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        int i = pos.getX() & 0xf;
        int j = pos.getZ() & 0xf;
        int k = Math.max(j - 1, 0);
        int l = Math.min(j + 1, 15);
        Chunk chunk = context.getWorld().getChunk(pos);
        int n = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, i, l);
        if (n >= chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, i, k) + steepness) {
            return true;
        }
        int o = Math.max(i - 1, 0);
        int p = Math.min(i + 1, 15);
        int q = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, o, j);
        return q >= chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, p, j) + steepness;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.STEEP;
    }
}
