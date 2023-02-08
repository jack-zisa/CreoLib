package com.github.creoii.creolib.core.registry;

import com.github.creoii.creolib.core.CreoLib;
import com.github.creoii.creolib.api.world.surface.FastNoiseThresholdMaterialCondition;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class MaterialConditionRegistry {
    public static void register() {
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoLib.NAMESPACE, "fast_noise"), FastNoiseThresholdMaterialCondition.CODEC_HOLDER.codec());
    }
}
