package com.github.creoii.creolib.core.registry;

import com.github.creoii.creolib.core.CreoLib;
import com.github.creoii.creolib.core.noise.FastNoiseLite;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public final class FastNoiseParametersRegistry extends FabricDynamicRegistryProvider {
    public static final RegistryKey<Registry<FastNoiseLite>> FAST_NOISE_SETTINGS = RegistryKey.ofRegistry(new Identifier(CreoLib.NAMESPACE, "worldgen/fast_noise"));
    public static final Codec<RegistryEntry<FastNoiseLite>> REGISTRY_CODEC = RegistryElementCodec.of(FAST_NOISE_SETTINGS, FastNoiseLite.CODEC);

    public FastNoiseParametersRegistry(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.add(RegistryKey.of(FAST_NOISE_SETTINGS, new Identifier(CreoLib.NAMESPACE, "noodle")), new FastNoiseLite()
                .SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S)
                .SetFractalType(FastNoiseLite.FractalType.PingPong)
                .SetFractalGain(0f)
                .SetFractalPingPongStrength(4f)
        );
        entries.add(RegistryKey.of(FAST_NOISE_SETTINGS, new Identifier(CreoLib.NAMESPACE, "honeycomb")), new FastNoiseLite()
                .SetNoiseType(FastNoiseLite.NoiseType.Cellular)
                .SetFrequency(.015f)
                .SetFractalType(FastNoiseLite.FractalType.Ridged)
                .SetFractalLacunarity(0f)
        );
        entries.add(RegistryKey.of(FAST_NOISE_SETTINGS, new Identifier(CreoLib.NAMESPACE, "ridged_gridlike")), new FastNoiseLite()
                .SetNoiseType(FastNoiseLite.NoiseType.Value)
                .SetFrequency(.02f)
                .SetFractalType(FastNoiseLite.FractalType.Ridged)
                .SetFractalLacunarity(0f)
        );
        entries.add(RegistryKey.of(FAST_NOISE_SETTINGS, new Identifier(CreoLib.NAMESPACE, "constant_zones")), new FastNoiseLite()
                .SetNoiseType(FastNoiseLite.NoiseType.Cellular)
                .SetFrequency(.02f)
                .SetFractalType(FastNoiseLite.FractalType.None)
                .SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.Euclidean)
                .SetCellularReturnType(FastNoiseLite.CellularReturnType.CellValue)
        );
        entries.add(RegistryKey.of(FAST_NOISE_SETTINGS, new Identifier(CreoLib.NAMESPACE, "grid")), new FastNoiseLite()
                .SetNoiseType(FastNoiseLite.NoiseType.Cellular)
                .SetFrequency(.025f)
                .SetFractalType(FastNoiseLite.FractalType.None)
                .SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.Euclidean)
                .SetCellularReturnType(FastNoiseLite.CellularReturnType.CellValue)
                .SetCellularJitter(0f)
        );
        entries.add(RegistryKey.of(FAST_NOISE_SETTINGS, new Identifier(CreoLib.NAMESPACE, "web")), new FastNoiseLite()
                .SetNoiseType(FastNoiseLite.NoiseType.Cellular)
                .SetFrequency(.015f)
                .SetFractalType(FastNoiseLite.FractalType.None)
                .SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.Euclidean)
                .SetCellularReturnType(FastNoiseLite.CellularReturnType.Distance)
                .SetCellularJitter(1.5f));
    }

    @Override
    public String getName() {
        return "FastNoise Parameters";
    }
}
