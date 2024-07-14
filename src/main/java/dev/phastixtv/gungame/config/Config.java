package dev.phastixtv.gungame.config;

import dev.phastixtv.gungame.GunGame;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private static YamlConfiguration config;

    public void loadConfig() {
        file = new File(GunGame.INSTANCE.getDataFolder(), "config.yml");

        if (!file.exists())
            GunGame.INSTANCE.saveResource("config.yml", false);

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
