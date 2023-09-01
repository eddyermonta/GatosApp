package org.example.util;

import org.example.cats.model.Cat;
import org.example.cats.model.CatImage;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageUtility {
    static int maxWidth = ConfigConstants.MAX_WIDTH_CAT_IMAGEN;
    static int maxHeight = ConfigConstants.MAX_HEIGHT_CAT_IMAGEN;
    /**
     *
     * @param imageUrl Url of cat
     * @return Cat image
     */
    public static Image dowloadImage(String imageUrl) throws IOException {
        HttpsURLConnection https = HttpUtility.getHttpCatImage(new URL(imageUrl));
        return ImageIO.read(https.getInputStream());
    }
    /**
     *
     * @param image cat image
     * @param maxWidth accepted for app
     * @param maxHeight accepted for app
     * @return catimage resized
     */
    public static Image resizeImage(Image image, int maxWidth, int maxHeight) {
        return image.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
    }
    /**
     *
     * @param image cat image
     * @return icon of cat image
     */
    public static ImageIcon createImageIcon(Image image) {
        return new ImageIcon(image);
    }
    /**
     *
     * @param cat catImage object
     * @return catimage transformed in an icon and resized
     */
    public static ImageIcon dowloadAndResizeImage(CatImage cat){
        try{
            Image imagenOriginal = dowloadImage(cat.getUrl());
            Image fondoGato = imagenOriginal;

            boolean redimensionar =
                            imagenOriginal.getWidth(null) >maxWidth ||
                            imagenOriginal.getHeight(null) > maxHeight
                    ;

            fondoGato = (redimensionar)? resizeImage(imagenOriginal,maxWidth,maxHeight):fondoGato;

            return createImageIcon(fondoGato);

        }catch (IOException e){
           LoggerUtility.error(Errors.IMAGEN_ERROR +e.getMessage());
            return null;
        }
    }
    /**
     *
     * @param gatos cat object
     * @return cat transformed in an icon and resized
     */
    public static ImageIcon dowloadAndResizeImage(Cat gatos){
        try{
            Image imagenOriginal = dowloadImage(gatos.getImage().getUrl());
            Image fondoGato = imagenOriginal;

            boolean redimensionar =
                    imagenOriginal.getWidth(null) >maxWidth ||
                    imagenOriginal.getHeight(null) > maxHeight
                    ;

            fondoGato = (redimensionar)? resizeImage(imagenOriginal,maxWidth,maxHeight):fondoGato;

            return createImageIcon(fondoGato);

        }catch (IOException e){
            LoggerUtility.error(Errors.IMAGEN_ERROR +e.getMessage());
            return null;
        }
    }

}
