package com.juan.pl;

import org.bukkit.Bukkit;

public class TitleSender {



    public static void sendAll(String message, TitleType type){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a " + type.getAction() + " {\"text\":\"" + message + "\"}" );
    }
    public static void sendPrivate(String name, String message, TitleType type){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title "+ name + " " + type.getAction() + " {\"text\":\"" + message + "\"}" );
    }
}
