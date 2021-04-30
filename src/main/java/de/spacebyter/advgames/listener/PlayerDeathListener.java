package de.spacebyter.advgames.listener;

import de.spacebyter.advgames.AdvGames;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final AdvGames plugin;

    public PlayerDeathListener(AdvGames plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if(player == null) return;

        if(killer != null) {
            sendMessage("§7Der Spieler §b" + player.getName() + " §7wurde von §b" + killer.getName() + " §7getötet.");
            respawnPlayer(player);
            return;
        }
        sendMessage("§7Der Spieler §b" + player.getName() + " §7ist gestorben.");
        respawnPlayer(player);
    }

    private void sendMessage(String message) {
        plugin.getServer().getOnlinePlayers().forEach(players -> {
            players.sendMessage(plugin.getPREFIX() + message);
        });
    }

    private void respawnPlayer(final Player player) {
        if(this.plugin.getUUIDList().contains(player.getUniqueId())) {
            this.plugin.getServer().getScheduler().runTaskLater(this.plugin, () -> {
                player.spigot().respawn();
                player.teleport(this.plugin.getLocations().getLocation("LOBBY"));
                this.plugin.getGameManager().setPlayerToGhost(player);
            }, 2);
        }
    }

}
