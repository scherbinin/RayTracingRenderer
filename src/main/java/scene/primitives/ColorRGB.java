package scene.primitives;

import java.awt.*;

/**
 * Created by scher on 06.03.2019.
 */
public class ColorRGB {
    public static final ColorRGB GREEN = new ColorRGB(0 , 255, 0);
    public static final ColorRGB RED = new ColorRGB(255 , 0, 0);
    public static final ColorRGB BLUE = new ColorRGB(0 , 0, 255);
    public static final ColorRGB WHITE = new ColorRGB(255 , 255, 255);
    public static final ColorRGB BLACK = new ColorRGB(0 , 0, 0);
    public static final ColorRGB YELLOW = new ColorRGB(255 , 255, 0);

    private int red;
    private int green;
    private int blue;

    public ColorRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public ColorRGB adjustBrightness(double coefficient) {
        int newRed = (int)((double)red * coefficient);
        int newGreen = (int)((double)green * coefficient);
        int newBlue = (int)((double)blue * coefficient);

        if( newRed >= 255) newRed = 255;
        if( newGreen >= 255) newGreen = 255;
        if( newBlue >= 255) newBlue = 255;

        return new ColorRGB(newRed, newGreen, newBlue);

    }

    @Override
    public String toString() {
        return "RGB{ + " + red + ", " + green + ", " + blue + " }";
    }

    public ColorRGB add(ColorRGB colorRGB) {
        int newRed = (int)((double)red + colorRGB.getRed());
        int newGreen = (int)((double)green + colorRGB.getGreen());
        int newBlue = (int)((double)blue + colorRGB.getBlue());

        if( newRed >= 255) newRed = 255;
        if( newGreen >= 255) newGreen = 255;
        if( newBlue >= 255) newBlue = 255;

        return new ColorRGB(newRed, newGreen, newBlue);
    }
}
