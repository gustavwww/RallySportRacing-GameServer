package model;

import controller.ClientController;
import data.Tuple;
import data.Vector3;
import data.Vector4;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final ClientController client;

    private final int id;
    private final String name;

    private Vector3<Float> position;
    private Vector4<Float> quaternion;

    private List<Tuple<Vector3<Float>, Vector4<Float>>> objects;

    public Player(int id, String name, ClientController client) {
        this.id = id;
        this.name = name;
        this.client = client;

        position = new Vector3<>(0.0f, 0.0f, 0.0f);
        quaternion = new Vector4<>(0.0f, 0.0f, 0.0f, 0.0f);
        objects = new ArrayList<>();
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    public void setQuaternion(Vector4<Float> quaternion) {
        this.quaternion = quaternion;
    }

    public void setObjects(List<Tuple<Vector3<Float>, Vector4<Float>>> objects) {
        this.objects = objects;
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

    public List<Tuple<Vector3<Float>, Vector4<Float>>> getObjects() {
        return objects;
    }

    public ClientController getClient() {
        return client;
    }

}
