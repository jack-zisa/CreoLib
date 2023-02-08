package com.github.creoii.creolib.api.world;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows an implementing object to tick in the client world
 */
@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface ClientWorldTicker {
    List<ClientWorldTicker> TICKERS = new ArrayList<>();

    @Environment(EnvType.CLIENT)
    void tick(ClientWorld world);
}
