package model;

import controller.ClientController;
import data.Vector3;
import data.Vector4;

public class Player {

    private final ClientController client;

    private final int id;
    private final String name;

    private Vector3<Float> position;
    private Vector4<Float> quaternion;

    public Player(int id, String name, ClientController client) {
        this.id = id;
        this.name = name;
        this.client = client;

        position = new Vector3<>(0.0f, 0.0f, 0.0f);
        quaternion = new Vector4<>(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    public void setQuaternion(Vector4<Float> quaternion) {
        this.quaternion = quaternion;
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

    public Vector4<Float> getQuaternion() {
        return quaternion;
    }

    public ClientController getClient() {
        return client;
    }

}
