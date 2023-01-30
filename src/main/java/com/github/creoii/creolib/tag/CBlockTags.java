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
}
