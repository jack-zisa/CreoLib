package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructurePresence;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.structure.Structure;
import org.apache.commons.lang3.mutable.MutableBoolean;

/**
 * Squared distance should be less than or equal to searchRadius * 16
 * -
 * This placement modifier should be last, because it is resource intensive
 */
public class NearStructurePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NearStructurePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.STRUCTURE).fieldOf("structure").forGetter(placement -> {
            return placement.structures;
        }), Codec.INT.fieldOf("squared_distance").forGetter(placement -> {
            return placement.squaredDistance;
        })).apply(instance, NearStructurePlacementModifier::new);
    });
    private final RegistryEntryList<Structure> structures;
    // block coordinates squared
    private final int squaredDistance;
    private final int searchRadius;

    public NearStructurePlacementModifier(RegistryEntryList<Structure> structures, int squaredDistance) {
        this.structures = structures;
        // limit to 4 chunks, because any higher amount has detrimental effects on world loading times - even this is pushing it
        this.squaredDistance = Math.min(squaredDistance, 4096);
        this.searchRadius = ((int) Math.sqrt(this.squaredDistance)) >> 4;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        StructureWorldAccess world = context.getWorld();
        // we can't access StructureAccessor on the client
        if (!world.isClient()) {
            MutableBoolean place = new MutableBoolean(false);

            // iterate in an area
            ChunkPos.stream(new ChunkPos(pos), searchRadius).forEach(chunkPos -> {
                for (RegistryEntry<Structure> entry : structures) {
                    if (entry.hasKeyAndValue()) {
                        StructurePresence structurePresence = world.toServerWorld().getStructureAccessor().getStructurePresence(chunkPos, entry.value(), false);
                        if (structurePresence == StructurePresence.START_PRESENT) {
                            place.setValue(chunkPos.getCenterAtY(pos.getY()).getSquaredDistance(pos) <= squaredDistance);
                        }
                    }
                }
            });
            return place.booleanValue();
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.NEAR_STRUCTURE;
    }
}