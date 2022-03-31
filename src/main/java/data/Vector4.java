package data;

public class Vector4<T extends Number> {

    public final T x, y, z, w;

    public Vector4(T x, T y, T z, T w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getLength() {
        return Math.sqrt(Math.pow((double) x, 2) + Math.pow((double) y, 2) + Math.pow((double) z, 2) + Math.pow((double) w, 2));
    }



}
