package com.github.creoii.creolib.api.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public final class WorldUtil {
    public static void broadcast(@NotNull ServerWorld world, Text text, Predicate<ServerPlayerEntity> playerEntityPredicate) {
        world.getPlayers().forEach(playerEntity -> {
            if (playerEntityPredicate.test(playerEntity)) {
                playerEntity.sendMessage(text);
            }
        });
    }

    public static void broadcast(ServerWorld world, Text text) {
        broadcast(world, text, playerEntity -> true);
    }
}
