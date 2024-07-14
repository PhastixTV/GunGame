package dev.phastixtv.gungame;

import dev.phastixtv.gungame.command.GunGameCommand;
import dev.phastixtv.gungame.command.StatsCommand;
import dev.phastixtv.gungame.manager.ConfigManager;
import dev.phastixtv.gungame.manager.GamePlayerManager;
import dev.phastixtv.gungame.item.ItemBuilder;
import dev.phastixtv.gungame.manager.ItemManager;
import dev.phastixtv.gungame.listener.player.DeathListener;
import dev.phastixtv.gungame.listener.connection.PlayerJoinListener;
import dev.phastixtv.gungame.listener.player.RespawnListener;
import dev.phastixtv.gungame.spawn.Spawn;
import dev.phastixtv.gungame.config.SpawnAreaConfig;
import dev.phastixtv.gungame.config.SpawnConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class GunGame extends JavaPlugin {

    public static GunGame INSTANCE;

    private final GamePlayerManager gamePlayerManager;
    private final ItemManager itemManager;
    private final ConfigManager configManager;

    private final Spawn spawn;
    private final ItemBuilder itemBuilder;

    private final String prefix;
    private final String noPerm;
    private final String nullGamePlayer;

    public GunGame() {
        INSTANCE = this;

        gamePlayerManager = new GamePlayerManager();
        itemManager = new ItemManager();
        configManager = new ConfigManager();

        spawn = new Spawn();
        itemBuilder = new ItemBuilder();

        prefix = "§f§l[§r§6GunGame§f§l]§r";
        noPerm = prefix + " " + "§cDafür hast du keine Rechte!";
        nullGamePlayer = "GamePlayer is Null";
    }


    @Override
    public void onEnable() {
        startMessage();

        registerCommands();
        registerListener(Bukkit.getPluginManager());
        setGameRules();

        configManager.loadAllConfigs();
    }

    @Override
    public void onDisable() {
        configManager.saveAllConfigs();
    }

    private void registerCommands() {
        var command = this.getCommand("gungame");
        if (command != null) {
            command.setExecutor(new GunGameCommand());
        }
        command = this.getCommand("stats");
        if (command != null) {
            command.setExecutor(new StatsCommand());
        }
    }

    private void registerListener(PluginManager pluginManager) {
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new RespawnListener(), this);
    }

    private void startMessage() {
        this.getLogger().info("  _________ __                 __  .__                              ________               ________                       \n");
        this.getLogger().info(" /   _____//  |______ ________/  |_|__| ____    ____               /  _____/ __ __  ____  /  _____/_____    _____   ____  \n");
        this.getLogger().info(" \\_____  \\\\   __\\__  \\\\_  __ \\   __\\  |/    \\  / ___\\             /   \\  ___|  |  \\/    \\/   \\  ___\\__  \\  /     \\_/ __ \\ \n");
        this.getLogger().info(" /        \\|  |  / __ \\|  | \\/|  | |  |   |  \\/ /_/  >            \\    \\_\\  \\  |  /   |  \\    \\_\\  \\/ __ \\|  Y Y  \\  ___/ \n");
        this.getLogger().info("/_______  /|__| (____  /__|   |__| |__|___|  /\\___  / /\\  /\\  /\\   \\______  /____/|___|  /\\______  (____  /__|_|  /\\___  >\n");
        this.getLogger().info("        \\/           \\/                    \\//_____/  \\/  \\/  \\/          \\/           \\/        \\/     \\/      \\/     \\/ ");
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
