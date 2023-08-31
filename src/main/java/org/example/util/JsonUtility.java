package org.example.util;

import com.google.gson.Gson;
public class JsonUtility {
    static Gson gson = new Gson();
        public static <T> T parseJson(String jsonString, Class<T> clazz) {
            return gson.fromJson(jsonString, clazz);
        }
        public static String Json(Object object){
            return gson.toJson(object);
        }
    }

