package model;

import services.protocol.ServerProtocol;

import java.util.*;

public class Game implements Runnable {

    private static final int TICK_RATE = 20;

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());

    private final List<PlayerTime> times = Collections.synchronizedList(new ArrayList<>());

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

    public synchronized void setPlayerTime(Player player, float time) {
        synchronized (times) {
            boolean found = false;
            for (PlayerTime pt : times) {
                if (pt.getPlayer().getId() == player.getId() && time < pt.getTime()) {
                    pt.setTime(time);
                    found = true;
                    sendTimes();
                }
            }
            if (!found) {
                times.add(new PlayerTime(player, time));
                sendTimes();
            }
        }
    }

    private synchronized void sendTimes() {
        synchronized (players) {
            for (Player p : players) {
                p.getClient().sendTCP(ServerProtocol.getInstance().parseTimes(this));
            }
        }
    }

    @Override
    public void run() {
        isRunning = true;

        long sleepTime = 1000 / TICK_RATE;
        long taskTime;

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

    public List<PlayerTime> getTimes() {
        synchronized (times) {
         return new ArrayList<>(times);
        }
    }
}
