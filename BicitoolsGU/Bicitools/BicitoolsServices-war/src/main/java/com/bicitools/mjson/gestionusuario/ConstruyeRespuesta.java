/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.gestionusuario;

import com.bicitools.mjson.gestionusuario.RespuestaJson;
import java.util.ArrayList;

/**
 *
 * @author Ludwing
 */
public abstract class ConstruyeRespuesta {
    
    public static RespuestaJson construyeRespuestaOk(int codigo, String valor, String descripcion){
        RespuestaJson res= new RespuestaJson();
        res.setCodigo(codigo);
        res.setValor(valor);
        res.setDescripcion(descripcion);
        return res;
    }
    
    public static RespuestaJson construyeRespuestaConDatos(int codigo, String valor, String descripcion,ArrayList datos){
        RespuestaJson res= new RespuestaJson();
        res.setCodigo(codigo);
        res.setValor(valor);
        res.setDescripcion(descripcion);
        res.setDatos(datos);
        return res;
    }
    
    public static RespuestaJson construyeRespuestaFalla(String mensaje,ArrayList datos){
        RespuestaJson res= new RespuestaJson();
        res.setCodigo(101);
        res.setValor("ERROR");
        res.setDescripcion(mensaje);
        res.setDatos(datos);
        return res;
    }
}
