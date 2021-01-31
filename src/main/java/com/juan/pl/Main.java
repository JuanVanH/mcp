package com.juan.pl;

import com.juan.pl.commands.StartCommand;
import com.juan.pl.consumables.ConsumableListener;
import com.juan.pl.consumables.JumpGold;
import com.juan.pl.consumables.SprintMint;
import com.juan.pl.consumables.StealthDiamond;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener {

    private World world = Bukkit.getWorld("world");
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new ConsumableListener(this), this);

        Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand(this));
        Objects.requireNonNull(Bukkit.getWorld("world")).setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
//        world.setAutoSave(false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.sendMessage("Welcome");
        p.getInventory().clear();
        p.getInventory().addItem(JumpGold.getItemStack(10));
        p.getInventory().addItem(SprintMint.getItemStack(10));
        p.getInventory().addItem(StealthDiamond.getItemStack(10));
    }
}
