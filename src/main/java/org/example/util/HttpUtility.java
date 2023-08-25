package org.example.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtility {

    public static Response sendGetRequest(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return client.newCall(request).execute();
    }
    public static HttpsURLConnection getHttpConnection(URL imageUrl) throws IOException {
        HttpsURLConnection httpcon = (HttpsURLConnection) imageUrl.openConnection();
        httpcon.addRequestProperty("User-Agent","No User-Agent provided");
        return httpcon;
    }
}

