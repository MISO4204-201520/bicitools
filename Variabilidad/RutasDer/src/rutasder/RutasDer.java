/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rutasder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Pedro
 */
public class RutasDer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String[] propiedades = args[0].split(",");

        int i = 0;

        File configFile = new File("../BicitoolsRU/BicitoolsServices-war/src/main/java/com/bicitools/pruebarest/configRU.properties");

        try {
            Properties props = new Properties();

            props.setProperty("Alquileres", "N");
            props.setProperty("DesplazamientoGrupal", "N");
            props.setProperty("Metricas", "N");

            for (i = 0; i < propiedades.length; i++) {

                if (propiedades[i].equals("Alquileres")) {
                    props.setProperty("Alquileres", "Y");
                }
                if (propiedades[i].equals("Grupal")) {
                    props.setProperty("DesplazamientoGrupal", "Y");
                }
                if (propiedades[i].equals("Metricas")) {
                    props.setProperty("Metricas", "Y");
                }
            }

            FileWriter writer = new FileWriter(configFile);

            props.store(writer, "host settings");

            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }

    }

}
