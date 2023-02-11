package com.github.creoii.creolib.core;

import com.github.creoii.creolib.api.tag.CBiomeTags;
import com.github.creoii.creolib.api.util.CFoodComponent;
import com.github.creoii.creolib.api.util.registry.*;
import com.github.creoii.creolib.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public final class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo";
    public static final String COMMON = "c";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        FeatureRegistry.register();
        PlacementModifierRegistry.register();
        StructurePlacementTypeRegistry.register();
        MaterialConditionRegistry.register();
        DensityFunctionTypeRegistry.register();
        CBiomeTags.loadVanillaFeatureTags();

        BlockRegistryHelper.registerBlock(new Identifier(NAMESPACE, "test_block"), new PillarBlock(CBlockSettings.copy(Blocks.STONE)
                .dripSettings(new DripSettings(Blocks.DIAMOND_BLOCK.getDefaultState(), Fluids.LAVA))
                .flattenedState(Blocks.EMERALD_BLOCK.getDefaultState())
                .strippedBlock(Blocks.OAK_LOG)
                .waxedBlock(Blocks.GOLD_BLOCK)
        ), Items.STONE, ItemGroups.BUILDING_BLOCKS);

        ItemRegistryHelper.registerItem(new Identifier(NAMESPACE, "test_item"), new Item(new CItemSettings()
                .food(new CFoodComponent(1, 1f, false, true, 5))
                .fuelPower(5000)
                .pickupDelay(1000)
                .compostingChance(1f)
        ), Items.EMERALD, ItemGroups.INGREDIENTS);
    }
}
