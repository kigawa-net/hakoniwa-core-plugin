package net.kigawa.hakoniwa.range;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.data.ConfigPlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Range {

    Player p;
    ConfigPlayerData config;
    Bound bound;
    Location rangePoint;

    public Range(Player p) {
        this.p = p;
        config = new ConfigPlayerData(p);
        if (loadData()) {
            bound = new Bound((Location) getConfig().getValue("location"));
        }
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

    public Bound getBound() {
        return bound;
    }

    public boolean loadData() {
        rangePoint = (Location) config.getValue("location");
        if (rangePoint != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setBound() {
        bound = new Bound((Location) getConfig().getValue("location"));
    }

}
