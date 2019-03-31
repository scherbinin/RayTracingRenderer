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
//        renderer.translate(1, 1.3, -2.5);
//        renderer.rotate(10, -10, -30);

        for (double i =0; i<5; i = i+ 0.1) {
            renderer.translate(0, 0, (-1)*i);
            System.out.println("Cadre render time= " + renderer.renderCadre());
        }

    }
}
