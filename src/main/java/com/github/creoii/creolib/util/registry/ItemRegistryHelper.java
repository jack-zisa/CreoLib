package com.github.creoii.creolib.util.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ItemRegistryHelper {
    /**
     * Register an item
     * Optionally, add the item to an itemgroup after an item
     *
     * @param after - The Item or Block to add after
     * @param group - The ItemGroup to add to
     */
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

    /**
     * Register an item into multiple ItemGroups
     *
     * @param groups ItemGroups to add to
     */
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

    /**
     * @param group - The ItemGroup to add to
     * @param after - Optionally, the item to add after
     */
    public record ItemGroupSettings(ItemGroup group, @Nullable ItemConvertible after) { }
}
