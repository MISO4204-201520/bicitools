/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jhony
 */
public class ValidaInfoReportesJson {
    
   
    
    public static boolean esFechaHoraValida(String hour){
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

    
    
}
