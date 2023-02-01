package com.github.creoii.creolib.world.placement;

import com.github.creoii.creolib.registry.PlacementModifierRegistry;
import com.github.creoii.creolib.util.WorldUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

public class DensityFunctionPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<DensityFunctionPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(DensityFunction.REGISTRY_ENTRY_CODEC.fieldOf("density_function").forGetter(predicate -> {
            return predicate.densityFunction;
        }), WorldUtil.Range.CODEC.listOf().optionalFieldOf("ranges", List.of(new WorldUtil.Range(-1d, 1d))).forGetter(predicate -> {
            return predicate.ranges;
        })).apply(instance, DensityFunctionPlacementModifier::new);
    });
    private final RegistryEntry<DensityFunction> densityFunction;
    private final List<WorldUtil.Range> ranges;

    public DensityFunctionPlacementModifier(RegistryEntry<DensityFunction> densityFunction, List<WorldUtil.Range> ranges) {
        this.densityFunction = densityFunction;
        this.ranges = ranges;
    }

    @Override
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        double value = densityFunction.value().sample(new DensityFunction.UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
        for (WorldUtil.Range range : ranges) {
            if (value >= range.min() && value < range.max()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.NOISE;
    }
}