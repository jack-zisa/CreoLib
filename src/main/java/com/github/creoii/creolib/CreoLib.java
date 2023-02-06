package com.github.creoii.creolib;

import com.github.creoii.creolib.registry.*;
import com.github.creoii.creolib.util.BiomeFogModifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.client.render.FogShape;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;

public class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo";
    public static final String COMMON = "c";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        FeatureRegistry.register();
        PlacementModifierRegistry.register();
        StructurePlacementTypeRegistry.register();
        MaterialConditionRegistry.register();
        DensityFunctionTypeRegistry.register();

        BiomeModifications.create(new Identifier(NAMESPACE, "test_fog")).add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.DRIPSTONE_CAVES), biomeModificationContext -> {
            biomeModificationContext.getEffects().setFogColor(16003743);
        });
        BiomeFogModifier.BIOME_FOG_MODIFIERS.put(BiomeKeys.DRIPSTONE_CAVES, new BiomeFogModifier(.01f, .25f, FogShape.SPHERE));
    }
}
