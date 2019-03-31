package scene.primitives;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by scher on 09.03.2019.
 */
public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public static final Vector ZERO = new Vector(0, 0, 0);

    public Vector normalize() {
        double norm = 1.0 / length();
        this.x = this.x * norm;
        this.y = this.y * norm;
        this.z = this.z * norm;

        return this;
    }

    /**
     * Ray reflection at the point on surface by normal vector
     *
     * @param normal The normal vector of the some point of the some surface
     * @return Vector reflected by normal vector, at the point on the some surface
     */
    public Vector reflectByNormalVector(Vector normal) {
        //TODO: Optimize that method: new class instantiation should be there but not in te scalar multiplication
        double dot = this.scalarMulti(normal);
        return normal.multiply(2).multiply(dot).subtract(this);
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

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector multiply(double lamba) {
        return new Vector(this.x * lamba, this.y * lamba, this.z * lamba);
    }

    public Vector Add(Vector delta) {
        x += delta.getX();
        y += delta.getY();
        z += delta.getZ();

        return this;
    }

    public Vector subtract(double delta) {
        x += delta;
        y += delta;
        z += delta;

        return this;
    }

    public Vector subtract(Vector delta) {
        x -= delta.getX();
        y -= delta.getY();
        z -= delta.getZ();

        return this;
    }
}
