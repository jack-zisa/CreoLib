package com.github.creoii.creolib.api.entity.ai;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public class ShootProjectileGoal extends Goal {
    private final MobEntity mob;
    private int projectilesFired;
    private int projectileCooldown;
    private int targetNotVisibleTicks;
    private final EntityType<? extends ProjectileEntity> projectileType;
    private final int baseAttackCooldown;
    private final int maxProjectiles;

    public ShootProjectileGoal(MobEntity mob, EntityType<? extends ProjectileEntity> projectileType, int baseAttackCooldown, int maxProjectiles) {
        this.mob = mob;
        this.projectileType = projectileType;
        this.baseAttackCooldown = baseAttackCooldown;
        this.maxProjectiles = maxProjectiles;
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    public ShootProjectileGoal(MobEntity mob, EntityType<? extends ProjectileEntity> projectileType) {
        this(mob, projectileType, 100, 4);
    }

    public boolean canStart() {
        LivingEntity livingEntity = mob.getTarget();
        return livingEntity != null && livingEntity.isAlive() && mob.canTarget(livingEntity);
    }

    public void start() {
        projectilesFired = 0;
    }

    public void stop() {
        targetNotVisibleTicks = 0;
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        --projectileCooldown;
        LivingEntity livingEntity = mob.getTarget();
        if (livingEntity != null) {
            boolean targetVisible = mob.getVisibilityCache().canSee(livingEntity);
            if (targetVisible) {
                targetNotVisibleTicks = 0;
            } else {
                ++targetNotVisibleTicks;
            }

            double d = mob.squaredDistanceTo(livingEntity);
            if (d < 4d) {
                if (!targetVisible) {
                    return;
                }

                if (projectileCooldown <= 0) {
                    projectileCooldown = (int)(baseAttackCooldown * .2f);
                    mob.tryAttack(livingEntity);
                }

                mob.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
            } else if (d < getFollowRange() * getFollowRange() && targetVisible) {
                double e = livingEntity.getX() - mob.getX();
                double f = livingEntity.getBodyY(.5d) - mob.getBodyY(.5d);
                double g = livingEntity.getZ() - mob.getZ();
                if (projectileCooldown <= 0) {
                    ++projectilesFired;
                    if (projectilesFired == 1) {
                        projectileCooldown = (int)(baseAttackCooldown * .6f);
                    } else if (projectilesFired <= maxProjectiles) {
                        projectileCooldown = (int)(baseAttackCooldown * .06f);
                    } else {
                        projectileCooldown = baseAttackCooldown;
                        projectilesFired = 0;
                    }

                    if (projectilesFired > 1) {
                        double h = Math.sqrt(Math.sqrt(d)) * .5d;
                        if (!mob.isSilent()) {
                            mob.world.syncWorldEvent(null, 1018, mob.getBlockPos(), 0);
                        }

                        for (int i = 0; i < 1; ++i) {
                            ProjectileEntity projectileEntity = projectileType.create((ServerWorld) mob.world, null, null, new BlockPos(mob.getX(), mob.getBodyY(.5d) + .5d, mob.getZ()), SpawnReason.NATURAL, false, false);
                            if (projectileEntity != null) {
                                projectileEntity.setVelocity(mob.getRandom().nextTriangular(e, 2.297d * h), f, mob.getRandom().nextTriangular(g, 2.297d * h));
                                mob.world.spawnEntity(projectileEntity);
                            }
                        }
                    }
                }
                mob.getLookControl().lookAt(livingEntity, 10f, 10f);
            } else if (targetNotVisibleTicks < 5) {
                mob.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1d);
            }
            super.tick();
        }
    }

    private double getFollowRange() {
        return mob.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
    }
}