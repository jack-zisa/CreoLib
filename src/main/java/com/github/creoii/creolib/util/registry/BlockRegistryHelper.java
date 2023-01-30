package com.github.creoii.creolib.util.registry;

import com.github.creoii.creolib.mixin.block.AbstractBlockAccessor;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.*;
import net.fabricmc.fabric.mixin.content.registry.AxeItemAccessor;
import net.fabricmc.fabric.mixin.content.registry.ShovelItemAccessor;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BlockRegistryHelper {
    public static void registerBlock(Identifier id, Block block) {
        Registry.register(Registries.BLOCK, id, block);
        if (((AbstractBlockAccessor) block).creo_lib_getBlockSettings() instanceof CBlockSettings settings) {
            FlammableBlockRegistry.getDefaultInstance().add(block, settings.getFireSettings().burnChance(), settings.getFireSettings().spreadChance());
            StrippableBlockRegistry.register(block, settings.getStrippedBlock());
            FlattenableBlockRegistry.register(block, settings.getFlattenedState());
            OxidizableBlocksRegistry.registerWaxableBlockPair(block, settings.getWaxed());
            LandPathNodeTypesRegistry.PathNodeTypeProvider provider = settings.getPathNodeProvider();
            if (provider instanceof LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider dynamic) {
                LandPathNodeTypesRegistry.registerDynamic(block, dynamic);
            } else if (provider instanceof LandPathNodeTypesRegistry.StaticPathNodeTypeProvider staticProvider)
                LandPathNodeTypesRegistry.register(block, staticProvider);
        }
    }

    public static void registerBlock(Identifier id, Block block, ItemGroup group) {
        registerBlock(id, block, new ItemRegistryHelper.ItemGroupSettings(group, null));
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ItemConvertible after, ItemGroup group) {
        registerBlock(id, block, new ItemRegistryHelper.ItemGroupSettings(group, after));
    }

    public static void registerBlock(Identifier id, Block block, @Nullable ItemRegistryHelper.ItemGroupSettings... groups) {
        registerBlock(id, block);
        if (groups != null) {
            ItemRegistryHelper.registerItem(id, new BlockItem(block, new FabricItemSettings()), groups);
        }
    }
}
