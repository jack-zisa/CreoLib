package com.github.creoii.creolib.phase;

import java.util.List;

public interface PhaseHolder {
    List<Phase<?>> getPhases();

    void setCurrentPhase(Phase<?> phase);

    Phase<?> getCurrentPhase();

    int getCurrentPhaseIndex();
}
