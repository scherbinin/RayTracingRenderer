package renderer;

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
        //Initially we are going to look along Z axis
        this.camera = camera;
        this.distanceFromCamera = zoom;
        this.outputFrameWidth = outputFrameWidth;
        this.outputFrameHeight = outputFrameHeight;

        this.leftBorderX = -outputFrameWidth / 2;
        this.UpBorderY = -outputFrameHeight / 2;
    }

    public Vector getRayVector(int x, int y) {
        double realCoordinateX = (leftBorderX + x) * (1.0 / outputFrameWidth);
        double realCoordinateY = -(UpBorderY + y) * (1.0 / outputFrameHeight);

        return new Vector(realCoordinateX, realCoordinateY, camera.getZ() + distanceFromCamera);
    }

    public int getOutputFrameWidth() {
        return outputFrameWidth;
    }

    public int getOutputFrameHeight() {
        return outputFrameHeight;
    }
}
