package scene.objects;

import common.Configuration;
import scene.FigureType;
import scene.primitives.ColorRGB;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 10.03.2019.
 */
public class Sphere extends Figure {
    private int radius;

    public Sphere(ColorRGB color, Point point, int radius, double reflectionForce, double reflectivity) {
        super(color, point, reflectionForce, reflectivity);
        this.radius = radius;
        this.figureType = FigureType.SPHERE;
    }

    public Sphere(ColorRGB color, int x, int y, int z, int radius, double reflectionForce, double reflectivity) {
        super(color, x, y, z, reflectionForce, reflectivity);
        this.radius = radius;
        this.figureType = FigureType.SPHERE;
    }

    @Override
    public Vector getNormalAtPoint(Point cameraMovingDelta, Point point) {
        return point.toVectorFromPoint(getPointAttaching().subtract(cameraMovingDelta)).normalize();
    }

    public double checkOnIntersectionInDistanceRange(Point camera, Vector viewDirection, double minDistance,
                                                     double maxDistance, Point cameraMovingDelta) {
        Vector oc = camera.toVectorFromPoint(getPointAttaching().subtract(cameraMovingDelta));
        double k1 = viewDirection.scalarMulti(viewDirection);
        double k2 = 2 * (viewDirection.scalarMulti(oc));
        double k3 = oc.scalarMulti(oc) - radius * radius;
        double discriminant = k2 * k2 - 4.0 * k1 * k3;
        double minEquityRootInInterval = Configuration.MAX_SCENE_DEPTH;

        if (discriminant < 0)
            return Configuration.MAX_SCENE_DEPTH;//Means no intersection exists

        double t1 = (-k2 + Math.sqrt(discriminant)) / (2 * k1);
        double t2 = (-k2 - Math.sqrt(discriminant)) / (2 * k1);

        if (t1 > minDistance && t1 < maxDistance && t1 < minEquityRootInInterval)
            minEquityRootInInterval = t1;

        if (t2 > minDistance && t2 < maxDistance && t2 < minEquityRootInInterval)
            minEquityRootInInterval = t2;

        return minEquityRootInInterval;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | radius= " + radius;
    }
}
