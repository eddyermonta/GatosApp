package org.example.cats.services;

import org.example.cats.controller.CatController;
import org.example.cats.model.Cat;
import org.example.cats.model.CatImage;
import org.example.cats.view.UI;
import org.example.util.*;
import javax.swing.*;

public class CatService {

    public static ImageIcon getImageIcon(CatImage gato){
        return ImageUtility.dowloadAndResizeImage(gato);
    }
    public static ImageIcon getImageIcon(Cat gato){
        return ImageUtility.dowloadAndResizeImage(gato);
    }
    public static CatImage getGato(CatImage gatos){
        return new CatImage( gatos.getId(),gatos.getUrl());
    }
    public static void cargarGatos(){
        CatImage gato = getGato(CatController.getRandomCat());
        ImageIcon fondoGato = getImageIcon(gato);
        UI.menuGatos(gato,fondoGato);
    }
    public static void favoritoGato(CatImage gato) {
        CatController.pushFavoriteCat(gato);
    }
    public static void cargarGatosFavoritos(){
        Cat gatoFavorito = CatController.getFavoriteCat(
                ConfigReader.getCode(ConfigConstants.CONFIG_PROPERTIES_DEV,
                        ConfigConstants.API_KEY)
        );
        ImageIcon fondoGato = getImageIcon(gatoFavorito);
        if (gatoFavorito != null) {
            UI.menuFavoritos(gatoFavorito,fondoGato);
        }
    }
    public static void eliminarFavorito(Cat gatoFavorito) {
        CatController.deleteFavoriteCat(gatoFavorito);
    }

}
