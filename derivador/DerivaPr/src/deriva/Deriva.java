/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deriva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author Ludwing
 */
public class Deriva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deriva d = new Deriva();
        d.leeArchivo();
       
    }

    public String escribeCompila(String nom) {
        String res = "\n";
        res = res.concat("cd..\n");
        res = res.concat("cd " + nom + "\n");
        res = res.concat("start mvn clean install\n");
        return res;
    }
    
    public String escribeCopia(String nom) {
        String res = "\n";
        res = res.concat("cd..\n");
        res = res.concat("cd " + nom + "\n");
        res = res.concat("copy BicitoolsManager-ear\\target\\*.ear ..\\derivador\\pfinal\\");
        res = res.concat("\n");
        return res;
    }

    public void escribeArchivo(String nombre, String datos) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        FileWriter fichero2 = null;
        PrintWriter pw2 = null;
        try {
            fichero = new FileWriter(nombre+".bat");
            pw = new PrintWriter(fichero);
            
            pw.println(datos);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
         // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
    
    public HashMap creaHash (){
        HashMap h = new HashMap();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String compilacion="";
        String inicio, fin;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("mapa.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                inicio= linea.substring(0, linea.indexOf(","));
                fin=linea.substring(linea.indexOf(",")+1);
                h.put(inicio, fin);
               
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return h;
        
    }
    public void leeArchivo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String compilacion="";
        String copiar="";
        HashMap h = creaHash();
        

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("entrada.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                if(h.containsKey(linea)){
                    compilacion=compilacion.concat(escribeCompila((String)h.get(linea)));
                    copiar=copiar.concat(escribeCopia((String)h.get(linea)));
                }
            }
            escribeArchivo("compilacion",compilacion);
            escribeArchivo("copiar",copiar);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
