package com.github.creoii.creolib.util;

import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface Tickable {
    List<Tickable> TICKERS = new ArrayList<>();

    void tick(World world);
}
