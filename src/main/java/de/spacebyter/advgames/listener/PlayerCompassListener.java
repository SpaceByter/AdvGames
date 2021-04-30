package de.spacebyter.advgames.listener;

import de.spacebyter.advgames.AdvGames;
import de.spacebyter.advgames.object.CustomHeads;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class PlayerCompassListener implements Listener {

    private final AdvGames plugin;

    public PlayerCompassListener(AdvGames plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {
        if(event.getItem() == null) return;
        if(event.getItem().getItemMeta() == null) return;

        if(event.getItem().getType() == Material.COMPASS) {
            event.setCancelled(true);
            Inventory inventory = Bukkit.createInventory(null, 27, "§cKompass");

            plugin.getUUIDList().forEach(uuids -> {
                Player target = plugin.getServer().getPlayer(uuids);
                if(target == null) return;

                inventory.addItem(new CustomHeads(getSkinValue(target) ,"§6[" + target.getName() + "]").createCustomSkull());
            });

            event.getPlayer().openInventory(inventory);
        }
    }

    @EventHandler
    public void handleInventoryInteract(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;

        if(event.getInventory().getName() != "§cKompass") return;

        String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("[", "").replace("]", "");
        Player target = plugin.getServer().getPlayer(name);

        event.getWhoClicked().closeInventory();
        event.getWhoClicked().teleport(target.getLocation());
        if(!((Player)event.getWhoClicked()).getAllowFlight()) ((Player)event.getWhoClicked()).setAllowFlight(true);
        if(!((Player)event.getWhoClicked()).isFlying()) ((Player)event.getWhoClicked()).setFlying(true);
    }

    //<editor-fold defaultstate="collapsed" desc="getSkinValue">
    private String getSkinValue(Player player) {
        return ((CraftPlayer)player).getHandle().getProfile().getProperties().get("textures").iterator().next().getValue();
    }
    //</editor-fold>

}
