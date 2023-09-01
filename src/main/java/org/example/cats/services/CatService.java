package org.example.cats.services;

import okhttp3.*;
import org.example.cats.model.Cat;
import org.example.cats.model.CatImage;
import org.example.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class CatService {
    public static CatImage getJSONCat(){
        try(Response response = HttpUtility.catRequestImage()){
            if(response.body()!=null){
                String JSON = response.body().string();
                LoggerUtility.trace("GATOS IMAGENES\n"+JSON);
                System.out.println("GATOS IMAGENES\n"+JSON);
                return JsonUtility.parseJson(JSON, CatImage[].class)[0];
            }
        }catch (IOException e){
           LoggerUtility.error(Errors.HTTP_ERROR);
        }
        return new CatImage();
    }

    public static CatImage getGato(CatImage gatos){
        return new CatImage( gatos.getId(),gatos.getUrl());
    }

    public static ImageIcon getImageIcon(CatImage gato){
        return ImageUtility.dowloadAndResizeImage(gato);
    }
    public static ImageIcon getImageIcon(Cat gato){
        return ImageUtility.dowloadAndResizeImage(gato);
    }

    public static void menuGatos(){
        CatImage gato = getGato(getJSONCat());
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
                menuGatos();
                break;
            default:
                break;
        }
    }

    private static void favoritoGato(CatImage gatosImage) {
            Cat gatoFavorito = new Cat();
            gatoFavorito.setImage_id(gatosImage.getId());
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

    public static Cat getGatosFavoritos(String code) {
        try (Response response = HttpUtility.catRequestGetFavorites()){
            if (response.body()!=null){
                String json = response.body().string();
                System.out.println(json);
                Cat [] gatosFavoritos = JsonUtility.parseJson(json,Cat[].class);
                if(gatosFavoritos.length > 0){
                    int randomIndex = (new Random()).nextInt(gatosFavoritos.length);
                    return gatosFavoritos[randomIndex];

                }else{
                    System.out.println("No se encontraron gatos favoritos.");
                    throw new IOException();
                }

            }

        } catch (IOException e) {
            LoggerUtility.error("Error al obtener gatos favoritos: " + e.getMessage());
            return null;
        }
        return null;
    }

    public static void menuFavoritos(){
        Cat gatoFavorito = getGatosFavoritos(
                ConfigReader.getCode(ConfigConstants.CONFIG_PROPERTIES_DEV,
                ConfigConstants.API_KEY)
        );

        ImageIcon fondoGato = getImageIcon(gatoFavorito);
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

        int opcionMenu = Arrays.asList(botones).indexOf(opcionElegida);

        switch (opcionMenu){
            case 0:
                menuFavoritos();
                break;
            case 1:
                eliminarFavorito(gatoFavorito);
                menuFavoritos();
                break;
            default:
                break;
        }
    }

    private static void eliminarFavorito(Cat gato) {
        try( Response response = HttpUtility.catRequestDeleteFavorite(gato)) {
           if(response.code()==200){
               JOptionPane.showMessageDialog(null, "gato eliminado con id" + gato.getImage().getId());
           }
        } catch (IOException e) {
            LoggerUtility.error("el gato no se pudo borrar"+e.getMessage());
        }

    }
}
