package com.github.creoii.creolib.api.util;

import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows any implementing object to tick both client & server side
 */
@FunctionalInterface
public interface Tickable {
    List<Tickable> TICKERS = new ArrayList<>();

    void tick(World world);
}
