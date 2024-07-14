package dev.phastixtv.gungame.command;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.gameplayer.GamePlayer;
import dev.phastixtv.gungame.spawn.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GunGameCommand implements CommandExecutor {

    private final Spawn spawn;
    private final GunGame plugin;

    public GunGameCommand() {
        this.plugin = GunGame.INSTANCE;
        this.spawn = plugin.getSpawn();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(player);
            if (player == null) return false;
            if (args.length == 1) {

                switch (args[0].toLowerCase()) {

                    case "help":
                        helpMessage(player);
                        break;

                }
            } else if (args.length <= 4 ) {

                if (!player.hasPermission("gungame.cmd.set")) {
                    player.sendMessage(plugin.getNoPerm());
                    return false;
                }

                if (args[0].equalsIgnoreCase("set")) {
                    switch (args[1]) {
                        case "spawn":
                            spawn.setSpawn(player);
                            player.sendMessage(plugin.getPrefix() + " " + "§aDer Spawnpunkt wurde gesetzt");
                            break;

                        case "spawnarea":
                            switch (args[2]) {
                                case "1":
                                    //logic
                                    break;

                                    case "2":
                                        //logic
                                        break;
                            }
                            break;

                        case "level":
                            if (args.length == 4) {
                                Player target = Bukkit.getPlayer(args[2]);
                                GamePlayer gameTarget = plugin.getGamePlayerManager().getGamePlayer(target);
                                if (gameTarget != null) {
                                    gameTarget.setLevel(Integer.parseInt(args[3]));
                                    player.sendMessage(plugin.getPrefix() + " " + "§aDas Level von §6" + target.getName() + "§a liegt bei §6" + gameTarget.getLevel());
                                    return true;
                                }
                            }
                            gamePlayer.setLevel(Integer.parseInt(args[2]));
                            player.sendMessage(plugin.getPrefix() + " " + "§aDein Level liegt nun bei §6" + gamePlayer.getLevel());
                            break;
                    }
                }
            }
        }
        return true;
    }

    private void helpMessage(Player player) {
        player.sendMessage("--------------" + GunGame.INSTANCE.getPrefix() + "--------------");
        player.sendMessage("§6/gungame help §7Shows you the available Commands for you.");
        player.sendMessage("§6/stats §7Siehe deine Stats.");
        player.sendMessage("§6/stats <Player> §7Siehe die Stats eines Spielers");

        if (player.hasPermission("gungame.cmd.help")) {
            player.sendMessage("");
            player.sendMessage("§6/gungame set spawnarea1 §7Set the spawnarea point 1.");
            player.sendMessage("§6/gungame set spawnarea2 §7Set the spawnarea point 2, to complete the area.");
            player.sendMessage("§6/gungame set spawn §7Set where the players should spawn.");
            player.sendMessage("§6/gungame set level <Player> <level> §7Set the level of a player.");
            player.sendMessage("§6/gungame set level <level> §7Setzte das Level für dich.");
        }

        player.sendMessage("--------------" + GunGame.INSTANCE.getPrefix() + "--------------");

    }
}