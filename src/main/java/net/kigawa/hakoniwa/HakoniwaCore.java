package net.kigawa.hakoniwa;

import net.kigawa.hakoniwa.commands.Commands;
import net.kigawa.hakoniwa.data.PlayerDataMap;
import net.kigawa.hakoniwa.listener.MainListener;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.java.annotation.dependency.*;
import org.bukkit.plugin.java.annotation.plugin.*;
import org.bukkit.plugin.java.annotation.plugin.author.*;

@Plugin(name = "HakoniwaCore", version = "1.0.0")
@Author("kigawa.net")
@Dependency("CommandAPI")
@Dependency("WorldGuard")

public class HakoniwaCore extends JavaPlugin {

    public static PlayerDataMap playerDataMap = new PlayerDataMap();

    private static HakoniwaCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        getCommand("mrn").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new MainListener(), this);

        getLogger().info("HakoniwaCoreが起動しました");
    }

    @Override
    public void onDisable() {
    }

    public static HakoniwaCore getPlugin() {
        return plugin;
    }
}
