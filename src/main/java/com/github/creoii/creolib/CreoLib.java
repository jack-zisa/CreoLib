package com.github.creoii.creolib;

import com.github.creoii.creolib.registry.AttributeRegistry;
import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import net.fabricmc.api.ModInitializer;

public class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo_lib";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        StructurePlacementTypeRegistry.register();
    }
}
