package de.spacebyter.advgames.listener;

import de.spacebyter.advgames.AdvGames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class PlayerInteractListener implements Listener {

    private final AdvGames plugin;


    public PlayerInteractListener(AdvGames plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock().getType() == Material.DIAMOND_BLOCK) {
            Inventory inventory = Bukkit.createInventory(null, 27);

            plugin.getLootManager().getLoot().forEach(loots -> {
                if(plugin.getLootManager().randomChance(loots.getChance())) {
                    inventory.addItem(loots.getItemStack());
                }
            });
            return;
        }
    }

}
