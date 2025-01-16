package net.braisgameplays.antidupe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiDupe extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new CloseInventoryOnBackpackDrop(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
