package com.github.creoii.creolib;

import com.github.creoii.creolib.registry.AttributeRegistry;
import com.github.creoii.creolib.registry.PlacementRegistry;
import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import net.fabricmc.api.ModInitializer;

public class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo";
    public static final String COMMON_NAMESPACE = "c";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        PlacementRegistry.register();
        StructurePlacementTypeRegistry.register();
    }
}
