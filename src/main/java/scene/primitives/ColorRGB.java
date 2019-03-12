package scene.primitives;

/**
 * Created by scher on 06.03.2019.
 */
public class ColorRGB {
    public static final ColorRGB GREEN = new ColorRGB(0 , 255, 0);
    public static final ColorRGB RED = new ColorRGB(255 , 0, 0);
    public static final ColorRGB BLUE = new ColorRGB(0 , 0, 255);
    public static final ColorRGB WHITE = new ColorRGB(255 , 255, 255);

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

    @Override
    public String toString() {
        return "RGB{ + " + red + ", " + green + ", " + blue + " }";
    }
}
