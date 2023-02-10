package com.github.creoii.creolib.core.registry;

import com.github.creoii.creolib.api.world.densityfunction.*;
import com.github.creoii.creolib.core.CreoLib;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class DensityFunctionTypeRegistry {
    public static void register() {
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "fast_noise"), FastNoiseDensityFunction.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "swap"), SwapDensityFunction.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "sin"), TrigonometricDensityFunction.Sin.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "cos"), TrigonometricDensityFunction.Cos.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "tan"), TrigonometricDensityFunction.Tan.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "x_coord"), CoordinateDensityFunction.GetX.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "y_coord"), CoordinateDensityFunction.GetY.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "z_coord"), CoordinateDensityFunction.GetZ.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "random"), RandomDensityFunction.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "x_clamped_gradient"), XClampedGradientDensityFunction.CODEC);
        Registry.register(Registries.DENSITY_FUNCTION_TYPE, new Identifier(CreoLib.NAMESPACE, "z_clamped_gradient"), ZClampedGradientDensityFunction.CODEC);
    }
}
