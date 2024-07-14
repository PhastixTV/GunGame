package dev.phastixtv.gungame.manager;

import dev.phastixtv.gungame.config.Config;
import dev.phastixtv.gungame.config.SpawnConfig;
import lombok.Getter;

@Getter
public class ConfigManager {

    private final Config config;
    private final SpawnConfig spawnConfig;

    public ConfigManager() {
        config = new Config();
        spawnConfig = new SpawnConfig();
    }

    public void loadAllConfigs() {
        config.loadConfig();
        spawnConfig.loadConfig();
    }

    public void saveAllConfigs() {
        config.save();
        spawnConfig.save();
    }
}
