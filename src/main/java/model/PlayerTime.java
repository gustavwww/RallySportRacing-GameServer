package model;

public class PlayerTime {

    private final Player player;
    private float time;

    public PlayerTime(Player player, float time) {
        this.player = player;
        this.time = time;
    }

    public Player getPlayer() {
        return player;
    }


    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }


}
