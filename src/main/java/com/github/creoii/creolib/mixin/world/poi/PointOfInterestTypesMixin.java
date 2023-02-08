package com.github.creoii.creolib.mixin.world.poi;

import com.github.creoii.creolib.api.tag.CBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PointOfInterestTypes.class)
public class PointOfInterestTypesMixin {
    @Inject(method = "getTypeForState", at = @At("RETURN"), cancellable = true)
    private static void creo_lib_attractsLightningBlocks(BlockState state, CallbackInfoReturnable<Optional<RegistryEntry<PointOfInterestType>>> cir) {
        if (state.isIn(CBlockTags.ATTRACTS_LIGHTNING)) {
            cir.setReturnValue(Optional.ofNullable(Registries.POINT_OF_INTEREST_TYPE.entryOf(PointOfInterestTypes.LIGHTNING_ROD)));
        }
    }
}
