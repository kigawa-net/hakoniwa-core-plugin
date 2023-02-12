package net.kigawa.hakoniwa.listener;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.Utils;
import net.kigawa.hakoniwa.range.PlayerRange;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (HakoniwaCore.playerRangeMap.isLoaded(p)) return;

        PlayerRange playerRange = new PlayerRange(p);
        HakoniwaCore.playerRangeMap.putData(p, playerRange);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        HakoniwaCore.playerRangeMap.removePlayer(p);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        if (!HakoniwaCore.playerRangeMap.isLoaded(e.getPlayer())) return;

        Player p = e.getPlayer();
        PlayerRange playerRange = HakoniwaCore.playerRangeMap.getData(p);

        if (p.getGameMode().equals(GameMode.CREATIVE)) return;

        if (!HakoniwaCore.getRange().isHasRange()) return;

        if (HakoniwaCore.getRange().getBound().isPlayerWithinBound(p.getLocation())) {
            // 範囲内に入ったときの初期処理
            if (!playerRange.isInBound()) {
                playerRange.setInBound(true);
                p.sendMessage(Utils.message("§a建築可能範囲に入りました！！自由に建築をしましょう！！"));
                Utils.goodSound(p);
            }
            return;
        }

        if (playerRange.isInBound()) {
            playerRange.setInBound(false);
            p.sendMessage(Utils.message("§c建築可能範囲外になりました！！ここからは建築ができません"));
            Utils.errorSound(p);
        }

    }

    @EventHandler
    public void onSetBlock(BlockPlaceEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        if(!HakoniwaCore.playerRangeMap.isLoaded(e.getPlayer())) return;

        Player p = e.getPlayer();

        if (p.getGameMode().equals(GameMode.CREATIVE)) return;

        Location block = e.getBlock().getLocation();

        if (!HakoniwaCore.getRange().getBound().isPlayerWithinBound(block)) {
            e.setCancelled(true);
            p.sendMessage(Utils.message("§cここにブロックを置くことは出来ません"));
        }

    }

}
