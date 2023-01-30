package com.github.creoii.creolib.world;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface ClientWorldTicker {
    List<ClientWorldTicker> TICKERS = new ArrayList<>();

    @Environment(EnvType.CLIENT)
    void tick(ClientWorld world);
}
