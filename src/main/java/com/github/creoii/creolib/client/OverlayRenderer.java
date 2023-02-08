package com.github.creoii.creolib.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public interface OverlayRenderer {
    List<OverlayRenderer> OVERLAY_RENDERERS = new ArrayList<>();

    Identifier getTexture();

    float getOpacity(MinecraftClient client, ClientPlayerEntity playerEntity);
}
