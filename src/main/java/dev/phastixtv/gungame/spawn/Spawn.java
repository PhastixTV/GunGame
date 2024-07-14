package dev.phastixtv.gungame.spawn;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.config.SpawnConfig;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Spawn {

    private final SpawnConfig config;

    public Spawn() {
        this.config = GunGame.INSTANCE.getSpawnConfig();
    }

    public void setSpawn(Player player) {
        config.setLocation(player);
    }

    public void removeSpawn() {
        config.removeLocation();
    }

    public void teleportPlayer(Player player) {
        if (config.loadLocation() == null) player.sendMessage("§cThere is no spawn that you can be teleported");
        player.teleport(config.loadLocation());
        if (player.getLastDeathLocation() == null)
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1L, 1L);
    }

    public Location getSpawn() {
        return config.loadLocation();
    }
}
