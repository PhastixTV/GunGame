package dev.phastixtv.gungame.item;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum DefaultKits {

    LEVEL1(
            null,
            null,
            null,
            null,
            new ItemStack(Material.WOODEN_AXE)
    ),
    LEVEL2(
            null,
            null,
            null,
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.WOODEN_AXE)
    ),
    LEVEL3(
            null,
            null,
            new ItemStack(Material.LEATHER_LEGGINGS),
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.WOODEN_AXE)
    ),
    ;

    private final ItemStack helmet;
    private final ItemStack chestPlate;
    private final ItemStack leggins;
    private final ItemStack boots;
    private final ItemStack weapon;

    DefaultKits(ItemStack helmet, ItemStack chestPlate, ItemStack leggins, ItemStack boots, ItemStack weapon) {
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggins = leggins;
        this.boots = boots;
        this.weapon = weapon;
    }
}
