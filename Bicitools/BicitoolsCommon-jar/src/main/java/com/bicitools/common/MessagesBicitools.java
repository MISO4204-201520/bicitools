/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.ejb.Stateless;

/**
 *
 * @author Ludwing
 */
@Stateless
public abstract class MessagesBicitools {

    public static String getMessage(String name) {
        ResourceBundle rb = ResourceBundle.getBundle("prop.general");
        return (String) rb.getObject(name);

    }

    public static Enumeration  getCorreoProperties() throws FileNotFoundException {
         ResourceBundle rb = ResourceBundle.getBundle("prop.correo");
         return rb.getKeys();
        
    }
    
    public static String getCorreo(String name) {
        ResourceBundle rb = ResourceBundle.getBundle("prop.correo");
        return (String) rb.getObject(name);

    }
    

}
