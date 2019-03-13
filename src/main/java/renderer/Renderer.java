package renderer;

import common.Configuration;
import draw.Painter;
import scene.FigureType;
import scene.SceneContainer;
import scene.objects.Figure;
import scene.objects.Sphere;
import scene.primitives.ColorRGB;
import scene.primitives.Point;
import scene.primitives.Vector;
import java.util.List;

/**
 * Created by scher on 10.03.2019.
 */
public class Renderer {
    private Painter painter;
    private SceneContainer sceneContainer;
    private ViewPort viewPort;

    public Renderer(Painter painter, SceneContainer sceneContainer) {
        this.painter = painter;
        this.sceneContainer = sceneContainer;
        viewPort = new ViewPort(new Point(0,0,0), Configuration.WIDTH, Configuration.HEIGHT, 1);
    }

    /*
    * Return the time of calculations in milliseconds
    */
    public long renderCadre() {
        long startTime = System.currentTimeMillis();
        calculateNewCadre();
        painter.paintCadre();

        return startTime - System.currentTimeMillis();
    }

    /*
    * Return the time of calculations in milliseconds
     */
    private void calculateNewCadre() {
        for (int x = 0; x < viewPort.getOutputFrameWidth(); x++) {
            for (int y = 0; y < viewPort.getOutputFrameHeight(); y++) {
                ColorRGB color = traceRayForPoint(viewPort.getRayVector(x,y/*TODO PASS MATRIX OF TRANSITION AND ROTATION */));
                painter.paint(x, y, color);
            }
        }
    }

    private ColorRGB traceRayForPoint(Vector ray) {
        ColorRGB result = Configuration.BACKGROUND_COLOR;
        Figure intersectedFigure = lookForIntersection(ray);

        if (intersectedFigure != null) {
            result = calculateColorOfSurface(ray, intersectedFigure);
        }

        return result;
    }

    private Figure lookForIntersection(Vector ray) {
        List<Figure> sceneObjects = sceneContainer.getAllFigures();
        Figure intersectedFigure = null;

        for (Figure sceneObject : sceneObjects) {
            double minDistance = Configuration.MAX_SCENE_DEEP;
            double newDistance = Configuration.MAX_SCENE_DEEP;

            if (sceneObject.getFigureType().equals(FigureType.SPHERE)) {
                newDistance = ((Sphere) sceneObject).checkOnIntersection(ray);
            }

            if (newDistance < minDistance && newDistance > 1) {
                minDistance = newDistance;
                intersectedFigure = sceneObject;
            }
        }

        return intersectedFigure;
    }

    private ColorRGB calculateColorOfSurface(Vector ray, Figure intersectedFigure) {
        if (intersectedFigure != null)
            return intersectedFigure.getColor();

        return Configuration.BACKGROUND_COLOR;
    }
}
