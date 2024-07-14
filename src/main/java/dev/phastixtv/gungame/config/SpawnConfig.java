package dev.phastixtv.gungame.config;

import dev.phastixtv.gungame.GunGame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SpawnConfig {

    private File file;
    private static YamlConfiguration config;
    private static String root;

    public void loadConfig() {
        root = "Spawn";
        file = new File(GunGame.INSTANCE.getDataFolder(), "spawn.yml");

        if (!file.exists())
            GunGame.INSTANCE.saveResource("spawn.yml", false);

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


    public void setLocation(Player player) {
        Location location = player.getLocation();
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getX());
        config.set(root + ".Y", location.getY());
        config.set(root + ".Z", location.getZ());
        config.set(root + ".Yaw", location.getYaw());
        config.set(root + ".Pitch", location.getPitch());
        save();
    }

    public void removeLocation() {
        config.set(root + ".World", null);
        config.set(root + ".X", null);
        config.set(root + ".Y", null);
        config.set(root + ".Z", null);
        config.set(root + ".Yaw", null);
        config.set(root + ".Pitch", null);
        save();
    }

    public Location loadLocation() {
        if (config.contains(root)) {
            World world = Bukkit.getWorld(Objects.requireNonNull(config.getString(root + ".World")));
            double x = config.getDouble(root + ".X"),
                    y = config.getDouble(root + ".Y"),
                    z = config.getDouble(root + ".Z");
            float yaw = (float)config.getDouble(root + ".Yaw"),
                    pitch = (float)config.getDouble(root + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        }else
            return null;
    }
}
