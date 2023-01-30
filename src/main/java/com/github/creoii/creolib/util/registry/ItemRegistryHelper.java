package com.github.creoii.creolib.util.registry;

import com.github.creoii.creolib.util.CreoFoodComponent;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
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

    public static class CreoItemSettings extends FabricItemSettings {
        private ItemGroupSettings[] itemGroups;
        private int compostingChance;
        private int fuelPower;
        private boolean villagerCollectable;
        private int villagerFoodValue;

        public CreoItemSettings compostingChance(int chance) {
            compostingChance = chance;
            return this;
        }

        public CreoItemSettings fuelPower(int power) {
            fuelPower = power;
            return this;
        }

        public CreoItemSettings food(CreoFoodComponent foodComponent) {
            super.food(foodComponent);
            return this;
        }

        public CreoItemSettings itemGroups(ItemGroupSettings[] itemGroups) {
            this.itemGroups = itemGroups;
            return this;
        }

        public CreoItemSettings villagerCollectable() {
            villagerCollectable = true;
            return this;
        }

        public CreoItemSettings villagerFood(int value) {
            villagerFoodValue = value;
            return this;
        }
    }
}
