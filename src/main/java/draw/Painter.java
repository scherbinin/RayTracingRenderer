package draw;

import scene.primitives.ColorRGB;

import javax.swing.*;
import java.awt.*;

/**
 * Created by scher on 05.03.2019.
 */
public class Painter {
    private JFrame frame;
    private int width;
    private int height;
    private Canvas canvas;
    ColorRGB[] linearCadre;

    public Painter(int width, int height) {
        this.width = width;
        this.height = height;
        linearCadre = new ColorRGB[width * height];
        initWindow();
    }

    private void initWindow() {
        frame = new JFrame("Rendered scene");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(1, 1));

        canvas = new Canvas(width, height);
        frame.add(canvas);
        frame.setVisible(true);
    }

    public void paintCadre() {
        canvas.drawCadre(linearCadre);
    }

    public void paint(int x, int y, ColorRGB color) {
        linearCadre[x + y * width] = color;
    }
}
