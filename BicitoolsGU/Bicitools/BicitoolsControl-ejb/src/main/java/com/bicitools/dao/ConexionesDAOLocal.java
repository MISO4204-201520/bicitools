/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.Conexiones;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author TaidyJohana
 */
@Local
public interface ConexionesDAOLocal {

    public String setConexion(Integer id_usuario, Date fechaConexion, int tipoConexion);
    public String cerrarSesion(Integer id_usuario, Date fechaConexion, int tipoConexion);
    
    public ArrayList<Conexiones> getConexion(Integer id_usuario);
}
