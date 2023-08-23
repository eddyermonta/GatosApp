package org.example.util;

import com.google.gson.Gson;
public class JsonUtility {
        public static <T> T parseJson(String jsonString, Class<T> clazz) {
            return new Gson().fromJson(jsonString, clazz);
        }
    }

