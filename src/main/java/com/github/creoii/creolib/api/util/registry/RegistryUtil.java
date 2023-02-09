package com.github.creoii.creolib.api.util.registry;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class RegistryUtil {
    public static final DynamicRegistryManager.Immutable DYNAMIC_REGISTRY_MANAGER = DynamicRegistryManager.of(Registries.REGISTRIES);

    /**
     * This method allows access to dynamic registries for Biomes, Configured Features, Dimensions, etc
     * Using this before world loading will result in an empty Optional, since these registries are only
     * loaded then
     */
    public static <T> Optional<Registry<T>> getRegistryFromKey(RegistryKey<? extends Registry<? extends T>> registryKey) {
        return DYNAMIC_REGISTRY_MANAGER.getOptional(registryKey);
    }

    /**
     * @return A list of registry entries present in {@param registryKey}
     * This method will return an empty list if used before registries are dynamically loaded,
     * unless they are loaded with an {@link net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider}
     */
    public static <T> List<RegistryEntry<T>> getEntries(RegistryKey<? extends Registry<? extends T>> registryKey) {
        List<RegistryEntry<T>> entries = new ArrayList<>();
        Optional<Registry<T>> registry = getRegistryFromKey(registryKey);
        registry.ifPresent(ts -> ts.forEach(obj -> {
            entries.add(ts.getEntry(obj));
        }));
        return entries;
    }
}
