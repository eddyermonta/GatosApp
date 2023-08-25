package org.example.cats;

import okhttp3.*;
import org.example.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class CatService {
    public static void verGatos(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        try( Response response = HttpUtility.sendGetRequest(client, ConfigConstants.URL_CAT_API)){
            if(response.body() != null){
                String JSON = response.body().string();

                Cat[] gatosList = JsonUtility.parseJson(JSON,Cat[].class);
                Cat gato = new Cat(
                        gatosList[0].getId(),
                        gatosList[0].getUrl(),
                        gatosList[0].getApiKEY(),
                        gatosList[0].getImage()
                );
                ImageIcon fondoGato = ImageUtility.dowloadAndResizeImage(gato);
                //esta logica deberia ir a otro lado

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
                        verGatos();
                        break;
                    case 1:
                        favoritoGato(gato);
                        break;
                    default:
                        break;
                }

            }
        }catch (IOException e){
           LoggerUtility.error(Errors.HTTP_ERROR);
        }
    }

    private static void favoritoGato(Cat gatos) {
    }
}
