package scene;

import scene.objects.Figure;
import scene.objects.Sphere;
import scene.primitives.ColorRGB;

import java.util.Arrays;
import java.util.List;

/**
 * Created by scher on 09.03.2019.
 */
class SceneLoader {
    private List<Figure> sceneLoader;
    //TODO: Some light settings and etc

    public SceneLoader() {
        //TODO: In plans implement reading from the picked JSON file
        sceneLoader = fillFakeObjectsOnScene();
    }

    public List<Figure> getSceneObjects() {
        return sceneLoader;
    }

    private List<Figure> fillFakeObjectsOnScene() {
        Figure sphere1 = new Sphere(ColorRGB.RED, 0, -1, 3, 1);
        Figure sphere2 = new Sphere(ColorRGB.BLUE, 2, 0, 4, 1);
        Figure sphere3 = new Sphere(ColorRGB.GREEN, -2, 0, 4, 1);

        return Arrays.asList(sphere1, sphere2, sphere3);
    }
}
