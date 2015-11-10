package com.bicitools.bicitoolsredsocial;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;

/**
 *
 * @author Ludwing
 */
public abstract class ConstruyeRespuestaRedSocial {
    
    public static RespuestaJsonRedSocial construyeRespuestaOk(int codigo, String valor, String descripcion){
        RespuestaJsonRedSocial res= new RespuestaJsonRedSocial();
        res.setCodigo(codigo);
        res.setValor(valor);
        res.setDescripcion(descripcion);
        return res;
    }
    
    public static RespuestaJsonRedSocial construyeRespuestaConDatos(int codigo, String valor, String descripcion,ArrayList datos){
        RespuestaJsonRedSocial res= new RespuestaJsonRedSocial();
        res.setCodigo(codigo);
        res.setValor(valor);
        res.setDescripcion(descripcion);
        res.setDatos(datos);
        return res;
    }
    
    public static RespuestaJsonRedSocial construyeRespuestaFalla(String mensaje,ArrayList datos){
        RespuestaJsonRedSocial res= new RespuestaJsonRedSocial();
        res.setCodigo(101);
        res.setValor("ERROR");
        res.setDescripcion(mensaje);
        res.setDatos(datos);
        return res;
    }
}
