package model;

import controller.ClientController;
import data.Vector3;

public class Player {

    private final ClientController client;

    private final String name;
    private Vector3<Float> position;

    public Player(String name, ClientController client) {
        this.name = name;
        this.client = client;

        position = new Vector3<>(0.0f, 0.0f, 0.0f);
    }

    public String getName() {
        return name;
    }

    public Vector3<Float> getPosition() {
        return position;
    }

    public ClientController getClient() {
        return client;
    }

}
