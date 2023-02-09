package net.kigawa.hakoniwa.data;

import net.kigawa.hakoniwa.range.Range;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataMap {
    private final Map<Player, Range> data = new HashMap<>();

    public Range getData(Player p) {
        return data.get(p);
    }

    public void putData(Player p, Range range) {
        data.put(p, range);
    }

    public boolean isPlayer(Player p) {
        if (data.containsKey(p)) {
            return true;
        } else {
            return false;
        }
    }

    public void removePlayer(Player p) {
        if (isPlayer(p)) {
            data.remove(p);
        }
    }
}
