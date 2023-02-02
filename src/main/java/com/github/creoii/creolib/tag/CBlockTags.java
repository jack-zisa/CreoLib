package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CBlockTags {
    // region Functional Tags
    public static final TagKey<Block> CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "chests"));
    public static final TagKey<Block> TRAPPED_CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "trapped_chests"));
    public static final TagKey<Block> FURNACES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "furnaces"));
    public static final TagKey<Block> RAVAGER_BREAKABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "ravager_breakable"));
    public static final TagKey<Block> BLOCKS_NO_CLIPPING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "blocks_no_clipping"));
    public static final TagKey<Block> BEACON_BEAM_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "beacon_beam_ignores"));
    // only functional if the block is added to the Lightning Rod POI states
    public static final TagKey<Block> ATTRACTS_LIGHTNING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "attracts_lightning"));
    public static final TagKey<Block> SIGNAL_FIRE_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "signal_fire_base_blocks"));
    public static final TagKey<Block> KEEPS_CORAL_ALIVE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "keeps_coral_alive"));
    public static final TagKey<Block> KEEPS_FARMLAND_MOIST = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "keeps_farmland_moist"));
    // currently only ignores on block place
    public static final TagKey<Block> OBSERVER_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "observer_ignores"));
    public static final TagKey<Block> COCOA_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "cocoa_plantable_on"));
    public static final TagKey<Block> SUGAR_CANE_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "sugar_cane_plantable_on"));
    public static final TagKey<Block> CACTUS_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "cactus_plantable_on"));
    public static final TagKey<Block> CONDUIT_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "conduit_frame_base_blocks"));
    // not functional
    public static final TagKey<Block> NETHER_PORTAL_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "nether_portal_base_blocks"));
    public static final TagKey<Block> EATEN_BY_SHEEP = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON_NAMESPACE, "eaten_by_sheep"));
    // endregion

    /**
     * TODO:
     *
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
     * Observer Ignores*
     * Nylium Replaceables
     * Nether Portal Base Blocks*
     * Projectiles Ignore
     * Wither Immune
     * Immovable
     * Silverfish Infested
     * Silverfish Disturbers
     * Scares Hoglins
     * Anvil Softeners
     * Impermeable
     * Dye Color/
     */
}
