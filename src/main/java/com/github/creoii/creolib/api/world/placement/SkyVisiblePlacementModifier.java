package com.github.creoii.creolib.api.world.placement;

import com.github.creoii.creolib.core.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class SkyVisiblePlacementModifier extends AbstractConditionalPlacementModifier {
    private static final SkyVisiblePlacementModifier INSTANCE = new SkyVisiblePlacementModifier();
    public static Codec<SkyVisiblePlacementModifier> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        return context.getWorld().isSkyVisible(pos);
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.SKY_VISIBLE;
    }
}
