package net.kigawa.hakoniwa;

import net.kigawa.hakoniwa.range.Range;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player) commandSender;

        if (!HakoniwaCore.playerDataMap.isPlayer(p)) return true;

        if (strings.length == 0) {
            p.sendMessage(Utils.message("§cサブコマンドを入力してください"));
            return true;
        } else {
            if (strings[0].equals("create")) {
                Range range = HakoniwaCore.playerDataMap.getData(p);
                if (range.getRangePoint() == null) {
                    range.getConfig().setValue("location", p.getLocation());
                    range.getConfig().saveConfig();
                    range.getConfig().setValue("hasRange", true);
                    range.getConfig().saveConfig();
                    range.getConfig().reloadConfig();
                    range.setBound();
                    p.sendMessage(Utils.message("§aここの地点に新たに建築範囲を設定しました"));
                    if (range.loadData()) {
                        p.sendMessage(Utils.message("§a建築可能範囲データをロードしました！！"));
                    } else {
                        p.sendMessage(Utils.message("§c建築可能範囲データをロード中にエラーが発生しました。エラーコード #1000"));
                    }
                } else {
                    p.sendMessage(Utils.message("§c既に建築できる範囲を指定しています"));
                }
            } else if (strings[0].equals("info")) {
                Range range = HakoniwaCore.playerDataMap.getData(p);
                if (range.getRangePoint() == null) return true;
                p.sendMessage(infoLoc(p, range.getRangePoint()));
            } else {
                p.sendMessage(Utils.message("§c関係のないコマンドを入力しています"));
            }
        }
        return true;
    }

    public String infoLoc(Player p, Location loc) {

        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();

        String info = "§f§l" + p.getName() + "§bさんの建築可能範囲(中心座標)\n" +
                "§d---------------\n" +
                "§fX: " + x + "\n" +
                "§fY: " + y + "\n" +
                "§fZ: " + z + "\n" +
                "§d---------------";

        return info;
    }

}
