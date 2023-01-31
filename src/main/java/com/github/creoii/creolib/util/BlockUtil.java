package com.github.creoii.creolib.util;

import com.github.creoii.creolib.mixin.block.BlockSettingsAccessor;
import com.github.creoii.creolib.util.registry.CBlockSettings;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;

import java.util.HashMap;
import java.util.Map;

public class BlockUtil {
    public static Map<Block, CBlockSettings> BLOCK_SETTINGS_REPLACED = new HashMap<>();

    public static CBlockSettings getOrDefaultSettings(Block block) {
        if (BLOCK_SETTINGS_REPLACED.containsKey(block)) return BLOCK_SETTINGS_REPLACED.get(block);
        else {
            BLOCK_SETTINGS_REPLACED.put(block, CBlockSettings.toOverrideSettings(block));
            return CBlockSettings.toOverrideSettings(block);
        }
    }

    public static void setHardness(Block block, float hardness) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).hardness(hardness));
    }

    public static float getHardness(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getHardness();
    }

    public static void setResistance(Block block, float resistance) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).resistance(resistance));
    }

    public static float getResistance(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getResistance();
    }

    public static void setStrength(Block block, float hardness, float resistance) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).hardness(hardness).resistance(resistance));
    }

    public static void setSlipperiness(Block block, float slipperiness) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).slipperiness(slipperiness));
    }

    public static float getSlipperiness(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getSlipperiness();
    }

    public static void setVelocityMultiplier(Block block, float multiplier) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).velocityMultiplier(multiplier));
    }

    public static float getVelocityMultiplier(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getVelocityMultiplier();
    }

    public static void setJumpVelocityMultiplier(Block block, float multiplier) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).jumpVelocityMultiplier(multiplier));
    }

    public static float getJumpVelocityMultiplier(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getJumpVelocityMultiplier();
    }

    public static void setSoundGroup(Block block, BlockSoundGroup soundGroup) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).sounds(soundGroup));
    }

    public static BlockSoundGroup getSoundGroup(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getSoundGroup();
    }

    public static void setLuminance(Block block, int luminance) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).luminance(luminance));
    }

    public static int getLuminance(BlockState state) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(state.getBlock())).getLuminance().applyAsInt(state);
    }

    public static boolean hasLuminance(BlockState state) {
        return getLuminance(state) > 0;
    }

    public static void setMapColor(Block block, MapColor color) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).mapColor(color));
    }

    public static MapColor getMapColor(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).getMaterial().getColor();
    }

    public static void setNoCollision(Block block) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).noCollision());
    }

    public static boolean isCollidable(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).isCollidable();
    }

    public static void setToolRequired(Block block) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).requiresTool());
    }

    public static boolean isToolRequired(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).isToolRequired();
    }

    public static void setEmissive(Block block, boolean emissive) {
        setEmissive(block, (state, world, pos) -> emissive);
    }

    public static void setEmissive(Block block, AbstractBlock.ContextPredicate emissive) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).emissiveLighting(emissive));
    }

    public static AbstractBlock.ContextPredicate isEmissive(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).hasEmissiveLighting();
    }

    public static void setPostProcessing(Block block, boolean postProcessing) {
        setPostProcessing(block, (state, world, pos) -> postProcessing);
    }

    public static void setPostProcessing(Block block, AbstractBlock.ContextPredicate postProcessing) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).postProcess(postProcessing));
    }

    public static AbstractBlock.ContextPredicate hasPostProcessing(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).hasPostProcessing();
    }

    public static void setSuffocates(Block block, boolean suffocates) {
        setSuffocates(block, (state, world, pos) -> suffocates);
    }

    public static void setSuffocates(Block block, AbstractBlock.ContextPredicate suffocates) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).suffocates(suffocates));
    }

    public static AbstractBlock.ContextPredicate doesSuffocate(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).suffocates();
    }

    public static void setAllowsSpawning(Block block, boolean allows) {
        setAllowsSpawning(block, (state, world, pos, type) -> allows);
    }

    public static void setAllowsSpawning(Block block, AbstractBlock.TypedContextPredicate<EntityType<?>> allows) {
        BLOCK_SETTINGS_REPLACED.replace(block, (CBlockSettings) getOrDefaultSettings(block).allowsSpawning(allows));
    }

    public static AbstractBlock.TypedContextPredicate<EntityType<?>> allowsSpawning(Block block) {
        return ((BlockSettingsAccessor) getOrDefaultSettings(block)).allowsSpawning();
    }

    public static void setFireSettings(Block block, CBlockSettings.FireSettings fireSettings) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).fireSettings(fireSettings));
    }

    public static CBlockSettings.FireSettings getFireSettings(Block block) {
        return getOrDefaultSettings(block).getFireSettings();
    }

    public static void setStrippedBlock(Block block, Block stripped) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).strippedBlock(stripped));
    }

    public static Block getStrippedBlock(Block block) {
        return getOrDefaultSettings(block).getStrippedBlock();
    }

    public static void setFlattenedState(Block block, BlockState flattened) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).flattenedState(flattened));
    }

    public static BlockState getFlattenedState(Block block) {
        return getOrDefaultSettings(block).getFlattenedState();
    }

    public static void setPathNodeProvider(Block block, LandPathNodeTypesRegistry.PathNodeTypeProvider provider) {
        if (provider instanceof LandPathNodeTypesRegistry.StaticPathNodeTypeProvider staticProvider) {
            BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).staticPathNode(staticProvider));
        } else {
            BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).dynamicPathNode((LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider) provider));
        }
    }

    public static LandPathNodeTypesRegistry.PathNodeTypeProvider getPathNodeProvider(Block block) {
        return getOrDefaultSettings(block).getPathNodeProvider();
    }

    public static void setWaxedBlock(Block block, Block waxed) {
        BLOCK_SETTINGS_REPLACED.replace(block, getOrDefaultSettings(block).waxedBlock(waxed));
    }

    public static Block getWaxedBlock(Block block) {
        return getOrDefaultSettings(block).getWaxed();
    }
}