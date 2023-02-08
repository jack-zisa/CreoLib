package com.github.creoii.creolib.api.world;

import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows an implementing object to tick in the server world
 */
@FunctionalInterface
public interface ServerWorldTicker {
    List<ServerWorldTicker> TICKERS = new ArrayList<>();

    void tick(ServerWorld world);
}
