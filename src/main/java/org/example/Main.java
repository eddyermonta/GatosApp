package org.example;

import org.example.cats.services.CatService;
import org.example.util.ConfigConstants;

import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
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

            opcionMenu = Arrays.asList(botones).indexOf(opcionElegida);

            switch (opcionMenu){
                case 0:
                    CatService.menuGatos();
                    break;
                case 1:
                    CatService.menuFavoritos();
                    break;
                default:
                    break;
            }

        }while (opcionMenu!=2);
    }
}