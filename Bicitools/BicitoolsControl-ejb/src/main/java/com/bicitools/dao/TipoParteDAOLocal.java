/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.TipoParte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface TipoParteDAOLocal {

    public int obtenerIdTipoParte(String nombre);
    
}
