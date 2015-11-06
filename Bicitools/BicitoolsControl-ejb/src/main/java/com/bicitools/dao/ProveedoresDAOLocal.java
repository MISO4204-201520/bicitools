/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.InfoInsertaProvJson;
import com.bicitools.mjson.InfoRolProveedorJson;
import com.bicitools.mjson.RespuestaJson;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface ProveedoresDAOLocal {
    
    public int obtenerIdProveedor(String usuario);

    public RespuestaJson crearProveedor(InfoInsertaProvJson info);
    
    public RespuestaJson verificarProveedor(InfoRolProveedorJson info) ;
    
}
