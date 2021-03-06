package scene;

import scene.objects.Figure;
import scene.objects.LightSource;

import java.util.List;

/**
 * Created by scher on 09.03.2019.
 */
public class SceneContainer {
    private SceneLoader sceneLoader;

    public SceneContainer() {
        sceneLoader = new SceneLoader();
    }

    public List<Figure> getAllFigures() {
        return sceneLoader.getSceneObjects();
    }

    public List<LightSource> getLightSources() {
        return sceneLoader.getLightSources();
    }
}
