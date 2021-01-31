package com.juan.pl.consumables;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IConsumable {
    String getName();
    void consume(Player p);

    static ItemStack getItemStack(int amount) {
        return null;
    }
}
