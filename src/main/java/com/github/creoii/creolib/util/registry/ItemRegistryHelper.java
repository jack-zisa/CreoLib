package com.github.creoii.creolib.util.registry;

import com.github.creoii.creolib.util.CFoodComponent;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ItemRegistryHelper {
    public static void registerItem(Identifier id, Item item, @Nullable ItemConvertible after, @Nullable ItemGroup group) {
        Registry.register(Registries.ITEM, id, item);
        if (group != null) {
            if (after != null) {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(after, item));
            } else {
                ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
            }
        }
    }

    public static void registerItem(Identifier id, Item item, ItemGroupSettings... groups) {
        Registry.register(Registries.ITEM, id, item);
        if (groups != null) {
            for (ItemGroupSettings settings : groups) {
                if (settings.after() != null) {
                    ItemGroupEvents.modifyEntriesEvent(settings.group()).register(entries -> entries.addAfter(settings.after(), item));
                } else {
                    ItemGroupEvents.modifyEntriesEvent(settings.group()).register(entries -> entries.add(item));
                }
            }
        }
    }

    public record ItemGroupSettings(ItemGroup group, @Nullable ItemConvertible after) { }

    public static class CItemSettings extends FabricItemSettings {
        private ItemGroupSettings[] itemGroups;
        private int compostingChance;
        private int fuelPower;
        private boolean villagerCollectable;
        private int villagerFoodValue;
        private int pickupDelay;

        public CItemSettings compostingChance(int chance) {
            compostingChance = chance;
            return this;
        }

        public CItemSettings fuelPower(int power) {
            fuelPower = power;
            return this;
        }

        public CItemSettings food(CFoodComponent foodComponent) {
            super.food(foodComponent);
            return this;
        }

        public CItemSettings itemGroups(ItemGroupSettings[] itemGroups) {
            this.itemGroups = itemGroups;
            return this;
        }

        public CItemSettings villagerCollectable() {
            villagerCollectable = true;
            return this;
        }

        public CItemSettings villagerFood(int value) {
            villagerFoodValue = value;
            return this;
        }

        public CItemSettings pickupDelay(int delay) {
            pickupDelay = delay;
            return this;
        }

        public int getCompostingChance() {
            return compostingChance;
        }

        public int getFuelPower() {
            return fuelPower;
        }

        public ItemGroupSettings[] getItemGroups() {
            return itemGroups;
        }

        public int getVillagerFoodValue() {
            return villagerFoodValue;
        }

        public boolean isVillagerCollectable() {
            return villagerCollectable;
        }

        public int getPickupDelay() {
            return pickupDelay;
        }
    }
}
