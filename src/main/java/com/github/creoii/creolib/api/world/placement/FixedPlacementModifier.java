package com.github.creoii.creolib.api.world.placement;

import com.github.creoii.creolib.core.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.Stream;

public class FixedPlacementModifier extends PlacementModifier {
    public static final Codec<FixedPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(IntProvider.createValidatingCodec(-16, 16).fieldOf("x_offset").forGetter(modifier -> {
            return modifier.offsetX;
        }), IntProvider.createValidatingCodec(-16, 16).optionalFieldOf("y_offset", ConstantIntProvider.ZERO).forGetter(modifier -> {
            return modifier.offsetY;
        }), IntProvider.createValidatingCodec(-16, 16).fieldOf("z_offset").forGetter(modifier -> {
            return modifier.offsetZ;
        })).apply(instance, FixedPlacementModifier::new);
    });
    private final IntProvider offsetX;
    private final IntProvider offsetY;
    private final IntProvider offsetZ;

    public FixedPlacementModifier(IntProvider offsetX, IntProvider offsetY, IntProvider offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    public FixedPlacementModifier(IntProvider offsetX, IntProvider offsetZ) {
        this(offsetX, ConstantIntProvider.ZERO, offsetZ);
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        ChunkPos chunkPos = context.getWorld().getChunk(pos).getPos();
        int x = chunkPos.getOffsetX(offsetX.get(random));
        int z = chunkPos.getOffsetZ(offsetZ.get(random));
        int y = context.getWorld().getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
        return Stream.of(new BlockPos(x, y, z));
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.FIXED;
    }
}
