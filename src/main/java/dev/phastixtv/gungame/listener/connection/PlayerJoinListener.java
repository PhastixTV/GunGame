package dev.phastixtv.gungame.listener.connection;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.gameplayer.GamePlayer;
import dev.phastixtv.gungame.spawn.Spawn;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Spawn spawn;
    private final GunGame plugin;

    public PlayerJoinListener() {
        this.spawn = GunGame.INSTANCE.getSpawn();
        this.plugin = GunGame.INSTANCE;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (spawn.getSpawn() != null)
            player.teleport(spawn.getSpawn());
        if (plugin.getGamePlayerManager().getGamePlayer(player) == null) {
            addGamePlayer(player);
            plugin.getLogger().info(this.getClass().getName() + ": Ein Neuer Gameplayer wurde erstellt");
        }
        plugin.getItemManager().giveItems(player);
    }

    private void addGamePlayer(Player player) {
        plugin.getGamePlayerManager().createGamePlayer(player);
        //MSQL
    }

}
