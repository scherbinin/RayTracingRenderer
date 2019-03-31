package renderer;

import common.Configuration;
import draw.Painter;
import javafx.util.Pair;
import scene.FigureType;
import scene.LightType;
import scene.SceneContainer;
import scene.objects.*;
import scene.primitives.ColorRGB;
import scene.primitives.Matrix;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 10.03.2019.
 */
public class Renderer {
    private Painter painter;
    private SceneContainer sceneContainer;
    private ViewPort viewPort;
    private int angleX;
    private int angleY;
    private int angleZ;
    private double deltaX;
    private double deltaY;
    private double deltaZ;

    public Renderer(Painter painter, SceneContainer sceneContainer) {
        this.painter = painter;
        this.sceneContainer = sceneContainer;
        viewPort = new ViewPort(Point.ZERO, Configuration.WIDTH, Configuration.HEIGHT, 1);
    }

    /*
    * Return the time of calculations in milliseconds
    */
    public long renderCadre() {
        long startTime = System.currentTimeMillis();
        calculateNewCadre();
        painter.paintCadre();

        return System.currentTimeMillis() - startTime;
    }

    public void rotate(int angleX, int angleY, int angleZ) {
        this.angleX = angleX;
        this.angleY = angleY;
        this.angleZ = angleZ;
    }

    public void translate(double deltaX, double deltaY, double deltaZ) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }

    public void resetRotation() {
        this.angleX = 0;
        this.angleY = 0;
        this.angleZ = 0;
    }

    public void resetTranslation() {
        this.deltaX = 0;
        this.deltaY = 0;
        this.deltaZ = 0;
    }

    /*
    * Return the time of calculations in milliseconds
     */
    private void calculateNewCadre() {
        Point cameraMovingDelta = new Point(deltaX, deltaY, deltaZ);

        for (int x = 0; x < viewPort.getOutputFrameWidth(); x++) {
            for (int y = 0; y < viewPort.getOutputFrameHeight(); y++) {
                Vector rotatedViewDirection = Matrix.rotate(viewPort.getVectorFromCameraToViewPortPoint(x, y),
                        this.angleX, angleY, angleZ);

                ColorRGB color = traceRayForPoint(viewPort.getCameraPosition(),
                        rotatedViewDirection,
                        viewPort.getDistanceFromCamera(),
                        Configuration.MAX_SCENE_DEPTH,
                        Configuration.REFLECTION_DEPTH, cameraMovingDelta);

                painter.paint(x, y, color);
            }
        }
    }

    private ColorRGB traceRayForPoint(Point camera, Vector cameraToViewPointVector, double minDistance,
                                      double maxDistance, int reflectivityDepth, Point cameraMovingDelta) {
        ColorRGB resultColorAtPoint = Configuration.BACKGROUND_COLOR;
        Pair<Double, Figure> distanceToClosestFigure = lookForIntersection(cameraToViewPointVector, camera,
                minDistance, maxDistance, cameraMovingDelta);

        if (distanceToClosestFigure.getValue() != null) {
            Figure intersectedFigure = distanceToClosestFigure.getValue();
            double distanceToFigure = distanceToClosestFigure.getKey();
            Point pointAtFigure = cameraToViewPointVector.multiply(distanceToFigure);
            Vector normalAtPoint = intersectedFigure.getNormalAtPoint(cameraMovingDelta, pointAtFigure).normalize();
            double surfaceReflectivity = intersectedFigure.getReflectivity();

            resultColorAtPoint = calculateColorOfPointAtFigure(pointAtFigure, cameraToViewPointVector,
                    intersectedFigure, normalAtPoint, cameraMovingDelta);

            if(reflectivityDepth > 0 && intersectedFigure.getReflectivity() > 0 ) {
                ColorRGB reflectedColor = traceRayForPoint(pointAtFigure,
                        cameraToViewPointVector.multiply(-1).reflectByNormalVector(normalAtPoint),
                        0.001,
                        Configuration.MAX_SCENE_DEPTH, reflectivityDepth - 1, cameraMovingDelta);

                resultColorAtPoint = resultColorAtPoint.adjustBrightness(1 - surfaceReflectivity).
                        add(reflectedColor.adjustBrightness(surfaceReflectivity));
            }
        }

        return resultColorAtPoint;
    }

    private Pair<Double, Figure> lookForIntersection(Vector viewDirection, Point camera, double minDistance,
                                                     double maxDistance, Point cameraMovingDelta) {
        Figure intersectedFigure = null;
        double minIntersectionDistance = Configuration.MAX_SCENE_DEPTH;

        for (Figure sceneObject : sceneContainer.getAllFigures()) {
            double newDistance = Configuration.MAX_SCENE_DEPTH;

            if (sceneObject.getFigureType().equals(FigureType.SPHERE)) {
                newDistance = ((Sphere) sceneObject).checkOnIntersectionInDistanceRange(camera, viewDirection,
                        minDistance, maxDistance, cameraMovingDelta);
            }

            if (newDistance < minIntersectionDistance) {
                minIntersectionDistance = newDistance;
                intersectedFigure = sceneObject;
            }
        }

        return new Pair<>(minIntersectionDistance, intersectedFigure);
    }

    private ColorRGB calculateColorOfPointAtFigure(Point pointAtFigure, Vector viewDirection, Figure figure,
                                                   Vector normal, Point cameraMovingDelta) {
        double summarizedBrightness = 0;

        for (LightSource lightSource : sceneContainer.getLightSources()) {
            if (!isPointInShadow(lightSource, pointAtFigure, figure, cameraMovingDelta))
                summarizedBrightness += lightSource.calculateBrightnessAtPoint(pointAtFigure, normal, viewDirection,
                        figure.getShine(), cameraMovingDelta);
        }

        return figure.getColor().adjustBrightness(summarizedBrightness);
    }

    private boolean isPointInShadow(LightSource lightSource, Point pointAtFigure, Figure figure, Point cameraMovingDelta) {
        if (!lightSource.getType().equals(LightType.AMBIENT)) {
            double minDistance =  0.01;
            double maxDistance = lightSource.getType().equals(LightType.POINT) ? 1.0 : Configuration.MAX_SCENE_DEPTH;
            Vector lightDirection = lightSource.getLightDirection(cameraMovingDelta, pointAtFigure);
            Pair<Double, Figure> distanceToClosestFigure = lookForIntersection(lightDirection, pointAtFigure,
                    minDistance, maxDistance, cameraMovingDelta);

            if (distanceToClosestFigure.getValue() != null && !figure.equals(distanceToClosestFigure.getValue())) {
                return true;
            }
        }
        return false;
    }
}
