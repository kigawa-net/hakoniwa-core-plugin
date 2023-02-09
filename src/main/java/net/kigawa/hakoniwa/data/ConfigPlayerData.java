package net.kigawa.hakoniwa.data;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.Utils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class ConfigPlayerData {

    HakoniwaCore plugin = HakoniwaCore.getPlugin();
    Player p;
    File file;
    YamlConfiguration config;

    public ConfigPlayerData(Player p) {
        this.p = p;
    }

    public void createPlayerFile() {
        File file = new File(plugin.getDataFolder() + "/PlayerData/", p.getUniqueId() + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            p.sendMessage(Utils.message("プレイヤーデータを作成しました"));
        }

        config = YamlConfiguration.loadConfiguration(file);
        this.file = file;
    }

    public void setValue(String str, Object value) {
        if (config == null) return;
        config.set(str, value);
    }

    public Object getValue(String value) {
        if (config == null) return null;
        return config.get(value);
    }

    public void saveConfig() {
        if (config == null) return;
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadConfig() {
        if (config == null);
        try {
            config.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }


}

/**
 *
 * yml構成
 *
 * location: loc
 *
 */