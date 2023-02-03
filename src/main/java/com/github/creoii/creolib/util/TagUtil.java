package com.github.creoii.creolib.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

public class TagUtil {
    public static boolean isEnchantmentIn(Enchantment enchantment, TagKey<Enchantment> tag) {
        RegistryEntry<Enchantment> entry = Registries.ENCHANTMENT.getEntry(enchantment);
        if (entry.hasKeyAndValue()) {
            return entry.isIn(tag);
        } return false;
    }
}
