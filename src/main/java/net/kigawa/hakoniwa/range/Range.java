package net.kigawa.hakoniwa.range;

import net.kigawa.hakoniwa.data.ConfigPlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Range {

    Player p;
    ConfigPlayerData config;
    Location rangePoint;
    final int POSSIBLE_RANGE = (int) getConfig().getValue("range");

    public Range(Player p) {
        this.p = p;
        config = new ConfigPlayerData(p);
    }

    public Player getP() {
        return p;
    }

    public ConfigPlayerData getConfig() {
        return config;
    }

    public Location getRangePoint() {
        return rangePoint;
    }

    public int getPOSSIBLE_RANGE() {
        return POSSIBLE_RANGE;
    }

    public boolean loadData() {
        rangePoint = (Location) config.getValue("location");
        if (rangePoint != null) {
            return true;
        } else {
            return false;
        }
    }

}
