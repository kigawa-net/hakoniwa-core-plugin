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

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("このコマンドはプレーヤー専用です。");
            return false;
        }

        if (HakoniwaCore.getRange() == null) return false;

        Player p = (Player) commandSender;

        if (!p.getWorld().getName().equals(HakoniwaCore.getWorldName())) {
            p.sendMessage(Utils.message("§cこのコマンドは使用できません"));
        }

        if (!HakoniwaCore.playerRangeMap.isLoaded(p)) return true;

        if (strings.length == 0) {
            p.sendMessage(Utils.message("§cサブコマンドを入力してください"));
            return true;
        }

        if (strings[0].equals("info")) {

            if (HakoniwaCore.getRange().getRangePoint() == null) {
                p.sendMessage(Utils.message("§cまだ建築範囲が設定されていません"));
                return true;
            }

            p.sendMessage(infoLoc());
            Utils.goodSound(p);
        }

        if (strings[0].equals("create")) {

            Range range = HakoniwaCore.getRange();

            if (range.getRangePoint() == null) {

                range.getConfig().get().set("location", p.getLocation());
                range.getConfig().saveConfig();

                range.getConfig().get().set("hasRange", true);
                range.getConfig().saveConfig();

                range.getConfig().reloadConfig();
                range.loadData();

                p.sendMessage(Utils.message("§aここの地点に新たに建築範囲を設定しました"));

                Utils.goodSound(p);

                if (range.loadData()) {
                    p.sendMessage(Utils.message("§a建築可能範囲データをロードしました！！"));
                } else {
                    p.sendMessage(Utils.message("§c建築可能範囲データをロード中にエラーが発生しました"));
                }

            } else {
                p.sendMessage(Utils.message("§c既に建築できる範囲を指定しています"));
                Utils.errorSound(p);
            }

            Utils.goodSound(p);

        } else if (strings[0].equals("remove")) {
            Range range = HakoniwaCore.getRange();
            if (range.isHasRange()) {

                range.getConfig().get().set("location", "");
                range.getConfig().saveConfig();

                range.getConfig().get().set("hasRange", false);
                range.getConfig().saveConfig();

                range.getConfig().reloadConfig();

                range.resetBound();

                p.sendMessage(Utils.message("§a建築範囲を削除しました"));

                Utils.goodSound(p);
            } else {
                p.sendMessage(Utils.message("§cまだ建築可能範囲を所有していません"));
                Utils.errorSound(p);
            }
        } else {
            p.sendMessage(Utils.message("§c関係のないコマンドを入力しています"));
            Utils.errorSound(p);
        }

        return true;
    }

    public String infoLoc() {

        Location loc = HakoniwaCore.getRange().getRangePoint();

        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();

        String info = "§b建築可能範囲(中心座標)\n" +
                "§d---------------\n" +
                "§fX: " + x + "\n" +
                "§fY: " + y + "\n" +
                "§fZ: " + z + "\n" +
                "§d---------------";

        return info;
    }

}
