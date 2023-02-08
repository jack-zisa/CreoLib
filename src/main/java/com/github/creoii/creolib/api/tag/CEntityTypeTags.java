package com.github.creoii.creolib.api.tag;

import com.github.creoii.creolib.core.CreoLib;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CEntityTypeTags {
    public static final TagKey<EntityType<?>> NO_CLIPPING_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "no_clipping_entities"));
    public static final TagKey<EntityType<?>> CACTUS_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/cactus"));
    public static final TagKey<EntityType<?>> SWEET_BERRY_BUSH_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/sweet_berry_bush"));
    public static final TagKey<EntityType<?>> DRIPSTONE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/dripstone"));
    public static final TagKey<EntityType<?>> LIGHTNING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/lightning"));
    public static final TagKey<EntityType<?>> DROWNING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/drowning"));
    public static final TagKey<EntityType<?>> MAGIC_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/magic"));
    public static final TagKey<EntityType<?>> FIRE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/fire"));
    public static final TagKey<EntityType<?>> WITHER_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/wither"));
    public static final TagKey<EntityType<?>> DRYOUT_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/dryout"));
    public static final TagKey<EntityType<?>> SUFFOCATION_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/suffocation"));
    public static final TagKey<EntityType<?>> STARVATION_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/starvation"));
    public static final TagKey<EntityType<?>> CRAMMING_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/cramming"));
    public static final TagKey<EntityType<?>> FLY_INTO_WALL_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/fly_into_wall"));
    public static final TagKey<EntityType<?>> FALL_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/fall"));
    public static final TagKey<EntityType<?>> OUT_OF_WORLD_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/out_of_world"));
    public static final TagKey<EntityType<?>> GENERIC_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "damage_immune/generic"));
    public static final TagKey<EntityType<?>> ZOGLIN_IGNORES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "zoglin_ignores"));
    public static final TagKey<EntityType<?>> AVOIDS_TRAPS = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "avoids_traps"));
    public static final TagKey<EntityType<?>> PROJECTILES_IGNORE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "projectiles_ignore"));
    public static final TagKey<EntityType<?>> WALKS_ON_WATER = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "walks_on_water"));
    public static final TagKey<EntityType<?>> WALKS_ON_LAVA = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "walks_on_lava"));
    public static final TagKey<EntityType<?>> COLLIDABLE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "collidable"));
    public static final TagKey<EntityType<?>> UNDEAD = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "undead"));
    public static final TagKey<EntityType<?>> ARTHROPOD = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "arthropod"));
    public static final TagKey<EntityType<?>> AQUATIC = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "aquatic"));
    public static final TagKey<EntityType<?>> ILLAGER = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "illager"));

    /**
     * TODO:
     *
     * Milkable
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
