package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Runnable {

    private static final int TICK_RATE = 20;

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());

    private boolean isRunning = false;

    public synchronized void addPlayer(Player player) {
        synchronized (players) {
            players.add(player);
        }
    }

    public synchronized void removePlayer(Player player) {
        synchronized (players) {
            players.remove(player);
        }
    }

    @Override
    public void run() {
        isRunning = true;

        long sleepTime = 1000 / TICK_RATE;
        long taskTime = 0;

        while(isRunning) {
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

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * @return Returns a copy of the players and their state.
     */
    public List<Player> getPlayers() {
        synchronized (players) {
            return new ArrayList<>(players);
        }
    }
}
