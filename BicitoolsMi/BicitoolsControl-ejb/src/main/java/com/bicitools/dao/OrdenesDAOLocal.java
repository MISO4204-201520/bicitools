/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.InfoConfigMiBiciJson;
import com.bicitools.mjson.InfoOrdenJson;
import com.bicitools.mjson.InfoRolProveedorJson;
import com.bicitools.mjson.RespuestaJson;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface OrdenesDAOLocal {
    public RespuestaJson validarOrden(InfoConfigMiBiciJson info);
    
    public RespuestaJson obtenerMisOrdenes(InfoRolProveedorJson info);
    
    public RespuestaJson obtenerDetallesOrden(InfoOrdenJson info);
    
}
