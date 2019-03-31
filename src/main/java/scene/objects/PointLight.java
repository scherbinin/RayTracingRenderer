package scene.objects;

import scene.LightType;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 16.03.2019.
 */
public class PointLight extends LightSource {
    private Point position;

    public PointLight(double intensity, Point position) {
        this.type = LightType.POINT;
        this.intensity = intensity;
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public double calculateBrightnessAtPoint(Point pointAtFigure, Vector normalAtPoint, Vector ray,
                                             Double reflectionForce, Point cameraMovingDelta) {
        //TODO: Here and in the DirectionalLight type we have the equal code, move it to base class
        double diffuseLight = 0;
        Vector lightDirection = getLightDirection(cameraMovingDelta, pointAtFigure);
        double shineLight = calculateShineForPoint(lightDirection, reflectionForce, normalAtPoint, ray);
        double dot = lightDirection.scalarMulti(normalAtPoint);

        if (dot > 0) {
            diffuseLight = intensity * dot / lightDirection.length();
        }

        return diffuseLight + shineLight;
    }

    public Vector getLightDirection(Point cameraMovingDelta, Point point) {
        return this.position.subtract(cameraMovingDelta).toVectorFromPoint(point);
    }
}
