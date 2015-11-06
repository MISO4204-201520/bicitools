/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.RutaPunto;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.RutasUsuarioJson;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface RutasDAOLocal {

    public RespuestaJson obtenerRutasUsuario(String usuario);
    public RespuestaJson obtenerRutasUsuarioFechas(String usuario,String fechaIni,String fechaFin);
    public RespuestaJson obtenerRecorridosUsuarioFechas(String usuario,String fechaIni,String fechaFin);

    public RespuestaJson crearRutasUsuario(RutasUsuarioJson info);

    public ArrayList obtenerPuntosRutaUsuario(String nombreRuta);
            
    public RespuestaJson crearRecorridoUsuario(RutasUsuarioJson info);
    
    public RespuestaJson obtenerMetricasUsuario(String usuario,String fechaIni,String fechaFin);
    
    public RespuestaJson exportarReporteMetricasUsuario(String usuario, String fechaIni, String fechaFin,String archivo);
    
    public RutaPunto obtenerPuntoRutaIndiceUsuario(String nombreRuta,int indice);
    
    public RespuestaJson exportarRutasUsuario(String usuario, String fechaIni, String fechaFin,String archivo);
    
    public RespuestaJson exportarRecorridosUsuario(String usuario, String fechaIni, String fechaFin,String archivo);
    
    public RespuestaJson obtenerRecorridosRuta(String usuario, String fechaIni, String fechaFin);
    
    public RespuestaJson exportarRecorridosRuta(String usuario, String fechaIni, String fechaFin, String archivo);
}
