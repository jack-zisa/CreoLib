package com.github.creoii.creolib.api.world.placement;

import com.github.creoii.creolib.core.registry.StructurePlacementTypeRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.Optional;

public class DistanceFromZeroStructurePlacement extends StructurePlacement {
    private static final Random RANDOM = Random.create();
    public static final Codec<DistanceFromZeroStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("min_squared_distance").forGetter(predicate -> {
            return predicate.minSquaredDistance;
        }), Codec.FLOAT.optionalFieldOf("chance", 1f).forGetter(predicate -> {
            return predicate.chance;
        })).and(buildCodec(instance)).apply(instance, DistanceFromZeroStructurePlacement::new);
    });
    private final int minSquaredDistance;
    private final float chance;

    public DistanceFromZeroStructurePlacement(int minSquaredDistance, float chance, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone);
        this.minSquaredDistance = minSquaredDistance;
        this.chance = chance;
    }

    @Override
    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (RANDOM.nextFloat() > chance) return false;
        return new Vec3d(chunkX, 0d, chunkZ).squaredDistanceTo(Vec3d.ZERO) >= minSquaredDistance;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return StructurePlacementTypeRegistry.DISTANCE_FROM_ZERO;
    }
}
