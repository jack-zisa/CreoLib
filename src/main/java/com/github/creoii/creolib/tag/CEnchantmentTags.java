package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CEnchantmentTags {
    // region Functional Tags
    public static final TagKey<Enchantment> CURSED = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "cursed"));
    public static final TagKey<Enchantment> TREASURE = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "treasure"));
    public static final TagKey<Enchantment> NOT_OFFERED_BY_LIBRARIANS = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "not_offered_by_librarians"));
    public static final TagKey<Enchantment> NOT_RANDOMLY_SELECTABLE = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "not_randomly_selectable"));
    public static final TagKey<Enchantment> GRINDSTONE_IGNORES = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "grindstone_ignores"));
    // endregion
    // region Non-Functional Tags
    public static final TagKey<Enchantment> PROTECTION = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "protection"));
    public static final TagKey<Enchantment> SHARPNESS = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(CreoLib.COMMON, "sharpness"));
    // endregion

    /**
     * TODO:
     *
     * Allowed/
     */
}
