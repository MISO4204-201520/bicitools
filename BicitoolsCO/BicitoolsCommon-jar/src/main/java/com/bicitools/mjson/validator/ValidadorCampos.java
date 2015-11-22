/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ludwing
 */
public class ValidadorCampos {
    
    public static boolean esHoraValida(String hour){
        String validString = hour;
        boolean res= false;
        Pattern pat = Pattern.compile("[0-2][0-9]:[0-5][0-9]");
        Matcher mat = pat.matcher(validString);
        
        if (mat.matches()) {
            String [] cadenas = validString.split("[:]");
            if (Integer.parseInt(cadenas[0])<24)
                if(Integer.parseInt(cadenas[0])<60)
                    res=true;
        }
        return res;
    }

    public static boolean esDiaValido(String day) {
        String validDay = day;
        boolean res= false;
        Pattern pat = Pattern.compile("^[1-7](\\,[1-7])*");
        Matcher mat = pat.matcher(validDay);
        if (mat.matches()) 
            res=true;
        return res;
    }
    
    public static boolean esEmailValido(String email){   
        boolean res= false;
        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(email);
        if (mat.matches()) 
            res=true;
        return res;
    }
    
    public static boolean esCelularValido (String movil){
        boolean res= false;
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(movil);
        if (mat.matches()) 
            res=true;
        return res;
        
    }
    
    public static boolean esPuntosValido(int score){
        boolean res= false;
        if(score>=0 && score <=10)
            res=true;
        return res;
    }
    
}
