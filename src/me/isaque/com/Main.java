package me.isaque.com;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("&6Plugin ativado com sucesso!");

    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("&6Plugin desativado com sucesso!");
    }
}
