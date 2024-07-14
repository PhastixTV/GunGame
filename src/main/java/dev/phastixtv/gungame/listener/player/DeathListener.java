package dev.phastixtv.gungame.listener.player;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.gameplayer.GamePlayer;
import dev.phastixtv.gungame.spawn.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final GunGame plugin;

    public DeathListener() {
        this.plugin = GunGame.INSTANCE;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        GamePlayer killer = plugin.getGamePlayerManager().getGamePlayer(event.getPlayer().getKiller());
        GamePlayer target = plugin.getGamePlayerManager().getGamePlayer(event.getPlayer());

        if (target != null) {
            target.playerDiedStats();
        }

        respawn(event.getPlayer());
    }

    private void respawn(Player player) {
        Bukkit.getServer().getScheduler().runTaskLater(GunGame.getPlugin(GunGame.class), new Runnable() {
            @Override
            public void run() {
                player.spigot().respawn();
            }
        }, 1);
    }

    @EventHandler
    public void onDeathEntity(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(player);

        if (gamePlayer == null) {
            plugin.getLogger().info(plugin.getNullGamePlayer());
            return;
        }

        gamePlayer.killedPlayer();

    }

}
