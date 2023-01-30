package com.github.creoii.creolib.tag;

import com.github.creoii.creolib.CreoLib;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CreoBlockTags {
    public static final TagKey<Block> CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.NAMESPACE, "chests"));
    public static final TagKey<Block> TRAPPED_CHESTS = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.NAMESPACE, "trapped_chests"));
    public static final TagKey<Block> FURNACES = TagKey.of(RegistryKeys.BLOCK, new Identifier(CreoLib.NAMESPACE, "furnaces"));
}
