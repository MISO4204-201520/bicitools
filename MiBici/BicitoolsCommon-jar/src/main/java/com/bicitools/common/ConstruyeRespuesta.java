/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.common;

import com.bicitools.mjson.RespuestaJson;

/**
 *
 * @author Ludwing
 */
public abstract class ConstruyeRespuesta {
    
    public static RespuestaJson construyeRespuestaOk(){
        RespuestaJson res= new RespuestaJson();
        res.setCodigo(0);
        res.setValor("INFO");
        res.setDescripcion("OK");
        return res;
    }
    
    public static RespuestaJson construyeRespuestaFalla(String mensaje){
        RespuestaJson res= new RespuestaJson();
        res.setCodigo(101);
        res.setValor("ERROR");
        res.setDescripcion(mensaje);
        return res;
    }
}
