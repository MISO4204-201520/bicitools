/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.bussines;

/**
 *
 * @author Pedro
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
  public static void main(String[] args) {

	Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("../configRU.properties");

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
