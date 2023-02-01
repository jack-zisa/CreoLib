package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementModifierRegistry;
import com.github.creoii.creolib.util.Untested;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.structure.Structure;

@Untested("Structures generate after features")
public class NearStructurePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NearStructurePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.STRUCTURE).fieldOf("structure").forGetter(placement -> {
            return placement.structures;
        }), Codec.INT.fieldOf("squared_distance").forGetter(placement -> {
            return placement.squaredDistance;
        }), Codec.INT.optionalFieldOf("search_radius", 6).forGetter(placement -> {
            return placement.searchRadius;
        })).apply(instance, NearStructurePlacementModifier::new);
    });
    private final RegistryEntryList<Structure> structures;
    private final int squaredDistance;
    private final int searchRadius;

    public NearStructurePlacementModifier(RegistryEntryList<Structure> structures, int squaredDistance, int searchRadius) {
        this.structures = structures;
        this.squaredDistance = squaredDistance;
        if (searchRadius > 24) this.searchRadius = 24;
        else if (searchRadius <= 0) this.searchRadius = 1;
        else this.searchRadius = searchRadius;
    }

    public NearStructurePlacementModifier(RegistryEntryList<Structure> structures, int squaredDistance) {
        this(structures, squaredDistance, 6);
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        if (context.getWorld() instanceof ServerWorld serverWorld) {
            Pair<BlockPos, RegistryEntry<Structure>> locatePair = context.getChunkGenerator().locateStructure(serverWorld, structures, pos, searchRadius, false);
            if (locatePair != null) {
                BlockPos structurePos = locatePair.getFirst();
                return pos.getSquaredDistance(structurePos) <= squaredDistance;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.NEAR_STRUCTURE;
    }
}