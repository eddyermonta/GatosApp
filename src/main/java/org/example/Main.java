package org.example;

import org.example.cats.CatService;
import org.example.util.ConfigConstants;
import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       //String apikey = ConfigReader.getCode(ConfigConstants.CONFIG_PROPERTIES_DEV,ConfigConstants.API_KEY);
       //System.out.println(apikey);

        int opcionMenu = -1;
        String [] botones = {ConfigConstants.VER_GATOS,ConfigConstants.SALIR};
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

            if (opcionMenu == 0){
                CatService.verGatos();
            }

        }while (opcionMenu!=1);
    }
}