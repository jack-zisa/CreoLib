package com.github.creoii.creolib.mixin.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Settings.class)
public interface ItemSettingsAccessor {
    @Accessor("foodComponent")
    FoodComponent getFoodComponent();
}
