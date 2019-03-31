package renderer;

import scene.primitives.Matrix;
import scene.primitives.Point;
import scene.primitives.Vector;

/**
 * Created by scher on 10.03.2019.
 */
public class ViewPort {
    private Point camera;
    private double distanceFromCamera;

    private int leftBorderX;
    private int UpBorderY;
    private int outputFrameWidth;
    private int outputFrameHeight;


    public ViewPort(Point camera, int outputFrameWidth, int outputFrameHeight, double zoom) {
        this.camera = camera;
        this.distanceFromCamera = zoom;
        this.outputFrameWidth = outputFrameWidth;
        this.outputFrameHeight = outputFrameHeight;

        this.leftBorderX = -outputFrameWidth / 2;
        this.UpBorderY = -outputFrameHeight / 2;
    }

    public Vector getVectorFromCameraToViewPortPoint(int x, int y) {
        double realCoordinateX = (leftBorderX + x) * (1.0 / outputFrameWidth);
        double realCoordinateY = -(UpBorderY + y) * (1.0 / outputFrameHeight);
        double realCoordinateZ = distanceFromCamera;

        return (new Point(realCoordinateX, realCoordinateY, realCoordinateZ)).subtract(camera).toVector();
    }


    public int getOutputFrameWidth() {
        return outputFrameWidth;
    }

    public int getOutputFrameHeight() {
        return outputFrameHeight;
    }

    public Point getCameraPosition() {
        return camera;
    }

    public double getDistanceFromCamera() {
        return distanceFromCamera;
    }
}
