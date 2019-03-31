package scene.objects;

import scene.LightType;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 16.03.2019.
 */
public class AmbientLight extends LightSource {
    public AmbientLight(double intensity) {
        this.type = LightType.AMBIENT;
        this.intensity = intensity;
    }

    @Override
    public double calculateBrightnessAtPoint(Point pointAtFigure, Vector normalAtPoint, Vector ray,
                                             Double reflectionForce, Point cameraMovingDelta) {
        return intensity;
    }

    public Vector getLightDirection(Point cameraMovingDelta, Point point) {
        return Vector.ZERO;
    }
}
