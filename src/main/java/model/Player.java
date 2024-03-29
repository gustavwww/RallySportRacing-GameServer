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
    private int color;

    private Vector3<Float> position;
    private Vector4<Float> quaternion;

    private float speed;
    private Vector3<Float> speedVec;

    private String soundString;

    private List<Tuple<Vector3<Float>, Vector4<Float>>> objects;

    public Player(int id, String name, ClientController client) {
        this.id = id;
        this.name = name;
        this.client = client;

        color = 0;

        position = new Vector3<>(0.0f, 0.0f, 0.0f);
        quaternion = new Vector4<>(0.0f, 0.0f, 0.0f, 0.0f);

        speed = 0.0f;
        speedVec = new Vector3<>(0.0f, 0.0f, 0.0f);

        soundString = "00000";

        objects = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            objects.add(new Tuple<>(new Vector3<>(0f,0f,0f), new Vector4<>(0f,0f,0f,0f)));
        }

    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    public void setQuaternion(Vector4<Float> quaternion) {
        this.quaternion = quaternion;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setSpeedVec(Vector3<Float> speedVec) {
        this.speedVec = speedVec;
    }

    public void setSoundString(String soundString) {
        this.soundString = soundString;
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

    public int getColor() {
        return color;
    }

    public Vector3<Float> getPosition() {
        return position;
    }

    public Vector4<Float> getQuaternion() {
        return quaternion;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector3<Float> getSpeedVec() {
        return speedVec;
    }

    public String getSoundString() {
        return soundString;
    }

    public List<Tuple<Vector3<Float>, Vector4<Float>>> getObjects() {
        return objects;
    }

    public ClientController getClient() {
        return client;
    }

}
