package com.github.creoii.creolib.core.registry;

import com.github.creoii.creolib.core.CreoLib;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class AttributeRegistry {
    public static final EntityAttribute GENERIC_GRAVITY = new ClampedEntityAttribute("attribute.name.generic.gravity", .08d, -16d, 16d).setTracked(true);
    public static final EntityAttribute GENERIC_SWIM_SPEED = new ClampedEntityAttribute("attribute.name.generic.swim_speed", 1d, 0d, 1024d).setTracked(true);
    public static final EntityAttribute GENERIC_ATTACK_RANGE = new ClampedEntityAttribute("attribute.name.generic.attack_range", 3f, 0f, 256f).setTracked(true);
    public static final EntityAttribute PLAYER_REACH_DISTANCE = new ClampedEntityAttribute("attribute.name.player.reach_distance", 4.5f, 0f, 256f).setTracked(true);
    public static final EntityAttribute PLAYER_BLOCK_PLACE_SPEED = new ClampedEntityAttribute("attribute.name.player.block_place_speed", 4, 0, 256f).setTracked(true);
    public static final EntityAttribute PLAYER_BLOCK_BREAK_SPEED = new ClampedEntityAttribute("attribute.name.player.block_break_speed", 5, 0, 256f).setTracked(true);

    public static void register() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "generic.gravity"), GENERIC_GRAVITY);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "generic.swim_speed"), GENERIC_SWIM_SPEED);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "generic.attack_range"), GENERIC_ATTACK_RANGE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "player.reach_distance"), PLAYER_REACH_DISTANCE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "player.block_place_cooldown"), PLAYER_BLOCK_PLACE_SPEED);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "player.block_break_cooldown"), PLAYER_BLOCK_BREAK_SPEED);
    }
}
