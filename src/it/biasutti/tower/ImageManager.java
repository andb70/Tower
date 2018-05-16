package it.biasutti.tower;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    String _homeDir = "";
    public ImageManager(String homeDir) {
        _homeDir = homeDir;
    }

    public void save(int window, int imgeExt, int[] data, String fileName) {
        // http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/CreateBufferredImagewithcolorsbasedonintegerarray.htm

        int length = data.length;
        /*int red = 255;
        int green = 128;
        int blue = 64;
        data[i++] = (red << 16) | (green << 8) | blue;*/

        try {
            BufferedImage image = new BufferedImage(window, window, BufferedImage.TYPE_INT_RGB);
            image.setRGB(Math.floorDiv(window-imgeExt,2), Math.floorDiv(window-imgeExt,2), imgeExt, imgeExt, data, 0, imgeExt);

            File outputFile = new File(_homeDir + fileName);
            ImageIO.write(image, "png", outputFile);

        } catch (IOException e) {
            System.out.println("FileSave err: " + e);
        }
    }
}
