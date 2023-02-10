package net.kigawa.hakoniwa.range;

import net.kigawa.hakoniwa.data.ConfigPlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Range {

    private Player p;
    private ConfigPlayerData config;
    private Bound bound;
    private Location rangePoint;
    private boolean isInBound = false;
    private boolean isHasRange;

    public Range(Player p) {
        this.p = p;
        config = new ConfigPlayerData(p);
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

    public boolean isInBound() {
        return isInBound;
    }

    public void setInBound(boolean inBound) {
        isInBound = inBound;
    }

    public boolean isHasRange() {
        return isHasRange;
    }

    public boolean loadData() {
        isHasRange = (boolean) config.getValue("hasRange");
        if (isHasRange) {
            rangePoint = (Location) config.getValue("location");
            bound = new Bound((Location) getConfig().getValue("location"));
            p.sendMessage("インスタンスを作成");
            return true;
        } else {
            p.sendMessage("インスタンスを作sssss成");
            return false;
        }
    }

    public void setBound() {
        if (bound != null) return;
        bound = new Bound((Location) getConfig().getValue("location"));
    }

}
