package com.github.creoii.creolib.api.util;

import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public final class MiscUtil {
    public static ServerBossBar addGlobalBossBar(ServerWorld serverWorld, ServerBossBar bossBar) {
        serverWorld.getPlayers().forEach(bossBar::addPlayer);
        return bossBar;
    }

    public static ServerBossBar addLocalBossBar(ServerWorld serverWorld, BlockPos pos, ServerBossBar bossBar, int range) {
        serverWorld.getPlayers(player -> {
            return player.squaredDistanceTo(pos.toCenterPos()) <= MathHelper.square(range);
        }).forEach(bossBar::addPlayer);
        return bossBar;
    }
}
