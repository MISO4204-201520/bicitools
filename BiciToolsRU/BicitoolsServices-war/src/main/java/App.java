/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro
 */

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class App {
    public Properties prop = new Properties();
    
    
   public static App getInstance(){
       if (instance == null) {
           instance=new App();
       }
       return instance;
   } 

   
     static App instance;
  private  App() {

	
	InputStream input = null;

	try {

		input = new FileInputStream("configRU.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		System.out.println(prop.getProperty("DesplazamientoGrupal"));
		System.out.println(prop.getProperty("Alquileres"));

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

  }
}
