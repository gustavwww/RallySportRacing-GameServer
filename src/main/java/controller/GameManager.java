package controller;

import model.Game;

public class GameManager {

    public static Game hub = null;

    public static synchronized Game getHubGame() {
        if (hub == null) {
            hub = new Game();
            new Thread(hub).start();
            new Thread(new SnapshotSender(hub)).start();
        }

        return hub;
    }

}
