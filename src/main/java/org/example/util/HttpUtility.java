package org.example.util;

import okhttp3.*;
import org.example.cats.model.Cat;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class HttpUtility {
    static OkHttpClient client = new OkHttpClient().newBuilder().build();
    /**
     * @return JSON OF RANDOM CATS
     */
    public static Response RequestGetCatImage() throws IOException {
        Request request = new Request.Builder()
                .url(ConfigConstants.URL_CAT_IMAGE)
                .get()
                .build();
        return client.newCall(request).execute();
    }
    /**
     * @param imageUrl URL OF CAT IMAGE
     * @return DOWNLOAD CAT IMAGE
     */
    public static HttpsURLConnection getHttpCatImage(URL imageUrl) throws IOException {
        HttpsURLConnection httpcon = (HttpsURLConnection) imageUrl.openConnection();
        httpcon.addRequestProperty("User-Agent","No User-Agent provided");
        return httpcon;
    }
    /**
     *
     * @param body JSON BODY WITH CAT ID
     * @return HTTP POST OF FAVORITE CAT
     */
    public static  Response RequestPostFavoriteCat(RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(ConfigConstants.URL_CAT_FAVORITES)
                .post(body)
                .addHeader(ConfigConstants.CONTENT_TYPE, ConfigConstants.APLICATION_JSON)
                .addHeader(ConfigConstants.X_API_KEY,
                        ConfigReader.getCode(
                                ConfigConstants.CONFIG_PROPERTIES_DEV,
                                ConfigConstants.API_KEY))
                .build();
        return client.newCall(request).execute();
    }
    /**
     *
     * @return JSON WITH A LIST OF FAVORITES CATS
     */
    public static Response RequestGetFavoritesCats() throws IOException {
        Request request = new Request.Builder()
                .url(ConfigConstants.URL_CAT_FAVORITES)
                .get()
                .addHeader(ConfigConstants.CONTENT_TYPE, ConfigConstants.APLICATION_JSON)
                .addHeader(ConfigConstants.X_API_KEY,
                        ConfigReader.getCode(
                                ConfigConstants.CONFIG_PROPERTIES_DEV,
                                ConfigConstants.API_KEY))
                .build();
        return client.newCall(request).execute();
    }
    /**
     *
     * @param cat CAT ID THAT YOU WISH DELETE OF FAVORITES CAT
     * @return JSON WITH THE CONFIRMATION IF THE CAT WAS DELETED
     */
    public static  Response RequestDeleteFavoriteCat(Cat cat) throws IOException {
        Request request = new Request.Builder()
                .url(ConfigConstants.URL_CAT_FAVORITES+"/"+ cat.getId())
                .delete()
                .addHeader(ConfigConstants.CONTENT_TYPE, ConfigConstants.APLICATION_JSON)
                .addHeader(ConfigConstants.X_API_KEY,
                        ConfigReader.getCode(
                                ConfigConstants.CONFIG_PROPERTIES_DEV,
                                ConfigConstants.API_KEY))
                .build();
        return client.newCall(request).execute();
    }

}

