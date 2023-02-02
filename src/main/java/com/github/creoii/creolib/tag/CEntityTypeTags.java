package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CEntityTypeTags {
    public static final TagKey<EntityType<?>> NO_CLIPPING_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(CreoLib.COMMON, "no_clipping_entities"));

    /**
     * TODO:
     *
     * Milkable
     * Projectiles Ignore
     * Cacuts Immune
     * Berry Bush Immune
     * Dripstone Immune
     * Walks On Fluids
     * Goat Unrammable
     * Wither Resistant
     * Dragon Resistant
     * Undead
     * Aquatic
     * Raiders
     * Arthropods
     * Warden Ignores
     * Zoglin Ignores
     * Nether Native
     * End Native
     * Overworld Native
     * Johnny Ignores
     * Breaks Turtle Eggs
     * Sinks in Water
     * Suffocates in Air
     * Burns in Sunlight
     * Scares Creepers
     * Frog Eatable
     * Froglight Droppers
     * Sculk Ignores
     * Tripwire Ignores
     * Unfishable
     * Bosses
     * Ignores Slipperiness
     * Zombie
     * Zombified
     * Prey/
     */
}
