package model;

import controller.ClientController;
import data.Vector3;

public class Player {

    private final ClientController client;

    private final int id;
    private final String name;

    private Vector3<Float> position;
    private Vector3<Float> orientation;

    public Player(int id, String name, ClientController client) {
        this.id = id;
        this.name = name;
        this.client = client;

        position = new Vector3<>(0.0f, 0.0f, 0.0f);
        orientation = new Vector3<>(0.0f, 0.0f, 0.0f);
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    public void setOrientation(Vector3<Float> orientation) {
        this.orientation = orientation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Vector3<Float> getPosition() {
        return position;
    }

    public Vector3<Float> getOrientation() {
        return orientation;
    }

    public ClientController getClient() {
        return client;
    }

}
