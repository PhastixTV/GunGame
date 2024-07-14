package dev.phastixtv.gungame.listener.player;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.spawn.Spawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        Spawn spawn = GunGame.INSTANCE.getSpawn();
        Location respawnLoc = spawn.getSpawn();

        if (respawnLoc != null) {
            event.setRespawnLocation(respawnLoc);
        }
    }
}
