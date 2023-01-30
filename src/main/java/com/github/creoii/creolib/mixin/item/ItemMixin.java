package com.github.creoii.creolib.mixin.item;

import com.github.creoii.creolib.util.CFoodComponent;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemSettingsAccessor {
    @Shadow @Nullable public abstract FoodComponent getFoodComponent();

    private Item.Settings settings;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void creo_lib_accessItemSettings(Item.Settings settings, CallbackInfo ci) {
        this.settings = settings;
    }

    @Override
    public Item.Settings getItemSettings() {
        return settings;
    }

    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void creo_lib_foodEatingTimes(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (getFoodComponent() instanceof CFoodComponent foodComponent) {
            cir.setReturnValue(foodComponent.getEatTime());
        }
    }
}
