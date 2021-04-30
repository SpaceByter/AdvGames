/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.spacebyter.advgames.listener;

import de.spacebyter.advgames.AdvGames;
import de.spacebyter.advgames.enums.GameStates;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 *
 * @author Finn
 */
public class PlayerLittleEvents implements Listener {

    private final AdvGames plugin;

    public PlayerLittleEvents(AdvGames plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleEntityDamage(EntityDamageEvent event) {
        if (this.plugin.getGameStates() != GameStates.INGAME) {
            event.setCancelled(true);
        }

        if (!(event.getEntity() instanceof Player)) return;

        if (!this.plugin.getUUIDList().contains(event.getEntity().getUniqueId()))
            event.setCancelled(true);
    }

    @EventHandler
    public void handleEntityDamagebyEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        if (this.plugin.getGameStates() != GameStates.INGAME) {
            event.setCancelled(true);
        }

        if (!(this.plugin.getUUIDList().contains(event.getDamager().getUniqueId()))) {
            event.setCancelled(true);
        }

        if (!this.plugin.getUUIDList().contains(event.getEntity().getUniqueId()))
            event.setCancelled(true);
    }

    @EventHandler
    public void handleFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleCreatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleItemDrop(PlayerDropItemEvent event) {
        if(plugin.getGameStates() == GameStates.LOBBY || plugin.getGameStates() == GameStates.PRELOBBY || plugin.getGameStates() == GameStates.RESTART) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event){
        if(plugin.getGameStates() == GameStates.LOBBY || plugin.getGameStates() == GameStates.PRELOBBY || plugin.getGameStates() == GameStates.RESTART) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }
    
    @EventHandler
    public void handlePlayerPickItemUp(PlayerPickupItemEvent event){
        if(!(plugin.getUUIDList().contains(event.getPlayer().getUniqueId())))
        {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void handleArmorstand(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked() instanceof ArmorStand) {
            event.setCancelled(true);
        }
    }
    
}
