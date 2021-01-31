package com.juan.pl;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Dictionary;
import java.util.Hashtable;

public class Main_old extends JavaPlugin implements Listener {
    private final Dictionary<Player,Scoreboard> scoreBoards = new Hashtable<>();;
    private World world;

    @Override
    public void onEnable() {
        System.out.println("Plugin loaded");
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
        world = Bukkit.createWorld(new WorldCreator("world").type(WorldType.FLAT));
        assert world != null;
        world.getBlockAt(0,0,0).setType(Material.GOLD_BLOCK);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (scoreBoards.get(p) != null){
            p.setScoreboard(scoreBoards.get(p));
            p.sendMessage("Scoreboard loaded.");
        }
        else {
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("Stats", "Stats");
            obj.setDisplayName("Money");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score s = obj.getScore("Money");
            s.setScore(0);
            scoreBoards.put(p, board);
            p.setScoreboard(board);
        }
    }

    @EventHandler
    public void onBlockDig(BlockBreakEvent e){
        Player p = e.getPlayer();
        p.sendMessage("Block harvested: " + e.getBlock().getType().toString());
        if(e.getBlock().getType() == Material.DIRT){
            Scoreboard b = scoreBoards.get(p);
            Objective obj = b.getObjective("Stats");
            Score s = obj.getScore("Money");
            s.setScore(s.getScore() + 1);
            p.setScoreboard(b);
            p.sendMessage("New scoreboard.");
        }
    }

    private void renderMapFlat(){
        assert world != null;
        world.setBiome(0,0,0, Biome.PLAINS);

    }
}
