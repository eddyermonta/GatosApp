package org.example.util;

import com.google.gson.Gson;
public class JsonUtility {
    static Gson gson = new Gson();
    /**
     *
     * @param jsonString json body
     * @param clazz object
     * @return JSON to object
     * @param <T> object
     */
        public static <T> T parseJson(String jsonString, Class<T> clazz) {
            return gson.fromJson(jsonString, clazz);
        }
    /**
     *
     * @param object to tranform a JSON
     * @return JSON BODY
     */
    public static String Json(Object object){
            return gson.toJson(object);
        }
    }

