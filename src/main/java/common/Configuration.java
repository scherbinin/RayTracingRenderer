package common;

import scene.primitives.ColorRGB;

/**
 * Created by scher on 10.03.2019.
 */
public class Configuration {
    //TODO: Consider Spring config
    public final static int MAX_SCENE_DEPTH = 2000;
    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;
    public final static int REFLECTION_DEPTH = 2;
    public final static ColorRGB BACKGROUND_COLOR = ColorRGB.BLACK;
}
