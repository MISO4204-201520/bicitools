/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.InfoDomiciliarioJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.UsuarioJson;
import javax.ejb.Local;


/**
 *
 * @author Ludwing
 */
@Local
public interface DomiciliarioDAOLocal {
    
    public RespuestaJson creaDomiciliario(InfoDomiciliarioJson info);
    public RespuestaJson actualizaDomiciliario(InfoDomiciliarioJson info);
    public RespuestaJson detalleDomiciliario(UsuarioJson info);
    
}
