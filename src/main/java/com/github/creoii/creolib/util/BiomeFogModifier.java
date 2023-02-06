package com.github.creoii.creolib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.FogShape;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public record BiomeFogModifier(float fogStart, float fogEnd, FogShape fogShape) {
    public static final Map<RegistryKey<Biome>, BiomeFogModifier> BIOME_FOG_MODIFIERS = new HashMap<>();
}
