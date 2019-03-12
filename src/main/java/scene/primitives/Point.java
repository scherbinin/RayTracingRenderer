package scene.primitives;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by scher on 09.03.2019.
 *
 * Of course the Point3D class from package javafx.geometry could be used, but here i would like to go through all moments,
 * to understand deeply and develop all algorithms which follows from linear algebra and is required here.
 */
public class Point {
    protected double x;
    protected double y;
    protected double z;
    public static final Point ZERO = new Point(0, 0, 0);

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getDistance(Point secondPoint) {
        return getDistance(secondPoint.getX(), secondPoint.getY(), secondPoint.getZ());
    }

    public double getDistance(double x, double y, double z) {
        //TODO implement it
        throw new NotImplementedException();
    }

    public Point subtract(Point secondPoint) {
        return subtract(secondPoint.getX(), secondPoint.getY(), secondPoint.getZ());
    }

    public Point subtract(double x, double y, double z) {
        //TODO implement it
        throw new NotImplementedException();
    }

    public Point multiply(double lamba) {
        return new Point(x*lamba, y*lamba, z*lamba);
    }

    public Vector toVector() {
        return toVector(0, 0, 0);
    }

    public Vector toVector(Point from) {
        return toVector(from.getX(), from.getY(), from.getZ());
    }

    public Vector toVector(double x, double y, double z) {
        return new Vector(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public String toString() {
        return "Point{ + " + x + ", " + y + ", " + z + " }";
    }


}
