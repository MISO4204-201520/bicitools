/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author jhony
 */
public class Reportes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //String[] data = new String[3];
        //data[0]="historialdeviajes,reportes,mas cosas,features";
        System.out.println("salida");
        File dir = new File("../../bicitoolsRE/BicitoolsCommon-jar/src/main/resources/prop/");
        dir.mkdirs();
        File tmp = new File(dir, "general.properties");
        tmp.createNewFile();

        String content = "#This is the text content";

        try (FileOutputStream fop = new FileOutputStream(tmp)) {

            // if file doesnt exists, then create it
            if (!tmp.exists()) {
                tmp.createNewFile();
            }

            for (int i = 0; i < args.length; i++) {
                // get the content in bytes
                if (args[i] != null) {

                    content = args[i];
                    String[] features = content.split(",");
                    for (String feature : features) {
                        if (feature.equals("historialdeviajes") || feature.equals("reportes")) {
                            feature += " = true";
                            byte[] contentInBytes = feature.getBytes();

                            fop.write(contentInBytes);
                            fop.write('\n');
                            fop.flush();

                            System.out.println("Done");
                        }

                    }

                }

            }
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
