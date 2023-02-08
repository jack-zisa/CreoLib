package com.github.creoii.creolib.api.entity.ai;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class SummonEntityGoal<T extends MobEntity> extends CastSpellGoal {
    private final EntityType<T> summonType;
    private final int spawnCount;
    private final int spellTicks;
    private final int startTimeDelay;
    private final TargetPredicate closeEntityPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(16d).ignoreVisibility().ignoreDistanceScalingFactor();

    public SummonEntityGoal(MobEntity mob, SoundEvent castSpellSound, EntityType<T> summonType, int spawnCount, int spellTicks, int startTimeDelay) {
        super(mob, castSpellSound);
        this.summonType = summonType;
        this.spawnCount = spawnCount;
        this.spellTicks = spellTicks;
        this.startTimeDelay = startTimeDelay;
    }

    public SummonEntityGoal(MobEntity mob, SoundEvent castSpellSound, EntityType<T> summonType, int spawnCount) {
        this(mob, castSpellSound, summonType, spawnCount, 100, 340);
    }

    public boolean canStart() {
        if (!super.canStart()) {
            return false;
        } else {
            int i = getMob().world.getTargets(VexEntity.class, closeEntityPredicate, getMob(), getMob().getBoundingBox().expand(16d)).size();
            return getMob().getRandom().nextInt(8) + 1 > i;
        }
    }

    protected int getSpellTicks() {
        return spellTicks;
    }

    protected int startTimeDelay() {
        return startTimeDelay;
    }

    protected void castSpell() {
        ServerWorld serverWorld = (ServerWorld)getMob().world;
        for (int i = 0; i < spawnCount; ++i) {
            BlockPos blockPos = getMob().getBlockPos().add(-2 + getMob().getRandom().nextInt(5), 1, -2 + getMob().getRandom().nextInt(5));
            T livingEntity = summonType.create(getMob().world);
            if (livingEntity != null) {
                livingEntity.refreshPositionAndAngles(blockPos, 0f, 0f);
                livingEntity.initialize(serverWorld, getMob().world.getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                serverWorld.spawnEntityAndPassengers(livingEntity);
            }
        }
    }
}