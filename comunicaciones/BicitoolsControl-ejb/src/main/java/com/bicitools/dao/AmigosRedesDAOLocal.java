/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.IdEntidadJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.UsuarioUsuarioJson;
import javax.ejb.Local;

/**
 *
 * @author Ludwing
 */
@Local
public interface AmigosRedesDAOLocal {

    public RespuestaJson agregaAmigo(UsuarioUsuarioJson info);

    public RespuestaJson actualizarAmigo(UsuarioUsuarioJson info, int i);

    public RespuestaJson actualizarIdEntidad(IdEntidadJson info, int i);
    
}
