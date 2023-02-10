package net.kigawa.hakoniwa.range;

import net.kigawa.hakoniwa.HakoniwaCore;
import org.bukkit.Location;

public class Bound {

    private Location point;
    private Location minLoc;
    private Location maxLoc;
    private final int POSSIBLE_RANGE = HakoniwaCore.getPlugin().getConfig().getInt("range");

    public Bound(Location point) {
        this.point = point;
        setMinMaxLoc();
    }

    public Location getPoint() {
        return point;
    }

    public int getPOSSIBLE_RANGE() {
        return POSSIBLE_RANGE;
    }

    public Location getMinLoc() {
        return minLoc;
    }

    public void setMinLoc(Location minLoc) {
        this.minLoc = minLoc;
    }

    public Location getMaxLoc() {
        return maxLoc;
    }

    public void setMaxLoc(Location maxLoc) {
        this.maxLoc = maxLoc;
    }

    public void setMinMaxLoc() {
        Location locMinClone = point, locMaxClone = point;

        locMinClone.setX(point.getX() - POSSIBLE_RANGE);
        locMinClone.setZ(point.getZ() - POSSIBLE_RANGE);

        locMaxClone.setX(point.getX() + POSSIBLE_RANGE);
        locMaxClone.setZ(point.getZ() + POSSIBLE_RANGE);

        setMinLoc(locMinClone);
        setMaxLoc(locMaxClone);
    }

    public boolean isPlayerWithinBound(Location loc) {
        int minX = (int) getMinLoc().getX();
        int minZ = (int) getMinLoc().getZ();

        int maxX = (int) getMaxLoc().getX();
        int maxZ = (int) getMaxLoc().getZ();

        int x = (int) loc.getX();
        int z = (int) loc.getZ();

        return x >= minX && x <= maxX && z >= minZ && z <= maxZ;
    }

}
