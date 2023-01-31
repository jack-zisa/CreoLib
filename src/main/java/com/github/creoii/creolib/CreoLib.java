package com.github.creoii.creolib;

import com.github.creoii.creolib.phase.Phase;
import com.github.creoii.creolib.phase.PhaseHolder;
import com.github.creoii.creolib.phase.behavior.BroadcastMessageBehavior;
import com.github.creoii.creolib.phase.transition.SkipTransition;
import com.github.creoii.creolib.phase.transition.Transition;
import com.github.creoii.creolib.registry.AttributeRegistry;
import com.github.creoii.creolib.registry.PlacementRegistry;
import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import com.github.creoii.creolib.world.ServerWorldTicker;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo";
    public static final String COMMON_NAMESPACE = "c";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        PlacementRegistry.register();
        StructurePlacementTypeRegistry.register();

        Tester tester = new Tester();
        ServerWorldTicker.TICKERS.add(world -> {
            tester.getCurrentPhase().tick(world, BlockPos.ORIGIN);
        });
    }

    public static class Tester implements PhaseHolder {
        private final List<Phase<?>> phases;
        private Phase<?> currentPhase;

        public Tester() {
            phases = List.of(
                new Phase<>(new Identifier(NAMESPACE, "next_fast"), this, 600, List.of(
                        new BroadcastMessageBehavior(Text.literal("next_fast"), 20) // 30 times
                ), new SkipTransition(Transition.Type.NEXT)),
                new Phase<>(new Identifier(NAMESPACE, "next_slow"), this, 900, List.of(
                        new BroadcastMessageBehavior(Text.literal("next_slow"), 60) // 15 times
                ), new SkipTransition(Transition.Type.NEXT)),
                new Phase<>(new Identifier(NAMESPACE, "random"), this, 600, List.of(
                        new BroadcastMessageBehavior(Text.literal("random"), 300) // 2 times
                ), new SkipTransition(Transition.Type.RANDOM))
            );
            currentPhase = phases.get(0);
        }

        @Override
        public List<Phase<?>> getPhases() {
            return phases;
        }

        @Override
        public void setCurrentPhase(Phase<?> phase) {
            currentPhase = phase;
        }

        @Override
        public Phase<?> getCurrentPhase() {
            return currentPhase;
        }

        @Override
        public int getCurrentPhaseIndex() {
            return phases.indexOf(currentPhase);
        }
    }
}
