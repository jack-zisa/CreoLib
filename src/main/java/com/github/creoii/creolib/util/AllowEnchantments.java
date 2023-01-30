package com.github.creoii.creolib.util;

import net.minecraft.enchantment.Enchantment;

import java.util.function.Predicate;

public interface AllowEnchantments {
    Predicate<Enchantment> getAllowedEnchantments();
}
