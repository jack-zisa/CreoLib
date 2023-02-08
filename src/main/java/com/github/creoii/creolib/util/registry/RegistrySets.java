package com.github.creoii.creolib.util.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public final class RegistrySets {
    /**
     * Registers most blocks required in a wood set.
     *      Does not register signs, leaves, saplings, or boats.
     *      This is only for generic wood types, any special properties require normal registration.
     *
     * @param namespace - The namespace of your mod, used for ids.
     * @param name - The name of the wood type, used for ids.
     * @param barkColor - The MapColor of the wood set bark.
     * @param woodColor - The MapColor of the wood set wood.
     * @param after - Item to place after in item groups
     */
    public static WoodSet createWoodSet(String namespace, String name, MapColor barkColor, MapColor woodColor, @Nullable ItemConvertible after, @Nullable ItemConvertible logAfter) {
        return createWoodSet(namespace, name, barkColor, woodColor, after, logAfter, true);
    }

    public static WoodSet createWoodSet(String namespace, String name, MapColor barkColor, MapColor woodColor, @Nullable ItemConvertible after, @Nullable ItemConvertible logAfter, boolean includeLogs) {
        Block strippedLog = null;
        Block log = null;
        Block strippedWood = null;
        Block wood = null;
        if (includeLogs) {
            strippedLog = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor));
            log = new PillarBlock(FabricBlockSettings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor).strength(2f).sounds(BlockSoundGroup.WOOD));
            strippedWood = new PillarBlock(FabricBlockSettings.copy(strippedLog));
            wood = new PillarBlock(FabricBlockSettings.copy(strippedLog).mapColor(barkColor));
        }
        Block planks = new Block(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD).mapColor(woodColor));
        Block slab = new SlabBlock(FabricBlockSettings.copy(planks));
        Block stairs = new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copy(planks));
        Block fence = new FenceBlock(FabricBlockSettings.copy(planks));
        Block fenceGate = new FenceGateBlock(FabricBlockSettings.copy(planks), SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundEvents.BLOCK_FENCE_GATE_OPEN);
        Block button = new ButtonBlock(FabricBlockSettings.copy(planks), 30, true, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
        Block pressurePlate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(planks), SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON);
        Block door = new DoorBlock(FabricBlockSettings.copy(planks).strength(3f).sounds(BlockSoundGroup.WOOD).nonOpaque(), SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN);
        Block trapdoor = new TrapdoorBlock(FabricBlockSettings.copy(planks).strength(3f).nonOpaque().allowsSpawning((state, world, pos, type) -> false), SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN);
        return new WoodSet(namespace, name, after, logAfter, log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, button, pressurePlate, door, trapdoor);
    }

    public record WoodSet(String namespace, String name, @Nullable ItemConvertible after, @Nullable ItemConvertible logAfter, @Nullable Block log, @Nullable Block strippedLog, @Nullable Block wood, @Nullable Block strippedWood, Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block button, Block pressurePlate, Block door, Block trapdoor) {
        public void register() {
            if (after != null) {
                if (log != null)
                    if (logAfter != null) {
                        BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_log"), log, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, after), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, logAfter));
                    } else {
                        BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_log"), log, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, after), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, null));
                    }
                if (strippedLog != null)
                    BlockRegistryHelper.registerBlock(new Identifier(namespace, "stripped_" + name + "_log"), strippedLog, log, ItemGroups.BUILDING_BLOCKS);
                if (wood != null)
                    BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_wood"), wood, strippedLog, ItemGroups.BUILDING_BLOCKS);
                if (strippedWood != null)
                    BlockRegistryHelper.registerBlock(new Identifier(namespace, "stripped_" + name + "_wood"), strippedWood, wood, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_planks"), planks, strippedWood, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_stairs"), stairs, planks, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_slab"), slab, stairs, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_fence"), fence, slab, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_fence_gate"), fenceGate, fence, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_door"), door, fenceGate, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_trapdoor"), trapdoor, door, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_pressure_plate"), pressurePlate, trapdoor, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_button"), button, pressurePlate, ItemGroups.BUILDING_BLOCKS);
            } else {
                if (log != null)
                    if (logAfter != null) {
                        BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_log"), log, new ItemRegistryHelper.ItemGroupSettings(ItemGroups.BUILDING_BLOCKS, null), new ItemRegistryHelper.ItemGroupSettings(ItemGroups.NATURAL, logAfter));
                    } else {
                        BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_log"), log, ItemGroups.BUILDING_BLOCKS);
                    }
                if (strippedLog != null)
                    BlockRegistryHelper.registerBlock(new Identifier(namespace, "stripped_" + name + "_log"), strippedLog, ItemGroups.BUILDING_BLOCKS);
                if (wood != null)
                    BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_wood"), wood, ItemGroups.BUILDING_BLOCKS);
                if (strippedWood != null)
                    BlockRegistryHelper.registerBlock(new Identifier(namespace, "stripped_" + name + "_wood"), strippedWood, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_planks"), planks, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_stairs"), stairs, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_slab"), slab, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_fence"), fence, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_fence_gate"), fenceGate, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_door"), door, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_trapdoor"), trapdoor, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_pressure_plate"), pressurePlate, ItemGroups.BUILDING_BLOCKS);
                BlockRegistryHelper.registerBlock(new Identifier(namespace, name + "_button"), button, ItemGroups.BUILDING_BLOCKS);
            }
        }
    }

    public record StoneSet(String namespace, String name, @Nullable ItemConvertible after, @Nullable ItemConvertible stoneAfter, @Nullable Block stone, Block stairs, Block slab, Block wall, @Nullable Block cobbled, @Nullable Block cobbledStairs, @Nullable Block cobbledSlab, @Nullable Block cobbledWall, @Nullable Block bricks, @Nullable Block brickStairs, @Nullable Block brickSlab, @Nullable Block brickWall, @Nullable Block tiles, @Nullable Block tileStairs, @Nullable Block tileSlab, @Nullable Block tileWall, @Nullable Block polished, @Nullable Block chiseled, @Nullable Block pillar) {
        public void register() {
            if (after != null) {
            } else {
            }
        }
    }
}
