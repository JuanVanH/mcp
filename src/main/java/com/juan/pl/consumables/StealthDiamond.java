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

public class StealthDiamond implements IConsumable {
    @Override
    public String getName() {
        return "Diamond of stealth";
    }

    @Override
    public void consume(Player p) {
        ItemStack is = Objects.requireNonNull(p.getItemInHand());
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,1000,5,true,true,true));
        TitleSender.sendPrivate(p.getDisplayName(), ChatColor.LIGHT_PURPLE + "One with the scene", TitleType.ACTIONBAR);
        p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,1,2);
    }

    public static ItemStack getItemStack(int amount) {
        ItemStack jumpGold = new ItemStack(Material.DIAMOND,amount);
        ItemMeta meta = jumpGold.getItemMeta();
        meta.setDisplayName("Diamond of stealth");
        meta.setLore(Arrays.asList("Makes you invisible if you believe in it"));
        jumpGold.setItemMeta(meta);
        return jumpGold;
    }
}
