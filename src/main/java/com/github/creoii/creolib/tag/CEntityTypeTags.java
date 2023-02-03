package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CEntityTypeTags {
    public static final TagKey<EntityType<?>> NO_CLIPPING_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "no_clipping_entities"));
    public static final TagKey<EntityType<?>> CACTUS_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/cactus"));
    public static final TagKey<EntityType<?>> SWEET_BERRY_BUSH_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/sweet_berry_bush"));
    public static final TagKey<EntityType<?>> DRIPSTONE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/dripstone"));
    public static final TagKey<EntityType<?>> LIGHTNING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/lightning"));
    public static final TagKey<EntityType<?>> DROWNING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/drowning"));
    public static final TagKey<EntityType<?>> MAGIC_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/magic"));
    public static final TagKey<EntityType<?>> FIRE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/fire"));
    public static final TagKey<EntityType<?>> WITHER_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "immune/wither"));
    public static final TagKey<EntityType<?>> ZOGLIN_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "zoglin_ignores"));
    public static final TagKey<EntityType<?>> TRIPWIRE_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "tripwire_ignores"));
    public static final TagKey<EntityType<?>> PROJECTILES_IGNORE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "projectiles_ignore"));

    /**
     * TODO:
     *
     * Milkable
     * Projectiles Ignore
     * Walks On Fluids
     * Goat Unrammable
     * Wither Resistant
     * Dragon Resistant
     * Undead
     * Aquatic
     * Raiders
     * Arthropods
     * Warden Ignores
     * Nether Native
     * End Native
     * Overworld Native
     * Johnny Ignores
     * Breaks Turtle Eggs
     * Sinks in Water
     * Suffocates in Air
     * Burns in Sunlight
     * Scares Creepers
     * Froglight Droppers
     * Sculk Ignores
     * Unfishable
     * Bosses
     * Ignores Slipperiness
     * Zombie
     * Zombified
     * Prey/
     */
}
