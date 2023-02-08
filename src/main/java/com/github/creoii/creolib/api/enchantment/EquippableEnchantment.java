package com.github.creoii.creolib.api.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class EquippableEnchantment extends Enchantment {
    protected EquippableEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    public abstract void onEquip(World world, Entity entity, ItemStack stack, EquipmentSlot slot, int level);

    public abstract void onUnequip(World world, Entity entity, ItemStack stack, EquipmentSlot slot, int level);
}
