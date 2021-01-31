package com.juan.pl;

import com.juan.pl.commands.ConCommand;
import com.juan.pl.commands.HealingCommand;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main_Demo extends JavaPlugin implements Listener {

    static boolean walking = true;
    private World world = Bukkit.getWorld("world");

    @Override
    public void onEnable() {
        System.out.println("The plugin has been loaded.");
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("Hello")).setExecutor(new HealingCommand());
        Objects.requireNonNull(getCommand("Con")).setExecutor(new ConCommand(this.getConfig().getDefaults()));

        Entity e = world.spawnEntity(new Location(world,0,0,0), EntityType.POLAR_BEAR);
        e.setInvulnerable(true);
        e.setFireTicks(20 * 5); //5s
        PolarBear polarBear = (PolarBear)e;
        polarBear.setAgeLock(true);
    }

    @Override
    public void onDisable() {
        System.out.println("The plugin has been disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(!walking) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Movement not allowed.");
        }
    }
    @EventHandler
    public void onMove(PlayerEggThrowEvent e){
        e.getPlayer().sendMessage(ChatColor.BLUE + "Wow");
        e.getEgg().playEffect(EntityEffect.LOVE_HEARTS);
    }
}
