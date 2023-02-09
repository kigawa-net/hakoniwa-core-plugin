package net.kigawa.hakoniwa.listener;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.Utils;
import net.kigawa.hakoniwa.range.Range;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (HakoniwaCore.playerDataMap.isPlayer(p)) return;

        Range range = new Range(p);
        HakoniwaCore.playerDataMap.putData(p, range);
        range.getConfig().createPlayerFile();
        if (range.loadData()) {
            p.sendMessage(Utils.message("§a建築可能範囲データをロードしました！！"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        HakoniwaCore.playerDataMap.removePlayer(p);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (!HakoniwaCore.playerDataMap.isPlayer(e.getPlayer())) return;

        Player p = e.getPlayer();
        Range range = HakoniwaCore.playerDataMap.getData(p);

        if (range.getBound().isPlayerWithinBound(p.getLocation())) {
            p.sendMessage("範囲内！！");
        }

    }

}
