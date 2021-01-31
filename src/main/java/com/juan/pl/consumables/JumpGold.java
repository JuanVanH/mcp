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

public class JumpGold implements IConsumable {
    private static String name = "Jump Gold";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void consume(Player p) {
        ItemStack is = Objects.requireNonNull(p.getItemInHand());
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,1000,5,true,true,true));
        TitleSender.sendPrivate(p.getDisplayName(), ChatColor.YELLOW + "Gold is running through your veins", TitleType.ACTIONBAR);
        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE,1,2);
    }

    public static ItemStack getItemStack(int amount) {
        ItemStack jumpGold = new ItemStack(Material.GOLD_NUGGET,amount);
        ItemMeta meta = jumpGold.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList("This gold feels particularly bouncy"));
        jumpGold.setItemMeta(meta);
        return jumpGold;
    }
}
