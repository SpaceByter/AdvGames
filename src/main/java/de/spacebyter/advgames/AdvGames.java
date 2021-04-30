package de.spacebyter.advgames;

import de.spacebyter.advgames.enums.GameStates;
import de.spacebyter.advgames.listener.PlayerCompassListener;
import de.spacebyter.advgames.listener.PlayerConnectionListener;
import de.spacebyter.advgames.listener.PlayerDeathListener;
import de.spacebyter.advgames.listener.PlayerLittleEvents;
import de.spacebyter.advgames.loot.LootManager;
import de.spacebyter.advgames.manager.GameManager;
import de.spacebyter.advgames.manager.Locations;
import de.spacebyter.advgames.runnable.GameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.UUID;


public class AdvGames extends JavaPlugin {

    private final String PREFIX = "§3§lAdvGames §8〣 ";

    private final LinkedList<UUID> UUIDList = new LinkedList<>();

    private GameManager gameManager;
    private GameStates gameStates;

    private Locations locations;
    private LootManager lootManager;

    @Override
    public void onEnable() {
        gameStates = GameStates.LOBBY;

        gameManager = new GameManager(this);
        locations = new Locations();
        lootManager = new LootManager(this);

        new GameRunnable(this);

        new PlayerCompassListener(this);
        new PlayerConnectionListener(this);
        new PlayerLittleEvents(this);
        new PlayerDeathListener(this);

        loadWorlds();

    }

    private void loadWorlds() {
        Bukkit.getServer().createWorld(new WorldCreator("lobby"));
    }

    @Override
    public void onDisable() {

    }

    public String getPREFIX() {
        return PREFIX;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameStates(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    public GameStates getGameStates() {
        return gameStates;
    }

    public LinkedList<UUID> getUUIDList() {
        return UUIDList;
    }

    public boolean isJoin() {
        return gameStates.isJoin();
    }

    public Locations getLocations() {
        return locations;
    }

    public LootManager getLootManager() {
        return lootManager;
    }
}
