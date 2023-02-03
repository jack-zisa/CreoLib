package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.tag.CBlockTags;
import com.github.creoii.creolib.tag.CEntityTypeTags;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public boolean noClip;
    @Shadow public abstract BlockState getBlockStateAtPos();
    @Shadow public abstract EntityType<?> getType();

    private static final ImmutableMap<DamageSource, TagKey<EntityType<?>>> DAMAGE_IMMUNITIES = new ImmutableMap.Builder<DamageSource, TagKey<EntityType<?>>>()
            .put(DamageSource.CACTUS, CEntityTypeTags.CACTUS_IMMUNE)
            .put(DamageSource.SWEET_BERRY_BUSH, CEntityTypeTags.SWEET_BERRY_BUSH_IMMUNE)
            .put(DamageSource.STALAGMITE, CEntityTypeTags.DRIPSTONE_IMMUNE)
            .put(DamageSource.LIGHTNING_BOLT, CEntityTypeTags.LIGHTNING_IMMUNE)
            .put(DamageSource.FREEZE, EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES)
            .put(DamageSource.WITHER, CEntityTypeTags.WITHER_IMMUNE)
            .put(DamageSource.MAGIC, CEntityTypeTags.MAGIC_IMMUNE)
            .put(DamageSource.DROWN, CEntityTypeTags.DROWNING_IMMUNE)
            .put(DamageSource.IN_FIRE, CEntityTypeTags.FIRE_IMMUNE)
            .put(DamageSource.ON_FIRE, CEntityTypeTags.FIRE_IMMUNE)
            .build();

    @Inject(method = "tick", at = @At("HEAD"))
    private void creo_lib_blockNoClipTick(CallbackInfo ci) {
        if (getType().isIn(CEntityTypeTags.NO_CLIPPING_ENTITIES)) {
            noClip = !getBlockStateAtPos().isIn(CBlockTags.BLOCKS_NO_CLIPPING);
        }
    }

    @Inject(method = "move", at = @At("HEAD"))
    private void creo_lib_blockNoClipMove(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        if (getType().isIn(CEntityTypeTags.NO_CLIPPING_ENTITIES)) {
            noClip = !getBlockStateAtPos().isIn(CBlockTags.BLOCKS_NO_CLIPPING);
        }
    }

    @Inject(method = "isInsideWall", at = @At("HEAD"))
    private void creo_lib_blockNoClipInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if (getType().isIn(CEntityTypeTags.NO_CLIPPING_ENTITIES)) {
            noClip = !getBlockStateAtPos().isIn(CBlockTags.BLOCKS_NO_CLIPPING);
        }
    }

    @Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
    private void creo_lib_damageImmunities(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (DAMAGE_IMMUNITIES.containsKey(damageSource)) {
            cir.setReturnValue(getType().isIn(DAMAGE_IMMUNITIES.get(damageSource)));
        }
    }
}
