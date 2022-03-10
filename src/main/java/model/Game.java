package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Runnable {

    private static final int TICK_RATE = 200;

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void run() {

        long sleepTime = 1000 / TICK_RATE;
        long taskTime = 0;

        while(true) {
            taskTime = System.currentTimeMillis();

            // Take input
            // Update positions, velocity, etc.

            taskTime = System.currentTimeMillis() - taskTime;

            if (sleepTime - taskTime > 0) {
                try {
                    Thread.sleep(sleepTime - taskTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Player> getPlayers() {
        synchronized (players) {
            return new ArrayList<>(players);
        }
    }
}
