package draw;

import scene.primitives.ColorRGB;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by scher on 05.03.2019.
 */
class Canvas extends JComponent {
    private BufferedImage firstBuffer;
    private BufferedImage secondBuffer;
    private BufferedImage currentBuffer;
    private int width;
    private int height;
    private int[] linerCadre;

    public Canvas(int with, int height) {
        this.width = with;
        this.height = height;
        firstBuffer = new BufferedImage(with, height, BufferedImage.TYPE_INT_RGB);
        secondBuffer = new BufferedImage(with, height, BufferedImage.TYPE_INT_RGB);
        currentBuffer = firstBuffer;
        linerCadre = new int[with * height * 3];
    }

    public void drawCadre(ColorRGB cadre[]) {
        BufferedImage buffer = getInMemoryBuffer();
        buffer.getRaster().setPixels(0, 0, width, height, convertCadreToArray(cadre));
        switchBuffer();
        repaint();
    }

    private int[] convertCadreToArray(ColorRGB[] cadre) {
        for (int i = 0; i < width * height; i++) {
            linerCadre[i * 3] = cadre[i].getRed();
            linerCadre[i * 3 + 1] = cadre[i].getGreen();
            linerCadre[i * 3 + 2] = cadre[i].getBlue();
        }

        return linerCadre;
    }

    private BufferedImage getInMemoryBuffer() {
        if (currentBuffer == firstBuffer)
            return secondBuffer;
        else
            return firstBuffer;
    }

    private void switchBuffer() {
        if (currentBuffer == firstBuffer)
            currentBuffer = secondBuffer;
        else
            currentBuffer = firstBuffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(currentBuffer, 0, 0, null);
        super.paintComponent(g);
    }
}
