package scene.objects;

import scene.LightType;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 16.03.2019.
 */
public class DirectionalLight extends LightSource {
    private Vector direction;

    public DirectionalLight(double intensity, Vector direction) {
        this.type = LightType.DIRECTIONAL;
        this.intensity = intensity;
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction;
    }

    @Override
    public double calculateBrightnessAtPoint(Point pointAtFigure, Vector normalAtPoint, Vector ray,
                                             Double reflectionForce, Point cameraMovingDelta) {
        double diffuseLight = 0;
        double shineLight =  calculateShineForPoint(direction, reflectionForce, normalAtPoint, ray);
        double dot = direction.scalarMulti(normalAtPoint);

        if( dot>0) {
            diffuseLight = intensity * dot / direction.length();
        }

        return diffuseLight + shineLight;
    }

    public Vector getLightDirection(Point cameraMovingDelta, Point pointAtFigure) {
        return direction;
    }
}
