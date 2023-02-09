package net.kigawa.hakoniwa;

import net.kigawa.hakoniwa.data.PlayerDataMap;
import net.kigawa.hakoniwa.listener.MainListener;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.java.annotation.dependency.*;
import org.bukkit.plugin.java.annotation.plugin.*;
import org.bukkit.plugin.java.annotation.plugin.author.*;

import java.io.File;

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
        setup();

        getServer().getPluginManager().registerEvents(new MainListener(), this);
        getCommand("mrn").setExecutor(new RegionCommands());

        getLogger().info("HakoniwaCoreが起動しました");
    }

    @Override
    public void onDisable() {
    }

    public static HakoniwaCore getPlugin() {
        return plugin;
    }

    public void setup() {
        File file = new File(plugin.getDataFolder() + "/PlayerData/", "");
        if (!file.exists()) {
            if (file.mkdir()) {
                plugin.getLogger().info("プレイヤーデータフォルダを作成しました");
            } else {
                plugin.getLogger().info("エラーが発生し、プレイヤーデータフォルダを作成することが出来ませんでした");
            }
        }
    }

}
