package data;

public class Vector3<T extends Number> {

    public final T x, y, z;

    public Vector3(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getLength() {
        return Math.sqrt(Math.pow((double) x, 2) + Math.pow((double) y, 2) + Math.pow((double) z, 2));
    }



}
