package org.example.util;

import org.example.cats.Cat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageUtility {

    public static Image dowloadImage(String imageUrl) throws IOException {
        return ImageIO.read(new URL(imageUrl));
    }

    public static Image resizeImage(Image image, int maxWidth, int maxHeight) {
        return image.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
    }

    public static ImageIcon createImageIcon(Image image) {
        return new ImageIcon(image);
    }

    public static ImageIcon dowloadAndResizeImage(Cat gatos){
        try{
            Image imagenOriginal = dowloadImage(gatos.getUrl());

            int maxWidth = ConfigConstants.MAX_WIDTH_CAT_IMAGEN;
            int maxHeight = ConfigConstants.MAX_HEIGHT_CAT_IMAGEN;

            boolean redimensionar = imagenOriginal.getWidth(null) >maxWidth ||
                    imagenOriginal.getHeight(null) > maxHeight;

            Image fondoGato = imagenOriginal;
            if(redimensionar){
                fondoGato = resizeImage(imagenOriginal,maxWidth,maxHeight);
            }
            return createImageIcon(fondoGato);

        }catch (IOException e){
            e.printStackTrace();
            return null;

        }
    }

}
