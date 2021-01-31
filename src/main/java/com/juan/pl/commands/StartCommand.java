package com.juan.pl.commands;

import com.juan.pl.Main;
import com.juan.pl.TitleSender;
import com.juan.pl.TitleType;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class StartCommand implements CommandExecutor {

    private Main main;
    private int worldSize = 400;
    private float gameTimeInSeconds = 300;
    private float progress;
    private BossBar bossBar;
    private BukkitRunnable borderMover;
    private BukkitRunnable progressor;
    private BukkitRunnable countdown;
    private World world;
    private WorldBorder worldBorder;
    int countdownCount = 20;


    public StartCommand(Main main) {
        this.main = main;
        world = Bukkit.getWorld("world");
        worldBorder = world.getWorldBorder();
        bossBar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SEGMENTED_6);
        assignTasks();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        reset();
        try {
            gameTimeInSeconds = Float.parseFloat(args[0]);
        } catch (Exception ignored) {
        }

        world.setPVP(false);

        Random random = new Random();
        int x = random.nextInt(500) - 1000;
        int z = random.nextInt(500) - 1000;

        Location loc = world.getHighestBlockAt(x, z).getLocation();
        WorldBorder wb = world.getWorldBorder();
        wb.setCenter(loc);
        wb.setSize(worldSize);

        world.getPlayers().forEach(p -> {
            p.teleport(loc);
            bossBar.addPlayer(p);
        });

        TitleSender.sendAll(ChatColor.GREEN + "20 seconds remaining!", TitleType.ACTIONBAR);

        countdown.runTaskTimer(main, 0L, 20L);
        borderMover.runTaskTimer(main, 420L, (long) gameTimeInSeconds * 20 / 6);
        progressor.runTaskTimer(main, 400L, 20);

        return false;
    }
    private void reset(){
        System.out.println("resetting game logic");
        try {
            countdown.cancel();
            borderMover.cancel();
            progressor.cancel();
            progress = 0;
            countdownCount = 20;
            bossBar.removeAll();
            bossBar.setProgress(0);
            assignTasks();
        }
        catch (Exception ex){
        }
    }

    private void assignTasks(){
        borderMover =  new BukkitRunnable() {
            @Override
            public void run() {
                TitleSender.sendAll(ChatColor.YELLOW + "The borders are moving", TitleType.ACTIONBAR);
                worldBorder.setSize((int) (worldBorder.getSize() * 0.8), 20);

                if (progress > gameTimeInSeconds) cancel();
            }
        };
        progressor = new BukkitRunnable() {
            @Override
            public void run() {
                progress++;
                bossBar.setProgress(progress / gameTimeInSeconds);
                if (progress > gameTimeInSeconds) {
                    bossBar.removeAll();
                    cancel();
                }
            }
        };
        countdown = new BukkitRunnable() {

            @Override
            public void run() {
                if (countdownCount-- <= 0) {
                    world.setPVP(true);
                    TitleSender.sendAll(ChatColor.RED + "Fight!", TitleType.TITLE);
                    cancel();
                } else {
                    TitleSender.sendAll(ChatColor.GREEN + String.format("%s seconds remaining!", countdownCount), TitleType.ACTIONBAR);
                }
            }

        };

    }
}
