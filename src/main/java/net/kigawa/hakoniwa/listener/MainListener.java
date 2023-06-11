package net.kigawa.hakoniwa.listener;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.Utils;
import net.kigawa.hakoniwa.range.PlayerRange;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        if (HakoniwaCore.getRange().getBound().isInBound(p.getLocation())) {
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
    public void onPlaceBlock(BlockPlaceEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName()) || !HakoniwaCore.playerRangeMap.isLoaded(e.getPlayer())) return;

        Player p = e.getPlayer();

        blockEvent(e, p);

    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {

        if (!e.getPlayer().getWorld().getName().equals(HakoniwaCore.getWorldName()) || !HakoniwaCore.playerRangeMap.isLoaded(e.getPlayer())) return;

        Player p = e.getPlayer();

        blockEvent(e, p);

    }

    private void blockEvent(@NotNull BlockEvent blockEvent, @Nullable Player player) {
        final var block = blockEvent.getBlock();
        if (!(blockEvent instanceof Cancellable cancellable)) return;

        if (!block.getWorld().getName().equals(HakoniwaCore.getWorldName())) return;

        if (player != null && player.getGameMode().equals(GameMode.CREATIVE)) return;

        Location blockLocation = block.getLocation();

        if (!HakoniwaCore.getRange().getBound().isInBound(blockLocation)) {
            cancellable.setCancelled(true);
            if (player != null) player.sendMessage(Utils.message("§cここにブロックを置くことは出来ません"));
        }
    }

}
