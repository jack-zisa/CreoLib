package com.github.creoii.creolib.mixin.entity;

import com.github.creoii.creolib.tag.CBlockTags;
import com.github.creoii.creolib.tag.CEntityTypeTags;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract BlockState getBlockStateAtPos();
    @Shadow public abstract EntityType<?> getType();
    @Shadow public abstract World getWorld();
    @Shadow protected abstract void checkBlockCollision();
    @Shadow public abstract void onLanding();
    @Shadow public abstract boolean isInLava();
    @Shadow public abstract boolean isTouchingWater();
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow public abstract Vec3d getVelocity();
    @Shadow public abstract void setVelocity(Vec3d velocity);
    @Shadow public abstract double getX();
    @Shadow public abstract double getZ();
    @Shadow public abstract boolean isSubmergedInWater();
    @Shadow public abstract boolean isDescending();
    @Shadow public abstract boolean isSpectator();
    @Shadow protected boolean onGround;
    @Shadow public boolean noClip;

    private static final ImmutableMap<DamageSource, TagKey<EntityType<?>>> DAMAGE_IMMUNITIES = new ImmutableMap.Builder<DamageSource, TagKey<EntityType<?>>>()
            .put(DamageSource.GENERIC, CEntityTypeTags.GENERIC_IMMUNE)
            .put(DamageSource.FALL, CEntityTypeTags.FALL_IMMUNE)
            .put(DamageSource.CACTUS, CEntityTypeTags.CACTUS_IMMUNE)
            .put(DamageSource.SWEET_BERRY_BUSH, CEntityTypeTags.SWEET_BERRY_BUSH_IMMUNE)
            .put(DamageSource.STALAGMITE, CEntityTypeTags.DRIPSTONE_IMMUNE)
            .put(DamageSource.LIGHTNING_BOLT, CEntityTypeTags.LIGHTNING_IMMUNE)
            .put(DamageSource.WITHER, CEntityTypeTags.WITHER_IMMUNE)
            .put(DamageSource.MAGIC, CEntityTypeTags.MAGIC_IMMUNE)
            .put(DamageSource.DROWN, CEntityTypeTags.DROWNING_IMMUNE)
            .put(DamageSource.IN_FIRE, CEntityTypeTags.FIRE_IMMUNE)
            .put(DamageSource.ON_FIRE, CEntityTypeTags.FIRE_IMMUNE)
            .put(DamageSource.IN_WALL, CEntityTypeTags.SUFFOCATION_IMMUNE)
            .put(DamageSource.FLY_INTO_WALL, CEntityTypeTags.FLY_INTO_WALL_IMMUNE)
            .put(DamageSource.STARVE, CEntityTypeTags.STARVATION_IMMUNE)
            .put(DamageSource.CRAMMING, CEntityTypeTags.CRAMMING_IMMUNE)
            .put(DamageSource.OUT_OF_WORLD, CEntityTypeTags.OUT_OF_WORLD_IMMUNE)
            .put(DamageSource.DRYOUT, CEntityTypeTags.DRYOUT_IMMUNE)
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

    @Inject(method = "tick", at = @At("TAIL"))
    private void creo_lib_walkOnFluids(CallbackInfo ci) {
        if (!isSpectator()) {
            if (getType().isIn(CEntityTypeTags.WALKS_ON_WATER) || getType().isIn(CEntityTypeTags.WALKS_ON_LAVA)) {
                ShapeContext shapeContext = ShapeContext.of((Entity) (Object) this);
                if ((isInLava() || (isTouchingWater() && !isSubmergedInWater())) && !isDescending()) {
                    if (!shapeContext.isAbove(FluidBlock.COLLISION_SHAPE, getBlockPos(), true) || getWorld().getFluidState(getBlockPos().up()).isIn(isTouchingWater() ? FluidTags.WATER : FluidTags.LAVA)) {
                        setVelocity(getVelocity().multiply(.5d).add(0d, .05d, 0d));
                    } else onGround = true;
                }
                checkBlockCollision();
            }
        }
    }

    @Inject(method = "fall", at = @At("TAIL"), cancellable = true)
    private void creo_lib_landOnFluids(CallbackInfo ci) {
        if (!isSpectator()) {
            if (getType().isIn(CEntityTypeTags.WALKS_ON_WATER) || getType().isIn(CEntityTypeTags.WALKS_ON_LAVA)) {
                checkBlockCollision();
                if ((isInLava() && getType().isIn(CEntityTypeTags.WALKS_ON_LAVA)) || ((isTouchingWater() && !isSubmergedInWater()) && getType().isIn(CEntityTypeTags.WALKS_ON_WATER))) {
                    onLanding();
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "canAvoidTraps", at = @At("HEAD"), cancellable = true)
    private void creo_lib_canAvoidTraps(CallbackInfoReturnable<Boolean> cir) {
        if (getType().isIn(CEntityTypeTags.AVOIDS_TRAPS)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isCollidable", at = @At("HEAD"), cancellable = true)
    private void creo_lib_isCollidable(CallbackInfoReturnable<Boolean> cir) {
        if (getType().isIn(CEntityTypeTags.COLLIDABLE)) {
            cir.setReturnValue(true);
        }
    }
}
