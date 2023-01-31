package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import com.github.creoii.creolib.util.WorldUtil;
import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.List;
import java.util.Optional;

public class FastNoiseStructurePlacement extends StructurePlacement {
    public static final Codec<FastNoiseStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(FastNoiseLite.CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), WorldUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new WorldUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        })).and(buildCodec(instance)).apply(instance, FastNoiseStructurePlacement::new);
    });
    private final FastNoiseLite noise;
    private final List<WorldUtil.Range> ranges;

    public FastNoiseStructurePlacement(FastNoiseLite noise, List<WorldUtil.Range> ranges, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone);
        this.noise = noise;
        this.ranges = ranges;
    }

    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        BlockPos pos = getLocatePos(new ChunkPos(chunkX, chunkZ));
        double noiseValue = noise.GetNoise(pos.getX(), pos.getY(), pos.getZ());
        for (WorldUtil.Range range : ranges) {
            if (noiseValue >= range.min() && noiseValue < range.max()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BlockPos getLocatePos(ChunkPos chunkPos) {
        return chunkPos.getStartPos().add(getLocateOffset());
    }

    public StructurePlacementType<?> getType() {
        return StructurePlacementTypeRegistry.FIXED;
    }
}
