package scene.primitives;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by scher on 09.03.2019.
 */
public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public Point normalize() {
        //TODO: implement it
        throw new NotImplementedException();
    }

    public double angle(Vector vector) {
        return angle(vector.getX(), vector.getY(), vector.getZ());
    }

    public int angle(double x, double y, double z) {
        //TODO: calculate angle between those vectors in degrees
        throw new NotImplementedException();
    }

    public double scalarMulti(Vector vector) {
       return scalarMulti(vector.getX(), vector.getY(), vector.getZ());
    }

    public double scalarMulti(double x, double y, double z) {
       return this.x * x + this.y * y + this.z * z;
    }

    public double distance() {
        return (double) Math.sqrt(x*x + y*y + z*z);
    }

    public Vector multiply(double lamba) {
        x *= lamba;
        y *= lamba;
        z *= lamba;

        return this;
    }

    /*
    Shift vector on a some delta(x,y,z)
     */
    public Vector shift(Point delta) {
        x += delta.getX();
        y += delta.getY();
        z += delta.getZ();

        return this;
    }
}
