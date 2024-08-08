package dev.phastixtv.gungame;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class GunGame extends JavaPlugin {

    public static GunGame INSTANCE;

    private final String prefix;
    private final String noPerm;
    private final String nullGamePlayer;

    public GunGame() {
        INSTANCE = this;

        prefix = "§f§l[§r§6GunGame§f§l]§r";
        noPerm = prefix + " " + "§cDafür hast du keine Rechte!";
        nullGamePlayer = "GamePlayer ist Null";
    }


    @Override
    public void onEnable() {
        startMessage();

        registerCommands();
        registerListener(Bukkit.getPluginManager());
        setGameRules();

    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {

    }

    private void registerListener(PluginManager pluginManager) {

    }

    private void startMessage() {

    }

    private void setGameRules() {
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(false);
            world.setThundering(false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setClearWeatherDuration(1000000);
        }
    }
}
