package net.kigawa.hakoniwa.range;

import org.bukkit.entity.Player;

public class PlayerRange {

    private Player p;

    private boolean isInBound = false;

    public PlayerRange(Player p) {
        this.p = p;
    }

    public boolean isInBound() {
        return isInBound;
    }

    public void setInBound(boolean inBound) {
        isInBound = inBound;
    }
}
