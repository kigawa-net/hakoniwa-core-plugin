package net.kigawa.hakoniwa.listener;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.Utils;
import net.kigawa.hakoniwa.range.Range;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        if (HakoniwaCore.playerDataMap.isPlayer(p)) return;

        Range range = new Range(p);
        HakoniwaCore.playerDataMap.putData(p, range);
        range.getConfig().createPlayerFile();
        p.setFlying(false);
        if (range.loadData()) {
            p.sendMessage(Utils.message("§a建築可能範囲データをロードしました！！"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        HakoniwaCore.playerDataMap.removePlayer(p);
        if (p.isFlying()) {
            p.setFlying(false);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        if (!HakoniwaCore.playerDataMap.isPlayer(e.getPlayer())) return;

        Player p = e.getPlayer();
        Range range = HakoniwaCore.playerDataMap.getData(p);

        if (p.getGameMode().equals(GameMode.CREATIVE)) return;

        if (!range.isHasRange()) return;

        if (range.getBound().isPlayerWithinBound(p.getLocation())) {
            // 範囲内に入ったときの初期処理
            if (!range.isInBound()) {
                range.setInBound(true);
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(Utils.message("§a建築可能範囲に入りました！！自由に建築をしましょう！！"));
                Utils.goodSound(p);
            }
            return;
        }

        if (range.isInBound()) {
            range.setInBound(false);
            p.setAllowFlight(false);
            p.setFlying(false);
            p.sendMessage(Utils.message("§c建築可能範囲外になりました！！ここからは建築ができません"));
            Utils.errorSound(p);
        }

    }

    @EventHandler
    public void onSetBlock(BlockPlaceEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        if(!HakoniwaCore.playerDataMap.isPlayer(e.getPlayer())) return;

        Player p = e.getPlayer();
        Range range = HakoniwaCore.playerDataMap.getData(p);

        if (p.getGameMode().equals(GameMode.CREATIVE)) return;

        if (!range.isInBound()) {
            e.setCancelled(true);
            p.sendMessage(Utils.message("ここで、ブロックを置く権限はありません"));
            Utils.errorSound(p);
        }

    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName())) return;
        if(!HakoniwaCore.playerDataMap.isPlayer(e.getPlayer())) return;

        Player p = e.getPlayer();
        Range range = HakoniwaCore.playerDataMap.getData(p);

        if (p.getGameMode().equals(GameMode.CREATIVE)) return;

        if (!range.isInBound()) {
            e.setCancelled(true);
            p.sendMessage(Utils.message("ブロックを壊す権限はありません"));
            Utils.errorSound(p);
        }

    }

}
