package com.github.creoii.creolib.mixin.item;

import net.minecraft.item.Item;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface ItemSettingsDuck {
    Item.Settings getItemSettings();
}
