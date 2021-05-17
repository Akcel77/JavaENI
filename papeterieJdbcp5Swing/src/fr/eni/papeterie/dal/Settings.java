package fr.eni.papeterie.dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Properties properties;


    /**
     * Bloc D'init qui permet l'instantiation une seule fois
     * Permet de charger un ficher externe "settings.properties"
     */
    static {
        properties = new Properties();
        try{
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @param key de notre fichier "settings.properties"
     * @return la values de la key
     */
    public static String getProperties(String key) {
        return properties.getProperty(key, null);
    }
}
