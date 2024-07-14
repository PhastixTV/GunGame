package dev.phastixtv.gungame.manager;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.gameplayer.GamePlayer;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GamePlayerManager {

    private final GunGame plugin;

    @Getter
    private final Map<Player, GamePlayer> gamePlayerMap;

    public GamePlayerManager() {
        this.plugin = GunGame.INSTANCE;

        gamePlayerMap = new HashMap<>();
    }

    public GamePlayer getGamePlayer(Player player) {
        return this.gamePlayerMap.get(player);
    }

    public void createGamePlayer(Player player) {
        if (player == null) {
            GunGame.INSTANCE.getLogger().info("The Player is NULL");
            return;
        }

        GamePlayer gamePlayer = GamePlayer.create(player);
        plugin.getLogger().info(this.getClass().getName() + ": Is Creating a GamePlaer!");
        gamePlayerMap.put(player, gamePlayer);
    }

}
