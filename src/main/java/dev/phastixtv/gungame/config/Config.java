package dev.phastixtv.gungame.config;

import dev.phastixtv.gungame.GunGame;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class Config {

    private File file;
    private static YamlConfiguration config;

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

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

        host = config.getString("mysql.host");
        port = config.getInt("mysql.port");
        database = config.getString("mysql.database");
        username = config.getString("mysql.username");
        password = config.getString("mysql.password");
    }

    public void save() {
        try {
            config.save(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
