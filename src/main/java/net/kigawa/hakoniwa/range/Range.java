package net.kigawa.hakoniwa.range;

import net.kigawa.hakoniwa.data.ConfigRangeData;
import org.bukkit.Location;

public class Range {

    private ConfigRangeData config;
    private Bound bound;
    private Location rangePoint;
    private boolean isHasRange;

    public Range() {
        config = new ConfigRangeData();
    }

    public ConfigRangeData getConfig() {
        return config;
    }

    public Location getRangePoint() {
        return rangePoint;
    }

    public Bound getBound() {
        return bound;
    }

    public boolean isHasRange() {
        return isHasRange;
    }

    public boolean loadData() {
        isHasRange = (boolean) config.get().getBoolean("hasRange");

        if (isHasRange) {

            rangePoint = (Location) config.get().get("location");
            bound = new Bound(rangePoint);

            return true;
        }
        return false;
    }

    public void resetBound() {
        bound = null;
        rangePoint = null;
        loadData();
    }

}
