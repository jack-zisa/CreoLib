package com.github.creoii.creolib.phase;

import com.github.creoii.creolib.phase.behavior.Behavior;
import com.github.creoii.creolib.phase.transition.Transition;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Phase<T extends PhaseHolder> {
    private final Identifier id;
    private final T holder;
    private final int maxDuration;
    private final int startDelay;
    private final List<Behavior> behaviors;
    private final Transition outOfTimeTransition;
    private final List<Transition> transitions;
    private int duration;
    private int delay;
    private boolean stopped = false;

    private Phase(Identifier id, T holder, int maxDuration, int startDelay, List<Behavior> behaviors, Transition outOfTimeTransition, List<Transition> transitions) {
        this.id = id;
        this.holder = holder;
        this.maxDuration = maxDuration;
        duration = maxDuration;
        this.startDelay = startDelay;
        delay = startDelay;
        this.behaviors = behaviors;
        this.outOfTimeTransition = outOfTimeTransition;
        this.transitions = transitions;
    }

    public Phase(Identifier id, T holder, int maxDuration, List<Behavior> behaviors, Transition outOfTimeTransition) {
        this(id, holder, maxDuration, -1, behaviors, outOfTimeTransition, List.of());
    }

    public Phase(Identifier id, T holder, int maxDuration, int startDelay, List<Behavior> behaviors, Transition outOfTimeTransition) {
        this(id, holder, maxDuration, startDelay, behaviors, outOfTimeTransition, List.of());
    }

    public Phase(Identifier id, T holder, int maxDuration, List<Behavior> behaviors, Transition outOfTimeTransition, List<Transition> transitions) {
        this(id, holder, maxDuration, -1, behaviors, outOfTimeTransition, transitions);
    }

    public Identifier getId() {
        return id;
    }

    public T getHolder() {
        return holder;
    }

    public int getDuration() {
        return duration;
    }

    public void stop() {
        stopped = true;
    }

    public void start() {
        stopped = false;
    }

    public void initialize(World world, BlockPos pos) {
        duration = maxDuration;
        delay = startDelay;
        behaviors.forEach(behavior -> {
            behavior.initialize(world, pos, holder);
        });
    }

    public void tick(World world, BlockPos pos) {
        if (!stopped) {
            System.out.println(getId());
            if (delay <= 0) {
                --duration;
                if (duration <= 0) {
                    transition(world, pos, holder, outOfTimeTransition);
                }
                tickTransitions(world, pos);
                tickBehaviors(world, pos);
            } else --delay;
        }
    }

    public void tickBehaviors(World world, BlockPos pos) {
        behaviors.forEach(behavior -> behavior.tick(world, pos, holder, duration));
    }

    public void tickTransitions(World world, BlockPos pos) {
        transitions.forEach(transition -> {
            if (transition.satisfy(world, pos, holder, duration)) {
                transition(world, pos, holder, transition);
            }
        });
    }

    public void transition(World world, BlockPos pos, PhaseHolder holder, Transition transition) {
        holder.getCurrentPhase().stop();
        holder.setCurrentPhase(transition.getNextPhase(world, pos, holder, holder.getPhases()));
        holder.getCurrentPhase().initialize(world, pos);
    }
}
