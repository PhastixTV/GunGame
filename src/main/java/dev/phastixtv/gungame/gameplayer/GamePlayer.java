package dev.phastixtv.gungame.gameplayer;

import dev.phastixtv.gungame.GunGame;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.swing.plaf.SplitPaneUI;

@Getter
@Setter
public class GamePlayer {

    private final GunGame plugin;

    private final Player player;
    private int level;
    private int levelRecord;
    private int killStreak;
    private int killStreakRecord;

    public GamePlayer(final Player player, int level) {
        this.player = player;
        this.plugin = GunGame.INSTANCE;
        this.level = level;
    }

    public static GamePlayer create(final Player player) {
        return new GamePlayer(player, 1);
    }

    public void resetLevel() {
        this.level = 1;
    }

    public void resetLevelRecord() {
        this.levelRecord =1;
    }

    public void resetKillstreak() {
        this.killStreak = 0;
    }

    public void resetKillStreakLevel() {
        this.killStreakRecord = 0;
    }

    public void playerDiedStats() {
        //This method is setting the stats after a player died.
        if (this.level > this.levelRecord) {
            this.levelRecord = this.level;
        }
        if (this.killStreak > this.killStreakRecord) {
            this.killStreakRecord = this.killStreak;
        }

        if (this.level == 1) {
            return;
        }

        if (this.level >= 60) {
            this.level -= 9;
        } else if (this.level >= 50 ) {
            this.level -= 7;
        } else if (this.level >= 35) {
            this.level -= 4;
        } else if (this.level >= 22 ) {
            this.level -= 2;
        } else
            this.level -= 1;

        plugin.getItemManager().giveItems(player);
    }

    public void killedPlayer() {
        this.level += 1;
        this.killStreak += 1;
        plugin.getItemManager().giveItems(player);
        player.sendMessage(level + "Level");
        player.sendMessage(killStreak + "Kills");
    }
}
