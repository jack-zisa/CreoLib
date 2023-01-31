package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import com.github.creoii.creolib.world.densityfunction.FastNoiseDensityFunction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DensityFunctionTypeRegistry {
    public static void register() {
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "fast_noise"), FastNoiseDensityFunction.CODEC_HOLDER.codec());
    }
}
