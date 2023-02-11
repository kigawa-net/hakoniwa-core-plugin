package net.kigawa.hakoniwa;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Utils {

    public static String message(String str) {
        return "§aHakoniwa-Core: §f" + str;
    }

    public static void goodSound(Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
    }

    public static void errorSound(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
    }

}
