package com.github.creoii.creolib.phase.transition;

import com.github.creoii.creolib.phase.Phase;
import com.github.creoii.creolib.phase.PhaseHolder;
import com.github.creoii.creolib.util.ListUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public abstract class Transition {
    public static final Random RANDOM = Random.create();
    private final Type type;

    public Transition(Type type) {
        this.type = type;
    }

    public Phase<?> getNextPhase(World world, BlockPos pos, PhaseHolder holder, List<Phase<?>> phases) {
        onTransition(world, pos, holder);
        switch (type) {
            case NEXT -> {
                return ListUtil.cycle(phases, holder.getCurrentPhaseIndex());
            }
            case RANDOM -> {
                if (phases.size() == 1) return phases.get(0);
                else return phases.get(RANDOM.nextInt(phases.size() - 1));
            }
        }
        return phases.get(0);
    }

    public void onTransition(World world, BlockPos pos, PhaseHolder holder) {}

    public abstract boolean satisfy(World world, BlockPos pos, PhaseHolder holder, int duration);

    public enum Type {
        RANDOM,
        BY_ID,
        NEXT
    }
}
