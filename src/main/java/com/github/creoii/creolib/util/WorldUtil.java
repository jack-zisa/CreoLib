package com.github.creoii.creolib.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.function.Predicate;

public class WorldUtil {
    public static void broadcast(ServerWorld world, Text text, Predicate<ServerPlayerEntity> playerEntityPredicate) {
        world.getPlayers().forEach(playerEntity -> {
            if (playerEntityPredicate.test(playerEntity)) {
                playerEntity.sendMessage(text);
            }
        });
    }

    public static void broadcast(ServerWorld world, Text text) {
        broadcast(world, text, playerEntity -> true);
    }

    public record Range(double min, double max) {
        public static final Codec<Range> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(Codec.DOUBLE.fieldOf("min_inclusive").forGetter(range -> {
                return range.min;
            }), Codec.DOUBLE.fieldOf("max_exclusive").forGetter(range -> {
                return range.max;
            })).apply(instance, Range::new);
        });
    }
}
