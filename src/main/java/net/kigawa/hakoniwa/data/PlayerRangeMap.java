package net.kigawa.hakoniwa.data;

import net.kigawa.hakoniwa.range.PlayerRange;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerRangeMap {
    private final Map<Player, PlayerRange> data = new HashMap<>();

    public PlayerRange getData(Player p) {
        return data.get(p);
    }

    public void putData(Player p, PlayerRange range) {
        data.put(p, range);
    }

    public boolean isLoaded(Player p) {
        if (data.containsKey(p)) {
            return true;
        } else {
            return false;
        }
    }

    public void removePlayer(Player p) {
        if (isLoaded(p)) {
            data.remove(p);
        }
    }

    public void clearMap() {
        data.clear();
    }

}
