/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mibici.general.InfoInsertaProductoJson;
import com.bicitools.mjson.RespuestaJson;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface PartesDAOLocal {

    RespuestaJson crearParte(InfoInsertaProductoJson data);
    
}