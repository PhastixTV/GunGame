package dev.phastixtv.gungame.command;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.gameplayer.GamePlayer;
import dev.phastixtv.gungame.manager.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatsCommand implements CommandExecutor {

    private final GunGame plugin;
    private final StatsManager statsManager;

    public StatsCommand() {
        this.plugin = GunGame.INSTANCE;
        this.statsManager = plugin.getStatsManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                statsMessage(player);
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    statsMessage(target);
                }
            } else
                player.sendMessage(plugin.getPrefix() + " §cUsage: /stats <player>");
        }
        return true;
    }

    private void statsMessage(Player player) {
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(player);
        player.sendMessage("--------------" + gamePlayer.getPlayer().getName() + "--------------");
        player.sendMessage("Level Rekord: " + gamePlayer.getLevelRecord());
        player.sendMessage("Killstreak Rekord: " + gamePlayer.getKillStreakRecord());
        player.sendMessage("K/D" + )
        player.sendMessage("--------------" + gamePlayer.getPlayer().getName() + "--------------");
    }
}
