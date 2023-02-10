package com.github.creoii.creolib.api.util.registry;

import com.github.creoii.creolib.api.util.CFoodComponent;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;

public class CItemSettings extends FabricItemSettings {
    private ItemRegistryHelper.ItemGroupSettings[] itemGroups;
    private float compostingChance;
    private int fuelPower;
    private boolean villagerCollectable;
    private boolean farmerCompostable;
    private int villagerFoodValue;
    private int pickupDelay;

    public CItemSettings compostingChance(float chance) {
        compostingChance = chance;
        return this;
    }

    public CItemSettings fuelPower(int power) {
        fuelPower = power;
        return this;
    }

    public CItemSettings food(CFoodComponent foodComponent) {
        super.food(foodComponent);
        return this;
    }

    public CItemSettings itemGroups(ItemRegistryHelper.ItemGroupSettings[] itemGroups) {
        this.itemGroups = itemGroups;
        return this;
    }

    public CItemSettings villagerCollectable() {
        villagerCollectable = true;
        return this;
    }

    public CItemSettings farmerCompostable() {
        farmerCompostable = true;
        return this;
    }

    public CItemSettings villagerFood(int value) {
        villagerFoodValue = value;
        return this;
    }

    public CItemSettings pickupDelay(int delay) {
        pickupDelay = delay;
        return this;
    }

    public float getCompostingChance() {
        return compostingChance;
    }

    public int getFuelPower() {
        return fuelPower;
    }

    public FoodComponent getFoodComponent() {
        return foodComponent;
    }

    public ItemRegistryHelper.ItemGroupSettings[] getItemGroups() {
        return itemGroups;
    }

    public int getVillagerFoodValue() {
        return villagerFoodValue;
    }

    public boolean isVillagerCollectable() {
        return villagerCollectable;
    }

    public boolean isFarmerCompostable() {
        return farmerCompostable;
    }

    public int getPickupDelay() {
        return pickupDelay;
    }
}
