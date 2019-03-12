package scene.objects;

import common.Configuration;
import javafx.geometry.Point3D;
import scene.FigureType;
import scene.primitives.ColorRGB;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 10.03.2019.
 */
public class Sphere extends Figure{
    private int radius;

    public Sphere(ColorRGB color, Point point, int radius) {
        super(FigureType.SPHERE, color, point);
        this.radius = radius;
    }

    public Sphere(ColorRGB color, int x, int y, int z, int radius) {
        super(FigureType.SPHERE, color, x, y, z);
        this.radius = radius;
    }

    public double checkOnIntersection(Vector ray) {
        Vector oc = Point.ZERO.toVector(getPointAttaching());
        double k1 = ray.scalarMulti(ray);
        double k2 = 2 * (ray.scalarMulti(oc));
        double k3 = oc.scalarMulti(oc) - radius*radius;
        double discriminant = k2 * k2  - 4.0 * k1 * k3;

        if(discriminant < 0)
            return Configuration.MAX_SCENE_DEEP;//Means no intersection exists

        double t1 = (-k2 + Math.sqrt(discriminant))/(2*k1);
        double t2 = (-k2 - Math.sqrt(discriminant))/(2*k1);

        if(t1 < t2 && t1 > 1.0)
            return t1;

        return t2;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | radius= " + getPointAttaching().toString();
    }
}
