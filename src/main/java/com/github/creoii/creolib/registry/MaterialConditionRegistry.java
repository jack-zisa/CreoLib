package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.surfacerule.FastNoiseThresholdMaterialCondition;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MaterialConditionRegistry {
    public static void register() {
        Registry.register(Registries.MATERIAL_CONDITION, new Identifier(CreoLib.NAMESPACE, "fast_noise"), FastNoiseThresholdMaterialCondition.CODEC_HOLDER.codec());
    }
}
