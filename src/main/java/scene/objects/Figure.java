package scene.objects;

import scene.FigureType;
import scene.primitives.ColorRGB;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 09.03.2019.
 */
public abstract class Figure {
    protected FigureType figureType = FigureType.UNKNOWN;
    private Point pointAttaching;
    private ColorRGB color;
    private double shine;
    private double reflectivity;

    public Figure(ColorRGB color, Point point, double shine, double reflectivity) {
        this.shine = shine;
        this.pointAttaching = point;
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Figure(ColorRGB color, int x, int y, int z, double shine, double reflectivity) {
        this.shine = shine;
        this.pointAttaching = new Point(x,y,z);
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Point getPointAttaching() {
        return pointAttaching;
    }

    public ColorRGB getColor() {
        return color;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public abstract Vector getNormalAtPoint(Point cameraMovingDelta, Point point);

    public double getReflectivity() {
        return reflectivity;
    }

    @Override
    public String toString() {
        return " | pointAttaching=" + pointAttaching.toString() +
                " | shine=" + shine +
                " | Type=" + figureType.toString() +
                " | color= " + color.toString();
    }

    public double getShine() {
        return shine;
    }
}
