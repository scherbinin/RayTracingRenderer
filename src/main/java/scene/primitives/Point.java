package scene.primitives;

import javafx.beans.binding.ObjectExpression;
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
        return new Point(this.x - x, this.y - y, this.z - z);
    }

    public Point add(Point point) {
        return new Point(this.x + point.getX(), this.y + point.getY(), this.z + point.getZ());
    }

    public Point add(double x, double y, double z) {
        return new Point(this.x + x, this.y + y, this.z + z);
    }

    public Point multiply(double lamba) {
        return new Point(this.x*lamba, this.y*lamba, this.z*lamba);
    }

    public Vector toVectorFromPoint(Point from) {
        return toVectorFromPoint(from.getX(), from.getY(), from.getZ());
    }

    public Vector toVectorFromPoint(double x, double y, double z) {
        return new Vector(this.x - x, this.y - y, this.z - z);
    }

    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }

    public Matrix toMatrix() {
        return new Matrix(3, 1, new double[]{x, y, z});
    }

    @Override
    public String toString() {
        return "Point{ + " + x + ", " + y + ", " + z + " }";
    }

    @Override
    public boolean equals(Object vector) {
        if(vector !=null && vector instanceof Vector) {

            if (this.x == ((Vector)vector).getX() &&
                    this.y == ((Vector)vector).getY() &&
                    this.z == ((Vector)vector).getZ())
                return true;
        }

        return false;
    }
}
