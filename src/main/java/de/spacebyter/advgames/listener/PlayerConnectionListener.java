package de.spacebyter.advgames.listener;

import de.spacebyter.advgames.AdvGames;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private final AdvGames plugin;

    public PlayerConnectionListener(AdvGames plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        if(plugin.isJoin()) {
            plugin.getGameManager().connectPlayer(event.getPlayer());
            event.setJoinMessage(this.plugin.getPREFIX() + "§b" + event.getPlayer().getName() + " §7ist beigetreten.");
            return;
        }
        plugin.getGameManager().setPlayerToGhost(event.getPlayer());
        event.setJoinMessage(null);
    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
