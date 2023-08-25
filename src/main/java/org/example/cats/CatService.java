package org.example.cats;

import okhttp3.*;
import org.example.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class CatService {
    public static Cat getJSONCat(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        try( Response response = HttpUtility.sendGetRequest(client, ConfigConstants.URL_CAT_API)){
            if(response.body() != null){
                String JSON = response.body().string();
                return JsonUtility.parseJson(JSON,Cat[].class)[0];
            }
        }catch (IOException e){
           LoggerUtility.error(Errors.HTTP_ERROR);
        }
        return new Cat();
    }

    public static Cat getGato(Cat gatos){
        return new Cat(gatos.getId(),
                gatos.getUrl(),
                gatos.getApiKEY(),
                gatos.getImage()
        );
    }

    public static ImageIcon getImageIcon(Cat gato){
        return ImageUtility.dowloadAndResizeImage(gato);
    }

    public static void menuGatos(){
        Cat gato = getGato(getJSONCat());
        ImageIcon fondoGato = getImageIcon(gato);
        String [] botones = {"ver otra imagen", "favoritos", "volver"};

        String opcionElegida = (String) JOptionPane.showInputDialog(
                null,
                ConfigConstants.SELECCIONE,
                gato.getId(),
                JOptionPane.INFORMATION_MESSAGE,
                fondoGato,
                botones,
                botones[0]
        );

        int opcionMenu = Arrays.asList(botones).indexOf(opcionElegida);

        switch (opcionMenu){
            case 0:
                menuGatos();
                break;
            case 1:
                favoritoGato(gato);
                break;
            default:
                break;
        }
    }

    private static void favoritoGato(Cat gatos) {
    }
}
