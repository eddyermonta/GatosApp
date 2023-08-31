package org.example.cats;

import okhttp3.*;
import org.example.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class CatService {
    public static  Cat getJSONCat(){
        try(Response response = HttpUtility.catRequestImage()){
            if(response.body()!=null){
                String JSON = response.body().string();
                LoggerUtility.trace("GATOS IMAGENES\n"+JSON);
                System.out.println("GATOS IMAGENES\n"+JSON);
                return JsonUtility.parseJson(JSON,Cat[].class)[0];
            }
        }catch (IOException e){
           LoggerUtility.error(Errors.HTTP_ERROR);
        }
        return new Cat();
    }

    public static Cat getGato(Cat gatos){
        return new Cat(gatos.getId(),
                gatos.getImage_id(),
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
            Cat gatoFavorito = new Cat(gatos.getId());
            String json = JsonUtility.Json(gatoFavorito);

            MediaType   jsonMediaType = MediaType.parse(ConfigConstants.APLICATION_JSON);
            RequestBody body = RequestBody.create(json,jsonMediaType);

            try (Response response  = HttpUtility.catRequestFavorite(body)){
                if (response.body() != null) {
                    String jsonResponse = response.body().string();
                    LoggerUtility.trace("GATOS FAVORITOS\n" + jsonResponse);
                    System.out.println("GATOS FAVORITOS\n" + jsonResponse);
                }
            } catch (IOException e) {
                LoggerUtility.error(Errors.HTTP_ERROR);
            }

    }
}
