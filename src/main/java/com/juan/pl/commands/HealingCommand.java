package com.juan.pl.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player)sender;
        if (command.getName().equals("hello")) {
            player.sendMessage(ChatColor.GREEN + "Hi there, " + player.getName());
            player.setHealth(20);
        }
        return false;
    }
}
