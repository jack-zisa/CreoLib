package com.github.creoii.creolib.core.duck;

import com.github.creoii.creolib.api.util.registry.DripSettings;
import com.github.creoii.creolib.api.util.registry.FireSettings;

public interface AbstractBlockDuck {
    FireSettings getFireSettings();

    DripSettings getDripSettings();
}
