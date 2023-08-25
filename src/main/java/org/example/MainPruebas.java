package org.example;

import com.google.gson.Gson;
import org.example.cats.Cat;

public class MainPruebas {
    public static void main(String[] args) {
        StringBuilder builders = new StringBuilder();
        builders.append("[");
        builders.append("{");
        builders.append("\"id\": \"MTg5NTY1Nw\",");
        builders.append("\"url\": \"https://cdn2.thecatapi.com/images/MTg5NTY1Nw.gif\",");
        builders.append(" \"width\": 250,");
        builders.append("\"height\": 250");
        builders.append("}");
        builders.append("]");

        Cat[] catsArray = new Gson().fromJson(builders.toString(),Cat[].class);
        for (Cat cat: catsArray) {
            System.out.println(cat.getId()); //funciona, el int id puede dar lio
        }
    }
}
