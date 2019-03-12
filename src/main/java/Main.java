import common.Configuration;
import draw.Painter;
import renderer.Renderer;
import scene.SceneContainer;

/**
 * Created by scher on 05.03.2019.
 */
public class Main {
    public static void main(String[] args) {
        Painter painter = new Painter(Configuration.WIDTH, Configuration.HEIGHT);
        SceneContainer sceneContainer = new SceneContainer();

        Renderer renderer = new Renderer(painter, sceneContainer);
        System.out.println(renderer.renderCadre());
    }
}
