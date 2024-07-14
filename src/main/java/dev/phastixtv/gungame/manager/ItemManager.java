package dev.phastixtv.gungame.manager;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.gameplayer.GamePlayer;
import dev.phastixtv.gungame.item.DefaultKits;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private final GunGame plugin;
    private final Map<Integer, DefaultKits> levelKits;


    public ItemManager() {
        this.plugin = GunGame.INSTANCE;
        this.levelKits = new HashMap<>();
        initializeLevelKits();
    }

    private void initializeLevelKits() {
        for (DefaultKits kit : DefaultKits.values()) {
            String name = kit.name();
            if (name.startsWith("LEVEL")) {
                int level = Integer.parseInt(name.substring(5)); // Extrahiert die Zahl nach "LEVEL"
                levelKits.put(level, kit);
            }
        }
    }

    public void giveItems(Player player) {
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(player);
        DefaultKits kit = levelKits.get(gamePlayer.getLevel());

        if (kit != null) {
            removeItems(player);
            player.getInventory().setItem(0, kit.getWeapon());

            if (kit.getBoots() != null) {
                player.getInventory().setBoots(kit.getBoots());
            }
            if (kit.getLeggins() != null) {
                player.getInventory().setLeggings(kit.getLeggins());
            }
            if (kit.getChestPlate() != null) {
                player.getInventory().setChestplate(kit.getChestPlate());
            }
            if (kit.getHelmet() != null) {
                player.getInventory().setHelmet(kit.getHelmet());
            }
        }
    }

    public void removeItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }
}
