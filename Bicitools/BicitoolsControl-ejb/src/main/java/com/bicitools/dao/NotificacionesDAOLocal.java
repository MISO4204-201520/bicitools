/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.InfoRepoCantNotificaJson;
import com.bicitools.mjson.RespuestaJson;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface NotificacionesDAOLocal {

    public RespuestaJson registrarNotificacion(String usuario, String tipo);
    
    public RespuestaJson obtenerNotoficacionesUsuario(InfoRepoCantNotificaJson info);
}
