package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CBlockTags {
    public static final TagKey<Block> CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "chests"));
    public static final TagKey<Block> TRAPPED_CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "trapped_chests"));
    public static final TagKey<Block> FURNACES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "furnaces"));

    /**
     * TODO:
     *
     * Ravager Breakable
     * Beacon Beam Ignores
     * Blocks No Clip
     * Attracts Lightning
     * Signal Fire Base Blocks
     * Carver Carvable
     * Concrete
     * Concrete Powder
     * Terracotta
     * Glazed Terracotta
     * Stained Glass
     * Stained Glass Panes
     * Sandstone
     * Dead Coral Blocks
     * Dead Coral
     * Dead Coral Fans
     * Dead Coral Plants
     * Mineral Blocks
     * Observer Ignores
     * Keeps Coral Alive
     * Wets Farmland
     * Pollinates Bees
     * Nylium Replaceables
     * Nether Portal Base Blocks
     * Projectiles Ignore
     * Dragon Immune
     * Wither Immune
     * Immovable
     * Silverfish Infested
     * Silverfish Disturbers
     * Scares Hoglins
     * Anvil Softeners
     * Sheep Eatable Blocks
     * Cocoa Placeable On
     * Sugar Placeable On
     * Cactus Placeable On
     * Dye Color/
     */
}
