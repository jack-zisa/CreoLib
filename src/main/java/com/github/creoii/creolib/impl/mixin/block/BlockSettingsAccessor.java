package com.github.creoii.creolib.impl.mixin.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@Mixin(AbstractBlock.Settings.class)
public interface BlockSettingsAccessor {
    @Accessor("material")
    Material getMaterial();
    @Accessor("soundGroup")
    BlockSoundGroup getSoundGroup();
    @Accessor("luminance")
    ToIntFunction<BlockState> getLuminance();
    @Accessor("resistance")
    float getResistance();
    @Accessor("hardness")
    float getHardness();
    @Accessor("toolRequired")
    boolean isToolRequired();
    @Accessor("slipperiness")
    float getSlipperiness();
    @Accessor("velocityMultiplier")
    float getVelocityMultiplier();
    @Accessor("jumpVelocityMultiplier")
    float getJumpVelocityMultiplier();
    @Accessor("opaque")
    boolean isOpaque();
    @Accessor("offsetType")
    Function<BlockState, AbstractBlock.OffsetType> getOffsetType();
    @Accessor("collidable")
    boolean isCollidable();
    @Accessor("allowsSpawningPredicate")
    AbstractBlock.TypedContextPredicate<EntityType<?>> allowsSpawning();
    @Accessor("solidBlockPredicate")
    AbstractBlock.ContextPredicate isSolidBlock();
    @Accessor("suffocationPredicate")
    AbstractBlock.ContextPredicate suffocates();
    @Accessor("blockVisionPredicate")
    AbstractBlock.ContextPredicate blocksVision();
    @Accessor("postProcessPredicate")
    AbstractBlock.ContextPredicate hasPostProcessing();
    @Accessor("emissiveLightingPredicate")
    AbstractBlock.ContextPredicate hasEmissiveLighting();
}
