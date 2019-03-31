package scene.objects;

import scene.LightType;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 16.03.2019.
 */
public abstract class LightSource {
    protected LightType type;
    protected double intensity;

    public LightType getType() {
        return type;
    }

    public double getIntensity() {
        return intensity;
    }

    public abstract double calculateBrightnessAtPoint(Point pointAtFigure, Vector normalAtPoint, Vector ray,
                                                      Double reflectionForce, Point cameraMovingDelta);

    public abstract Vector getLightDirection(Point cameraMovingDelta, Point point);

    protected double calculateShineForPoint(Vector light, Double reflectionForce, Vector normalAtPoint, Vector viewDirection) {
        if(reflectionForce <= 0)
            return 0;

        Vector lightReflection = light.reflectByNormalVector(normalAtPoint);
        double rayDotLightReflection = viewDirection.multiply(-1).scalarMulti(lightReflection);

        if(rayDotLightReflection > 0) {
            return intensity * Math.pow(rayDotLightReflection / (lightReflection.length() * viewDirection.length()), reflectionForce);
        }

        return 0;
    }
}
