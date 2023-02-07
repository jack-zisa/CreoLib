package com.github.creoii.creolib.util;

import net.minecraft.enchantment.Enchantment;

import java.util.function.Predicate;

/**
 * Allows an item to specify the enchantments it can support
 * This is an alternate to {@link net.minecraft.enchantment.EnchantmentTarget}
 */
public interface AllowEnchantments {
    Predicate<Enchantment> getAllowedEnchantments();
}
