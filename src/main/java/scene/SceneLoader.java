package scene;

import scene.objects.*;
import scene.primitives.ColorRGB;
import scene.primitives.Point;
import scene.primitives.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by scher on 09.03.2019.
 */
class SceneLoader {
    private List<Figure> primitives;
    private List<LightSource> lights;

    public SceneLoader() {
        //TODO: In plans implement reading from the picked JSON file
        primitives = fillFakeObjectsOnScene();
        lights = fillFakeLightSourcesOnScene();
    }

    public List<Figure> getSceneObjects() {
        return primitives;
    }

    public List<LightSource> getLightSources() {
        return lights;
    }

    private List<Figure> fillFakeObjectsOnScene() {
        Figure sphere1 = new Sphere(ColorRGB.RED, 0, -1, 3, 1, 500, 0.2);
        Figure sphere2 = new Sphere(ColorRGB.BLUE, 2, 0, 4, 1, 500, 0.3);
        Figure sphere3 = new Sphere(ColorRGB.GREEN, -2, 1, 4, 1, 10, 0.4);

        Figure sphere4 = new Sphere(ColorRGB.YELLOW, 0,  -5001, 0, 5000, 1000, 0.5);

        return Arrays.asList(sphere1, sphere2, sphere3, sphere4);
    }

    private List<LightSource> fillFakeLightSourcesOnScene() {
        LightSource lightSource1 = new AmbientLight(0.2);
        LightSource lightSource2 = new PointLight(0.6, new Point(2, 1, 0));
        LightSource lightSource3 = new DirectionalLight(0.2, new Vector(1, 4, 4));

        return Arrays.asList(lightSource1, lightSource2, lightSource3);
    }
}
