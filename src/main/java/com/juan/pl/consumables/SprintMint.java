package com.juan.pl.consumables;

import com.juan.pl.TitleSender;
import com.juan.pl.TitleType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Objects;

public class SprintMint implements IConsumable {
    @Override
    public String getName() {
        return "Sprint Mint";
    }

    @Override
    public void consume(Player p) {
        ItemStack is = Objects.requireNonNull(p.getItemInHand());
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1000,5,true,true,true));
        TitleSender.sendPrivate(p.getDisplayName(), ChatColor.BLUE + "Fresh as the wind", TitleType.ACTIONBAR);
        p.playSound(p.getLocation(), Sound.ENTITY_PANDA_EAT,1,2);
    }

    public static ItemStack getItemStack(int amount) {
        ItemStack jumpGold = new ItemStack(Material.BLUE_ORCHID,amount);
        ItemMeta meta = jumpGold.getItemMeta();
        meta.setDisplayName("Sprint Mint");
        meta.setLore(Arrays.asList("Smells like the wind"));
        jumpGold.setItemMeta(meta);
        return jumpGold;
    }
}
