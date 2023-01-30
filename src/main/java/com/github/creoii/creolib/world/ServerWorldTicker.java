package com.github.creoii.creolib.world;

import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ServerWorldTicker {
    List<ServerWorldTicker> TICKERS = new ArrayList<>();

    void tick(ServerWorld world);
}
