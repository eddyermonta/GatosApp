package org.example;

import org.example.util.ConfigConstants;
import org.example.util.ConfigReader;

public class Main {
    public static void main(String[] args) {
       String apikey = ConfigReader.getCode(ConfigConstants.CONFIG_PROPERTIES_DEV,ConfigConstants.API_KEY);
        System.out.println(apikey);
    }
}