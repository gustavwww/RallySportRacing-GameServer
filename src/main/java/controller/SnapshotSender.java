package controller;

import model.Game;
import model.GameListener;
import model.Player;
import services.protocol.ServerProtocol;

public class SnapshotSender implements Runnable, GameListener {

    private static final int PACKET_RATE = 60;

    private final Game game;
    private final ServerProtocol protocol;

    public SnapshotSender(Game game) {
        this.game = game;
        protocol = ServerProtocol.getInstance();
    }

    @Override
    public void run() {
        new Thread(game).start();

        long sleepTime = 1000 / PACKET_RATE;
        long taskTime = 0;

        while(game.isRunning()) {
            taskTime = System.currentTimeMillis();

            // Send Game details to each player in the game.
            sendGameData();

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

    private void sendGameData() {
        for (Player p : game.getPlayers()) {
            // TODO: REPLACE TCP WITH UDP!
            p.getClient().sendTCP(protocol.parseGame(game));
        }
    }


}
