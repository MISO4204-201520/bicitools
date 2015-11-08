/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.Partes;
import com.bicitools.mjson.InfoInsertaProductoJson;
import com.bicitools.mjson.InfoConsultaPartesJson;
import com.bicitools.mjson.RespuestaJson;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface PartesDAOLocal {

    RespuestaJson crearParte(InfoInsertaProductoJson data);
    
    public RespuestaJson obtenerPartesPorTipo (InfoConsultaPartesJson info);
    
    public Partes obtenerInfoParte(int idParte);
    
}
