package de.spacebyter.advgames.manager;

import de.spacebyter.advgames.AdvGames;
import de.spacebyter.advgames.object.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {

    private final AdvGames plugin;

    public GameManager(AdvGames plugin) {
        this.plugin = plugin;
    }

    public void connectPlayer(Player target) {
        target.getInventory().clear();
        target.setGameMode(GameMode.ADVENTURE);
        plugin.getUUIDList().add(target.getUniqueId());

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            target.teleport(this.plugin.getLocations().getLocation("LOBBY"));
        }, 1);
    }

    public void teleportToIngame() {
        for(int i = 0; i != this.plugin.getUUIDList().size(); i++ ) {
            Player target = plugin.getServer().getPlayer(plugin.getUUIDList().get(i));

            target.teleport(plugin.getLocations().getLocation("ARENA." + i));
        }
    }

    public void setPlayerToGhost(Player target) {
        plugin.getServer().getOnlinePlayers().forEach(players -> {
            players.hidePlayer(target);
            spawnBlocks(target.getLocation());
        });

        target.spigot().setCollidesWithEntities(false);
        target.getInventory().clear();
        target.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setDisplayName("§8§l〣 §c§lKompass").getItemStack());
    }

    public void spawnBlocks(Location location) {
        for(int i = 0; i < 15; i++) {
            int radius = 50;

            double currentX = location.getX();
            double currentZ = location.getZ();

            SplittableRandom random = new SplittableRandom();

            int randomX = random.nextInt(-radius, radius);
            int randomZ = random.nextInt(-radius, radius);

            currentX = currentX + randomX;
            currentZ = currentZ + randomZ;

            double y = location.getWorld().getHighestBlockYAt((int)currentX, (int)currentZ);
            y = y + 1D;


            location.getWorld().getBlockAt((int)currentX, (int)y, (int)currentZ).setType(Material.DIAMOND_BLOCK);
        }

    }

}
