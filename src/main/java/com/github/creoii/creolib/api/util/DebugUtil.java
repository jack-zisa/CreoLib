package com.github.creoii.creolib.api.util;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class DebugUtil {
    public static <T> void printRegistryValues(Registry<T> registry) {
        registry.forEach(obj -> {
            Identifier id = registry.getId(obj);
            if (id != null) {
                System.out.println(id);
            }
        });
    }
}
