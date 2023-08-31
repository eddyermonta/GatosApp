package org.example.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.example.cats.Cat;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class HttpUtility {
    static OkHttpClient client = new OkHttpClient().newBuilder().build();
    public static Response catRequestImage() throws IOException {
        Request request = new Request.Builder()
                .url(ConfigConstants.URL_CAT_IMAGE)
                .get()
                .build();
        return client.newCall(request).execute();
    }

    public static  Response catRequestFavorite(RequestBody body) throws IOException {
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

    public static HttpsURLConnection getHttpConnection(URL imageUrl) throws IOException {
        HttpsURLConnection httpcon = (HttpsURLConnection) imageUrl.openConnection();
        httpcon.addRequestProperty("User-Agent","No User-Agent provided");
        return httpcon;
    }

}

