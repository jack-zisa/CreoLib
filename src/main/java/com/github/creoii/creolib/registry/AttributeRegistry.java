package com.github.creoii.creolib.registry;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AttributeRegistry {
    public static final EntityAttribute GENERIC_GRAVITY = new ClampedEntityAttribute("attribute.name.generic.gravity", .08d, -8d, 8d).setTracked(true);
    public static final EntityAttribute GENERIC_SWIM_SPEED = new ClampedEntityAttribute("attribute.name.generic.swim_speed", 1.0D, 0.0D, 1024.0D).setTracked(true);

    public static void register() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "generic.gravity"), GENERIC_GRAVITY);
        Registry.register(Registries.ATTRIBUTE, new Identifier(CreoLib.NAMESPACE, "generic.swim_speed"), GENERIC_SWIM_SPEED);
    }
}
