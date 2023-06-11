package net.kigawa.hakoniwa;

import net.kigawa.hakoniwa.data.PlayerRangeMap;
import net.kigawa.hakoniwa.listener.MainListener;
import net.kigawa.hakoniwa.range.Range;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.dependency.*;
import org.bukkit.plugin.java.annotation.plugin.*;
import org.bukkit.plugin.java.annotation.plugin.author.*;

import java.io.File;

@Plugin(name = "HakoniwaCore", version = "1.0.0")
@Author("kigawa.net")
@Dependency("CommandAPI")
@Dependency("WorldGuard")
@Command(name = "mrn", desc = "/mrn <subcommand>")

public class HakoniwaCore extends JavaPlugin {

    public static PlayerRangeMap playerRangeMap = new PlayerRangeMap();

    private static Range range = new Range();

    private static HakoniwaCore plugin;

    private static String world = "world";


    @Override
    public void onEnable() {

        plugin = this;

        world = getConfig().getString("world");

        setup();

        range.getConfig().createFile();

        saveDefaultConfig();

        if (range.loadData()) {
            getLogger().info("建築範囲をロードしました");
        } else {
            getLogger().info("建築範囲が設定されていないためロードできませんでした");
        }

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

    public static String getWorldName() {
        return world;
    }

    public static Range getRange() {
        return range;
    }

    public void setup() {
        File file = plugin.getDataFolder();
        if (!file.exists()) {
            if (file.mkdir()) {
                plugin.getLogger().info("プレイヤーデータフォルダを作成しました");
            } else {
                plugin.getLogger().info("エラーが発生し、プレイヤーデータフォルダを作成することが出来ませんでした");
            }
        }
    }

}

