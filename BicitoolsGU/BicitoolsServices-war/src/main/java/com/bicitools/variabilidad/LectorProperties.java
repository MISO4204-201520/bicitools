/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.variabilidad;

import java.util.ResourceBundle;

/**
 *
 * @author Taidy
 */
public class LectorProperties {
    
    public static boolean getMensajeFeatureProperties(String name){
        ResourceBundle rb = ResourceBundle.getBundle("prop.features");
        return Boolean.parseBoolean((String)rb.getObject(name));
    }
}
