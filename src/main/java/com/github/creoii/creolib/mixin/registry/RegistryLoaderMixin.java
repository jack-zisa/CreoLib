package com.github.creoii.creolib.mixin.registry;

import com.github.creoii.creolib.registry.FastNoiseParametersRegistry;
import com.github.creoii.creolib.util.noise.FastNoiseLite;
import com.google.common.collect.ImmutableList;
import net.minecraft.registry.RegistryLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {
    @Shadow @Final @Mutable
    public static List<RegistryLoader.Entry<?>> DYNAMIC_REGISTRIES;

    static {
        DYNAMIC_REGISTRIES = new ImmutableList.Builder<RegistryLoader.Entry<?>>()
                .addAll(DYNAMIC_REGISTRIES)
                .add(new RegistryLoader.Entry<>(FastNoiseParametersRegistry.FAST_NOISE_SETTINGS, FastNoiseLite.CODEC))
                .build();
    }
}
