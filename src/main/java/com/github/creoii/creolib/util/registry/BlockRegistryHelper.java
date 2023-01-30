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
            FlammableBlockRegistry.getDefaultInstance().add(block, settings.fireSettings.burnChance, settings.fireSettings.spreadChance);
            StrippableBlockRegistry.register(block, settings.strippedBlock);
            FlattenableBlockRegistry.register(block, settings.flattenedState);
            OxidizableBlocksRegistry.registerWaxableBlockPair(block, settings.waxed);
            LandPathNodeTypesRegistry.PathNodeTypeProvider provider = settings.pathNodeProvider;
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

    public static class CBlockSettings extends FabricBlockSettings {
        private FireSettings fireSettings;
        private Block strippedBlock;
        private BlockState flattenedState;
        private LandPathNodeTypesRegistry.PathNodeTypeProvider pathNodeProvider;
        private Block waxed;

        public CBlockSettings(Material material, MapColor color) {
            super(material, color);
        }

        public CBlockSettings(AbstractBlock.Settings settings) {
            super(settings);
        }

        public CBlockSettings(FabricBlockSettings settings) {
            super(settings);
        }

        public static CBlockSettings toOverrideSettings(Block block) {
            CBlockSettings settings = new CBlockSettings(block.getDefaultState().getMaterial(), block.getDefaultMapColor());
            settings.strength(block.getHardness(), block.getBlastResistance());
            if (block.hasRandomTicks(block.getDefaultState())) settings.ticksRandomly();
            settings.sounds(block.getSoundGroup(block.getDefaultState()));
            settings.slipperiness(block.getSlipperiness());
            settings.velocityMultiplier(block.getVelocityMultiplier());
            settings.jumpVelocityMultiplier(block.getVelocityMultiplier());
            FlammableBlockRegistry.Entry entry = FlammableBlockRegistry.getDefaultInstance().get(block);
            settings.fireSettings(new FireSettings(entry.getBurnChance(), entry.getSpreadChance()));
            settings.strippedBlock(AxeItemAccessor.getStrippedBlocks().get(block));
            settings.flattenedState(ShovelItemAccessor.getPathStates().get(block));
            LandPathNodeTypesRegistry.PathNodeTypeProvider provider = LandPathNodeTypesRegistry.getPathNodeTypeProvider(block);
            if (provider instanceof LandPathNodeTypesRegistry.StaticPathNodeTypeProvider staticProvider) {
                settings.staticPathNode(staticProvider);
            } else {
                settings.dynamicPathNode((LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider) provider);
            }
            settings.waxedBlock(HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().get(block));
            return settings;
        }

        public CBlockSettings fireSettings(FireSettings fireSettings) {
            this.fireSettings = fireSettings;
            return this;
        }

        public CBlockSettings strippedBlock(Block strippedBlock) {
            this.strippedBlock = strippedBlock;
            return this;
        }

        public CBlockSettings flattenedState(BlockState state) {
            this.flattenedState = state;
            return this;
        }

        public CBlockSettings staticPathNode(LandPathNodeTypesRegistry.StaticPathNodeTypeProvider provider) {
            this.pathNodeProvider = provider;
            return this;
        }

        public CBlockSettings dynamicPathNode(LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider provider) {
            this.pathNodeProvider = provider;
            return this;
        }

        public CBlockSettings waxedBlock(Block waxed) {
            this.waxed = waxed;
            return this;
        }

        public FireSettings getFireSettings() {
            return fireSettings;
        }

        public Block getStrippedBlock() {
            return strippedBlock;
        }

        public BlockState getFlattenedState() {
            return flattenedState;
        }

        public LandPathNodeTypesRegistry.PathNodeTypeProvider getPathNodeProvider() {
            return pathNodeProvider;
        }

        public Block getWaxed() {
            return waxed;
        }

        public record FireSettings(int burnChance, int spreadChance) {}
    }
}
