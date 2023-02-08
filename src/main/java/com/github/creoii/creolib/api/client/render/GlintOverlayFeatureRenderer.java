package com.github.creoii.creolib.api.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

@Environment(value= EnvType.CLIENT)
public class GlintOverlayFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final EntityModel<T> model;

    public GlintOverlayFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
        model = new EntityModel<>() {
            @Override
            public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
                context.getModel().setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            }

            @Override
            public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
                context.getModel().render(matrices, vertices, light, overlay, red, green, blue, alpha);
            }
        };
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumer, int light, T entity, float limbAngle, float limbDistance, float age, float headYaw, float headPitch, float tickDelta) {
        if (entity.getType() == EntityType.PIG) {
            matrices.push();
            matrices.scale(1.01f, 1.01f, 1.01f);
            getContextModel().copyStateTo(model);
            model.animateModel(entity, limbAngle, limbDistance, tickDelta);
            model.setAngles(entity, limbAngle, limbDistance, age, headYaw, headPitch);
            model.render(matrices, vertexConsumer.getBuffer(RenderLayer.getDirectGlint()), light, LivingEntityRenderer.getOverlay(entity, 0f), 1f, 1f, 1f, 1f);
            matrices.pop();
        }
    }
}