package com.github.creoii.creolib.impl.mixin.enchantment;

import com.github.creoii.creolib.api.tag.CEnchantmentTags;
import com.github.creoii.creolib.api.enchantment.AllowEnchantments;
import com.github.creoii.creolib.api.util.TagUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void creo_lib_acceptableEnchantments(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof AllowEnchantments allowEnchantments) {
            cir.setReturnValue(allowEnchantments.getAllowedEnchantments().test((Enchantment) (Object) this));
        }
    }

    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    private void creo_lib_offeredByLibrarians(CallbackInfoReturnable<Boolean> cir) {
        if (TagUtil.isEnchantmentIn((Enchantment) (Object) this, CEnchantmentTags.NOT_OFFERED_BY_LIBRARIANS)) cir.setReturnValue(false);
    }

    @Inject(method = "isAvailableForRandomSelection", at = @At("HEAD"), cancellable = true)
    private void creo_lib_notRandomlySelectable(CallbackInfoReturnable<Boolean> cir) {
        if (TagUtil.isEnchantmentIn((Enchantment) (Object) this, CEnchantmentTags.NOT_RANDOMLY_SELECTABLE)) cir.setReturnValue(false);
    }

    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void creo_lib_curses(CallbackInfoReturnable<Boolean> cir) {
        if (TagUtil.isEnchantmentIn((Enchantment) (Object) this, CEnchantmentTags.CURSED)) cir.setReturnValue(true);
    }

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void creo_lib_treasureEnchantments(CallbackInfoReturnable<Boolean> cir) {
        if (TagUtil.isEnchantmentIn((Enchantment) (Object) this, CEnchantmentTags.TREASURE)) cir.setReturnValue(true);
    }
}
