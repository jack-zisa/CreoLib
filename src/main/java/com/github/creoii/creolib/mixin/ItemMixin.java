package com.github.creoii.creolib.mixin;

import com.github.creoii.creolib.util.CreoFoodComponent;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow @Nullable public abstract FoodComponent getFoodComponent();

    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo_lib_foodEatingTimes(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (getFoodComponent() instanceof CreoFoodComponent foodComponent) {
            cir.setReturnValue(foodComponent.getEatTime());
        }
    }
}
