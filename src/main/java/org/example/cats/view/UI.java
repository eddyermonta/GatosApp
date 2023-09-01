package org.example.cats.view;

import org.example.cats.model.Cat;
import org.example.cats.model.CatImage;
import org.example.cats.services.CatService;
import org.example.util.ConfigConstants;
import javax.swing.*;
import java.util.Arrays;

public class UI {
    public static void menuPrincipal(){
        int opcionMenu = -1;
        String [] botones = {ConfigConstants.VER_GATOS,ConfigConstants.FAVORITOS,ConfigConstants.SALIR};
        do {

            String opcionElegida = (String) JOptionPane.showInputDialog(
                    null,
                    ConfigConstants.GATITOS_JAVA,
                    ConfigConstants.MENU_PRINCIPAL,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botones,
                    botones[0]
            );

            opcionMenu = getOpcionMenu(botones,opcionElegida);

            switch (opcionMenu){
                case 0:
                    CatService.cargarGatos();
                    break;
                case 1:
                    CatService.cargarGatosFavoritos();
                    break;
                default:
                    break;
            }

        }while (opcionMenu!=2);
    }
    public static void menuGatos(CatImage gato, ImageIcon fondoGato){
        String [] botones = {
                ConfigConstants.VER_GATOS,
                ConfigConstants.FAVORITOS,
                ConfigConstants.SALIR
        };

        String opcionElegida = (String) JOptionPane.showInputDialog(
                null,
                ConfigConstants.SELECCIONE,
                gato.getId(),
                JOptionPane.INFORMATION_MESSAGE,
                fondoGato,
                botones,
                botones[0]
        );

        switch (getOpcionMenu(botones,opcionElegida)){
            case 0:
                CatService.cargarGatos();
                break;
            case 1:
                CatService.favoritoGato(gato);
                CatService.cargarGatos();
                break;
            default:
                break;
        }
    }
    public static void menuFavoritos(Cat gatoFavorito, ImageIcon fondoGato){
        String [] botones = {
                ConfigConstants.VER_GATOS,
                ConfigConstants.ELIMINAR_FAVORITO,
                ConfigConstants.SALIR
        };

        String opcionElegida = (String) JOptionPane.showInputDialog(
                null,
                ConfigConstants.SELECCIONE,
                gatoFavorito.getImage_id(),
                JOptionPane.INFORMATION_MESSAGE,
                fondoGato,
                botones,
                botones[0]
        );

        switch (getOpcionMenu(botones,opcionElegida)){
            case 0:
                CatService.cargarGatosFavoritos();
                break;
            case 1:
                CatService.eliminarFavorito(gatoFavorito);
                CatService.cargarGatosFavoritos();
                break;
            default:
                break;
        }
    }
    private static int getOpcionMenu(String [] botones, String opcionElegida){
       return  Arrays.asList(botones).indexOf(opcionElegida);
    }
}

