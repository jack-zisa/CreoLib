package com.github.creoii.creolib.mixin.client.screen;

import com.github.creoii.creolib.api.tag.CEnchantmentTags;
import com.github.creoii.creolib.api.util.TagUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.stream.Collectors;

@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneScreenHandlerMixin extends ScreenHandler {
    @Shadow @Final private ScreenHandlerContext context;
    @Shadow @Final private Inventory result;
    private ItemStack item;

    protected GrindstoneScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Redirect(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/GrindstoneScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 2))
    private Slot creo_lib_fixResultSlot(GrindstoneScreenHandler instance, Slot slot) {
        return addSlot(new Slot(result, 2, 129, 34){

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                context.run((world, pos) -> {
                    if (world instanceof ServerWorld) {
                        ExperienceOrbEntity.spawn((ServerWorld)world, Vec3d.ofCenter(pos), getExperience(world));
                    }
                    world.syncWorldEvent(WorldEvents.GRINDSTONE_USED, pos, 0);
                });
                ((GrindstoneScreenHandler) (Object) (GrindstoneScreenHandlerMixin.this)).input.setStack(0, ItemStack.EMPTY);
                ((GrindstoneScreenHandler) (Object) (GrindstoneScreenHandlerMixin.this)).input.setStack(1, ItemStack.EMPTY);
            }

            private int getExperience(World world) {
                int i = 0;
                i += getExperience(((GrindstoneScreenHandler) (Object) (GrindstoneScreenHandlerMixin.this)).input.getStack(0));
                if ((i += getExperience(((GrindstoneScreenHandler) (Object) (GrindstoneScreenHandlerMixin.this)).input.getStack(1))) > 0) {
                    int j = (int)Math.ceil((double)i / 2.0);
                    return j + world.random.nextInt(j);
                }
                return 0;
            }

            private int getExperience(ItemStack stack) {
                int i = 0;
                Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);
                for (Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                    Enchantment enchantment = entry.getKey();
                    Integer integer = entry.getValue();
                    if (enchantment.isCursed() || TagUtil.isEnchantmentIn(enchantment, CEnchantmentTags.GRINDSTONE_IGNORES)) continue;
                    i += enchantment.getMinPower(integer);
                }
                return i;
            }
        });
    }

    @Inject(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;"))
    private void creo_lib_ignoredEnchantmentsGrind(ItemStack item, int damage, int amount, CallbackInfoReturnable<ItemStack> cir) {
        this.item = item;
    }

    @Redirect(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;set(Ljava/util/Map;Lnet/minecraft/item/ItemStack;)V"))
    private void creo_lib_ignoredEnchantmentsSet(Map<Enchantment, Integer> enchantments, ItemStack stack) {
        Map<Enchantment, Integer> map = EnchantmentHelper.get(item).entrySet().stream().filter(entry -> {
            Enchantment enchantment = entry.getKey();
            return enchantment.isCursed() || TagUtil.isEnchantmentIn(enchantment, CEnchantmentTags.GRINDSTONE_IGNORES);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        EnchantmentHelper.set(map, stack);
    }
}
