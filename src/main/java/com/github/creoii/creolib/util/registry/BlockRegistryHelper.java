package com.github.creoii.creolib.util.registry;

import com.github.creoii.creolib.mixin.AbstractBlockAccessor;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BlockRegistryHelper {
    public static void registerBlock(Identifier id, Block block) {
        Registry.register(Registries.BLOCK, id, block);
        if (((AbstractBlockAccessor) block).creo_lib_getBlockSettings() instanceof CreoBlockSettings settings) {
            FlammableBlockRegistry.getDefaultInstance().add(block, settings.fireSettings.burnChance, settings.fireSettings.spreadChance);
            StrippableBlockRegistry.register(block, settings.strippedBlock);
            FlattenableBlockRegistry.register(block, settings.flattenedState);
            OxidizableBlocksRegistry.registerWaxableBlockPair(block, settings.waxed);
            if (settings.pathNodeType != null) {
                LandPathNodeTypesRegistry.register(block, settings.pathNodeType, null);
            } else {
                LandPathNodeTypesRegistry.PathNodeTypeProvider provider = settings.pathNodeProvider;
                if (provider instanceof LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider dynamic) {
                    LandPathNodeTypesRegistry.registerDynamic(block, dynamic);
                } else
                    LandPathNodeTypesRegistry.register(block, (LandPathNodeTypesRegistry.StaticPathNodeTypeProvider) provider);
            }
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

    public static class CreoBlockSettings extends FabricBlockSettings {
        private FireSettings fireSettings;
        private Block strippedBlock;
        private BlockState flattenedState;
        private PathNodeType pathNodeType;
        private LandPathNodeTypesRegistry.PathNodeTypeProvider pathNodeProvider;
        private Block waxed;

        public CreoBlockSettings(Material material, MapColor color) {
            super(material, color);
        }

        public CreoBlockSettings(AbstractBlock.Settings settings) {
            super(settings);
        }

        public CreoBlockSettings(FabricBlockSettings settings) {
            super(settings);
        }

        public CreoBlockSettings fireSettings(FireSettings fireSettings) {
            this.fireSettings = fireSettings;
            return this;
        }

        public CreoBlockSettings strippedBlock(Block strippedBlock) {
            this.strippedBlock = strippedBlock;
            return this;
        }

        public CreoBlockSettings flattenedState(BlockState state) {
            this.flattenedState = state;
            return this;
        }

        public CreoBlockSettings pathNodeType(PathNodeType type) {
            this.pathNodeType = type;
            return this;
        }

        public CreoBlockSettings staticPathNode(LandPathNodeTypesRegistry.StaticPathNodeTypeProvider provider) {
            this.pathNodeProvider = provider;
            return this;
        }

        public CreoBlockSettings dynamicPathNode(LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider provider) {
            this.pathNodeProvider = provider;
            return this;
        }

        public CreoBlockSettings waxedBlock(Block waxed) {
            this.waxed = waxed;
            return this;
        }

        public record FireSettings(int burnChance, int spreadChance) {}
    }
}
