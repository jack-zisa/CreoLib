package com.github.creoii.creolib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public record BiomeFogModifier(Function<FogFunction, Float> fogStart, Function<FogFunction, Float> fogEnd, FogShape fogShape) {
    public static final Map<RegistryKey<Biome>, BiomeFogModifier> BIOME_FOG_MODIFIERS = new HashMap<>();

    public record FogFunction(ClientWorld world, Entity focusedEntity, float viewDistance, float tickDelta) { }
}