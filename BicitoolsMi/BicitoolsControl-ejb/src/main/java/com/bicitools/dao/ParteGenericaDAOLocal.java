/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.PartesGenericas;
import javax.ejb.Local;

/**
 *
 * @author jhony
 */
@Local
public interface ParteGenericaDAOLocal {
    public int obtenerValorParte(int idTipo);
    public PartesGenericas obtenerInfoParte(int idTipo);
}
