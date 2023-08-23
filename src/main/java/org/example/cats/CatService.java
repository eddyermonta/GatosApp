package org.example.cats;


import okhttp3.*;
import org.example.util.ConfigConstants;
import org.example.util.HttpUtility;
import org.example.util.ImageUtility;
import org.example.util.JsonUtility;
import javax.swing.*;
import java.io.IOException;

public class CatService {
    public static void verGatos(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        try( Response response = HttpUtility.sendGetRequest(client, ConfigConstants.URL_CAT_API)){
            if(response != null && response.body() != null){
                String JSON = response.body().string();
                Cat gatos  = JsonUtility.parseJson(JSON,Cat.class);
                ImageIcon fondoGato = ImageUtility.dowloadAndResizeImage(gatos);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
