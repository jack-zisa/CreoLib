package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CBlockTags {
    // region Functional Tags
    public static final TagKey<Block> CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "chests"));
    public static final TagKey<Block> TRAPPED_CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "trapped_chests"));
    public static final TagKey<Block> FURNACES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "furnaces"));
    public static final TagKey<Block> RAVAGER_BREAKABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "ravager_breakable"));
    public static final TagKey<Block> BLOCKS_NO_CLIPPING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "blocks_no_clipping"));
    public static final TagKey<Block> BEACON_BEAM_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "beacon_beam_ignores"));
    public static final TagKey<Block> ATTRACTS_LIGHTNING = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "attracts_lightning"));
    public static final TagKey<Block> SIGNAL_FIRE_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "signal_fire_base_blocks"));
    public static final TagKey<Block> KEEPS_CORAL_ALIVE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "keeps_coral_alive"));
    public static final TagKey<Block> KEEPS_FARMLAND_MOIST = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "keeps_farmland_moist"));
    public static final TagKey<Block> OBSERVER_IGNORES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "observer_ignores"));
    public static final TagKey<Block> COCOA_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "cocoa_plantable_on"));
    public static final TagKey<Block> SUGAR_CANE_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "sugar_cane_plantable_on"));
    public static final TagKey<Block> CACTUS_PLANTABLE_ON = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "cactus_plantable_on"));
    public static final TagKey<Block> CONDUIT_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "conduit_frame_base_blocks"));
    public static final TagKey<Block> NETHER_PORTAL_FRAME_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "nether_portal_frame_base_blocks"));
    public static final TagKey<Block> EATEN_BY_SHEEP = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "eaten_by_sheep"));
    public static final TagKey<Block> PROJECTILES_IGNORE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "projectiles_ignore"));
    // endregion

    // region Non-Functional Tags
    public static final TagKey<Block> CONCRETE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "concrete"));
    public static final TagKey<Block> CONCRETE_POWDER = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "concrete_powder"));
    public static final TagKey<Block> GLAZED_TERRACOTTA = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "glazed_terracotta"));
    public static final TagKey<Block> STAINED_GLASS_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "stained_glass_blocks"));
    public static final TagKey<Block> STAINED_GLASS_PANES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "stained_glass_panes"));
    public static final TagKey<Block> GLASS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "glass"));
    public static final TagKey<Block> GLASS_PANES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "glass_panes"));
    public static final TagKey<Block> SANDSTONE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "sandstone"));
    public static final TagKey<Block> RED_SANDSTONE = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "red_sandstone"));
    public static final TagKey<Block> MINERAL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "mineral_blocks"));
    public static final TagKey<Block> DEAD_CORAL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "dead_coral_blocks"));
    public static final TagKey<Block> DEAD_WALL_CORALS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "dead_wall_corals"));
    public static final TagKey<Block> DEAD_CORAL_PLANTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "dead_coral_plants"));
    public static final TagKey<Block> DEAD_CORALS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.COMMON, "dead_corals"));
    //endregion

    /**
     * TODO:
     *
     * Carver Carvable
     * Dead Coral Blocks
     * Dead Coral
     * Dead Coral Fans
     * Dead Coral Plants
     * Observer Ignores*
     * Nylium Replaceables
     * Nether Portal Base Blocks*
     * Projectiles Ignore
     * Immovable
     * Silverfish Infested
     * Silverfish Disturbers
     * Scares Hoglins
     * Anvil Softeners
     * Dye Color/
     */
}
