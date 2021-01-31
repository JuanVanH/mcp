package com.juan.pl.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class ConCommand implements CommandExecutor {

    private final Configuration config;
    public ConCommand(Configuration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (command.getName().equals("con")) {
            player.sendMessage(ChatColor.YELLOW + config.getString("word"));
            player.setHealth(20);
        }
        return false;
    }
}
