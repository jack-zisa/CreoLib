package com.github.creoii.creolib.world.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.Optional;

public class DistanceFromZeroStructurePlacement extends StructurePlacement {
    public static final Codec<DistanceFromZeroStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("min_squared_distance").forGetter(predicate -> {
            return predicate.minSquaredDistance;
        })).and(buildCodec(instance)).apply(instance, DistanceFromZeroStructurePlacement::new);
    });
    private final int minSquaredDistance;

    public DistanceFromZeroStructurePlacement(int minSquaredDistance, Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone);
        this.minSquaredDistance = minSquaredDistance;
    }

    @Override
    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        return new Vec3d(chunkX, 0d, chunkZ).squaredDistanceTo(Vec3d.ZERO) >= minSquaredDistance;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return null;
    }
}
