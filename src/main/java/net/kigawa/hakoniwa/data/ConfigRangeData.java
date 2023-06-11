package net.kigawa.hakoniwa.data;

import net.kigawa.hakoniwa.HakoniwaCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigRangeData {

    File file;
    YamlConfiguration config;

    public void createFile() {
        File file = new File(HakoniwaCore.getPlugin().getDataFolder(), "data.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(file);
            defaultConfig.set("hasRange", false);

            try {
                defaultConfig.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        this.file = file;
    }

    public YamlConfiguration get() {
        return config;
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