package com.github.creoii.creolib.mixin.client.render;

import com.github.creoii.creolib.core.registry.AttributeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final MinecraftClient client;

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 6d))
    private double creo_lib_targetedReachDistance(double constant) {
        if (client.player != null) {
            return client.player.getAttributeValue(AttributeRegistry.PLAYER_REACH_DISTANCE);
        } return constant;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 3d))
    private double creo_lib_targetedAttackRange(double constant) {
        if (client.player != null) {
            return client.player.getAttributeValue(AttributeRegistry.GENERIC_ATTACK_RANGE);
        } return constant;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 9d))
    private double creo_lib_squaredAttackRange(double constant) {
        if (client.player != null) {
            return MathHelper.square(client.player.getAttributeValue(AttributeRegistry.GENERIC_ATTACK_RANGE));
        } return constant;
    }
}
