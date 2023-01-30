package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.Optional;

public class FixedStructurePlacement extends StructurePlacement {
    public static final Codec<FixedStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockPos.CODEC.fieldOf("pos").forGetter(placement -> {
            return placement.pos;
        })).and(buildCodec(instance)).apply(instance, FixedStructurePlacement::new);
    });
    private final BlockPos pos;

    public FixedStructurePlacement(BlockPos pos, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, Optional.empty());
        this.pos = pos;
    }

    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public BlockPos getLocatePos(ChunkPos chunkPos) {
        return pos;
    }

    public StructurePlacementType<?> getType() {
        return StructurePlacementTypeRegistry.FIXED;
    }
}
