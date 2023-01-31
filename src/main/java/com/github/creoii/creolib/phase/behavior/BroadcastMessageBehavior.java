package com.github.creoii.creolib.phase.behavior;

import com.github.creoii.creolib.phase.PhaseHolder;
import com.github.creoii.creolib.util.WorldUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class BroadcastMessageBehavior extends Behavior {
    private final Text message;
    /* Interval in ticks - i.e. an interval of 20 would execute once every second. */
    private final int interval;
    private final Predicate<ServerPlayerEntity> playerEntityPredicate;

    public BroadcastMessageBehavior(Text message, int interval, Predicate<ServerPlayerEntity> playerEntityPredicate) {
        this.message = message;
        this.interval = Math.max(interval, 1);
        this.playerEntityPredicate = playerEntityPredicate;
    }

    public BroadcastMessageBehavior(Text message, int interval) {
        this(message, interval, playerEntity -> true);
    }

    @Override
    public void tick(World world, BlockPos pos, PhaseHolder holder, int duration) {
        if (duration % interval == 0) {
            System.out.println(holder.getCurrentPhase().getId() + "| " + interval);
            if (!world.isClient) {
                WorldUtil.broadcast((ServerWorld) world, message, playerEntityPredicate);
            }
        }
    }
}
