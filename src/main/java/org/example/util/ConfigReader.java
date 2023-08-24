package org.example.util;

import org.example.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    /**
     * @param tipoEntorno desarrollo| prueba | producción
     * @param codigo Propiedad a obtener del archivo de configuracion
     * @return retorna codigo correspondiente al archivo de configuracion
     */
    public static String getCode(String tipoEntorno, String codigo) {
        Properties properties = new Properties();
        String value = null;
        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream(tipoEntorno);
            properties.load(input);
            value = properties.getProperty(codigo);
        }catch (IOException e){
            LoggerUtility.error(Errors.KEY_ERROR);
        }
        return value;
    }

}
