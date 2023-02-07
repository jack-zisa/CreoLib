package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.FastNoiseParametersRegistry;
import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import com.github.creoii.creolib.util.MathUtil;
import com.github.creoii.creolib.util.WorldUtil;
import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.List;
import java.util.Optional;

public class FastNoiseStructurePlacement extends StructurePlacement {
    private static final Random RANDOM = Random.create();
    public static final Codec<FastNoiseStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(FastNoiseParametersRegistry.REGISTRY_CODEC.fieldOf("noise").forGetter(predicate -> {
            return predicate.noise;
        }), MathUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new MathUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        }), Codec.FLOAT.optionalFieldOf("chance", 1f).forGetter(predicate -> {
            return predicate.chance;
        })).and(buildCodec(instance)).apply(instance, FastNoiseStructurePlacement::new);
    });
    private final RegistryEntry<FastNoiseLite> noise;
    private final List<MathUtil.Range> ranges;
    private final float chance;

    public FastNoiseStructurePlacement(RegistryEntry<FastNoiseLite> noise, List<MathUtil.Range> ranges, float chance, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone);
        this.noise = noise;
        this.ranges = ranges;
        this.chance = chance;
    }

    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (RANDOM.nextFloat() > chance) return false;
        BlockPos pos = getLocatePos(new ChunkPos(chunkX, chunkZ));
        double noiseValue = noise.value().GetNoise(pos.getX(), 0f, pos.getZ());
        for (MathUtil.Range range : ranges) {
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
        return StructurePlacementTypeRegistry.FAST_NOISE;
    }
}
