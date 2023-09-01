package org.example.cats.controller;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.example.cats.model.Cat;
import org.example.cats.model.CatImage;
import org.example.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class CatController {
    public static CatImage getRandomCat(){
        try(Response response = HttpUtility.RequestGetCatImage()){
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
    public static void pushFavoriteCat(CatImage gatosImage) {
        Cat gatoFavorito = new Cat();
        gatoFavorito.setImage_id(gatosImage.getId());
        String json = JsonUtility.Json(gatoFavorito);

        MediaType jsonMediaType = MediaType.parse(ConfigConstants.APLICATION_JSON);
        RequestBody body = RequestBody.create(json,jsonMediaType);

        try (Response response  = HttpUtility.RequestPostFavoriteCat(body)){
            if (response.body() != null) {
                String jsonResponse = response.body().string();
                LoggerUtility.trace("GATOS FAVORITOS\n" + jsonResponse);
                System.out.println("GATOS FAVORITOS\n" + jsonResponse);
            }
        } catch (IOException e) {
            LoggerUtility.error(Errors.HTTP_ERROR);
        }
    }
    public static Cat getFavoriteCat(String code) {
        try (Response response = HttpUtility.RequestGetFavoritesCats()){
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
    public static void deleteFavoriteCat(Cat gato) {
        try( Response response = HttpUtility.RequestDeleteFavoriteCat(gato)) {
            if(response.code()==200){
                JOptionPane.showMessageDialog(null, "gato eliminado con id" + gato.getImage().getId());
            }else System.out.println(response.code());
        } catch (IOException e) {
            LoggerUtility.error("el gato no se pudo borrar"+e.getMessage());
        }
    }
}
