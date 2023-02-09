package net.kigawa.hakoniwa.commands;

import net.kigawa.hakoniwa.HakoniwaCore;
import net.kigawa.hakoniwa.Utils;
import net.kigawa.hakoniwa.range.Range;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player) commandSender;

        if (!HakoniwaCore.playerDataMap.isPlayer(p)) return true;

        if (strings.length == 0) {
            p.sendMessage("§cサブコマンドを入力してください");
            return true;
        } else {
            if (strings[0].equals("create")) {
                Range range = HakoniwaCore.playerDataMap.getData(p);
                if (range.getRangePoint() == null) {
                    range.getConfig().setValue("location", p.getLocation());
                    range.getConfig().saveConfig();
                    range.getConfig().reloadConfig();
                    p.sendMessage(Utils.message("§aここの地点に新たに建築範囲を設定しました"));
                    if (range.loadData()) {
                        p.sendMessage(Utils.message("§a建築可能範囲データをロードしました！！"));
                    } else {
                        p.sendMessage(Utils.message("§c建築可能範囲データをロード中にエラーが発生しました。エラーコード #1000"));
                    }
                } else {
                    p.sendMessage(Utils.message("§c既に建築できる範囲を指定しています"));
                }
                return true;
            }
        }

        return true;
    }
}
