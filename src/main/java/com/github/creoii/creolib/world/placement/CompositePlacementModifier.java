package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

public class CompositePlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<CompositePlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(PlacementModifier.CODEC.listOf().fieldOf("modifiers").forGetter(placement -> {
            return placement.modifiers;
        }), Codec.BOOL.fieldOf("fail_if_first_fails").orElse(true).forGetter(placement -> {
            return placement.failIfFirstFails;
        })).apply(instance, CompositePlacementModifier::new);
    });
    private final List<PlacementModifier> modifiers;
    private final boolean failIfFirstFails;

    public CompositePlacementModifier(List<PlacementModifier> modifiers, boolean failIfFirstFails) {
        this.modifiers = modifiers;
        this.failIfFirstFails = failIfFirstFails;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        boolean generated = false;
        for (PlacementModifier modifier : modifiers) {
            if (modifier instanceof AbstractConditionalPlacementModifier conditionalPlacementModifier) {
                if (conditionalPlacementModifier.shouldPlace(context, random, pos)) {
                    generated = true;
                } else if (failIfFirstFails) break;
            }
        }
        return generated;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.COMPOSITE;
    }
}
