package model;

import services.protocol.ServerProtocol;

import java.util.*;

public class Game implements Runnable {

    private static final int TICK_RATE = 20;

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());
    private final List<PlayerTime> times = Collections.synchronizedList(new ArrayList<>());

    private boolean isRunning = false;

    private final ServerProtocol protocol;

    public Game() {
        protocol = ServerProtocol.getInstance();
    }

    public synchronized void addPlayer(Player player) {
        synchronized (players) {
            player.getClient().sendTCP(protocol.parsePlayersState(this));
            players.add(player);
            sendToAllPlayersExcept("joined:" + protocol.parsePlayerState(player), player);
        }
    }

    public synchronized void removePlayer(Player player) {
        synchronized (players) {
            players.remove(player);
            sendToAllPlayersExcept("left:" + protocol.parsePlayerState(player), player);
        }
    }

    public synchronized void sendPlayerUpdate(Player player) {
        synchronized (players) {
            if (players.contains(player)) {
                sendToAllPlayersExcept("update:" + protocol.parsePlayerState(player), player);
            }
        }
    }

    public synchronized void setPlayerTime(Player player, float time) {
        synchronized (times) {
            boolean found = false;
            for (PlayerTime pt : times) {
                if (pt.getPlayer().getId() == player.getId() && time < pt.getTime()) {
                    pt.setTime(time);
                    found = true;
                    sendToAllPlayers(protocol.parseTimes(this));
                }
            }
            if (!found) {
                times.add(new PlayerTime(player, time));
                sendToAllPlayers(protocol.parseTimes(this));
            }
        }
    }

    private synchronized void sendToAllPlayersExcept(String msg, Player player) {
        synchronized (players) {
            for (Player p : players) {
                if (p.getId() != player.getId()) {
                    p.getClient().sendTCP(msg);
                }
            }
        }
    }

    private synchronized void sendToAllPlayers(String msg) {
        synchronized (players) {
            for (Player p : players) {
                p.getClient().sendTCP(msg);
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
