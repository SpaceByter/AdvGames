package de.spacebyter.advgames.runnable;

import de.spacebyter.advgames.AdvGames;
import de.spacebyter.advgames.enums.GameStates;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

public class GameRunnable implements Runnable {

    private Integer[] countdownValues = {60, 30, 20, 10, 5, 4, 3, 2};

    private final AdvGames plugin;

    private BukkitTask task;
    private GameStates gameStates;
    private int valueTime = 60;

    public GameRunnable(AdvGames plugin) {
        this.plugin = plugin;
        this.task = plugin.getServer().getScheduler().runTaskTimer(plugin, this, 20, 20);
    }

    public void stop(){
        this.task.cancel();
    }

    @Override
    public void run() {
        gameStates = plugin.getGameStates();

        switch (gameStates) {
            case LOBBY:
            case PRELOBBY:
                if(this.plugin.getUUIDList().size() <= 1) {
                    this.gameStates = GameStates.LOBBY;
                    this.valueTime = 61;
                    break;
                }

                if(valueTime == 1) {
                    sendMessage("§7Die Runde beginnt in §beiner §7Sekunden");
                }

                if(Arrays.asList(countdownValues).contains(valueTime)) {
                    sendMessage("§7Die Runde beginnt in §b" + valueTime + " §7Sekunden");
                }

                if(valueTime == 0) {
                    plugin.setGameStates(GameStates.INGAME);
                    plugin.getGameManager().teleportToIngame();

                    valueTime = 600;
                    break;
                }

                valueTime--;
                break;
            case INGAME:
                if(plugin.getUUIDList().isEmpty() || valueTime == 0) {
                    valueTime = 15;
                    plugin.setGameStates(GameStates.RESTART);
                    break;
                }

                if(plugin.getUUIDList().size() == 1) {

                    Player target = plugin.getServer().getPlayer(plugin.getUUIDList().get(0));
                    if(target != null) {
                        //WIN
                    }
                }

                valueTime--;
                break;
            case RESTART:

                break;
        }
    }

    private void sendMessage(String message) {
        plugin.getServer().getOnlinePlayers().forEach(players -> {
            players.sendMessage(plugin.getPREFIX() + message);
        });
    }
}
