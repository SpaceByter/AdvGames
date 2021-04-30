package de.spacebyter.advgames.loot;

import org.bukkit.inventory.ItemStack;

public abstract class Loot {

    private final ItemStack itemStack;
    private final int chance;

    public Loot(ItemStack itemStack, int chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getChance() {
        return chance;
    }
}
