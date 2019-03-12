package scene.objects;

import scene.FigureType;
import scene.primitives.ColorRGB;
import scene.primitives.Point;

/**
 * Created by scher on 09.03.2019.
 */
public abstract class Figure {
    private final FigureType figureType = FigureType.SPHERE;
    private Point pointAttaching;
    private ColorRGB color;

    public Figure(FigureType type, ColorRGB color, Point point) {
        this.pointAttaching = point;
        this.color = color;
    }

    public Figure(FigureType type, ColorRGB color, int x, int y, int z) {
        this.pointAttaching = new Point(x,y,z);
        this.color = color;
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

    @Override
    public String toString() {
        return "pointAttaching=" + pointAttaching.toString() +
                "Type=" + figureType.toString() +
                " | color= " + color.toString();
    }
}
