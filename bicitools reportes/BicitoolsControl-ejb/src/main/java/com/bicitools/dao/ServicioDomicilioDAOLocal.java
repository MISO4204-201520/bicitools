/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.CalificacionUsuarioServicioJson;
import com.bicitools.mjson.DatosEntradaServicioJson;
import com.bicitools.mjson.DatosSalidaServicioJson;
import com.bicitools.mjson.InfoEntregaJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.ServicioJson;
import javax.ejb.Local;

/**
 *
 * @author Ludwing
 */
@Local
public interface ServicioDomicilioDAOLocal {
     public RespuestaJson creaServicio(InfoEntregaJson info);
     public RespuestaJson enviaSolicitud(ServicioJson info);
     public RespuestaJson aceptaServicio(DatosEntradaServicioJson info);
     public RespuestaJson cancelaServicio(ServicioJson info);
     public RespuestaJson registraServicio(DatosSalidaServicioJson info);
     public RespuestaJson calificaServicio(CalificacionUsuarioServicioJson info);
    
}
