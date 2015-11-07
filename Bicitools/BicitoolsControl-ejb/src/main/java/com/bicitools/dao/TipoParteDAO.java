/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.TipoParte;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhony
 */
@Stateless
public class TipoParteDAO implements TipoParteDAOLocal {
    @PersistenceContext(unitName = "com.bicitools_unit")
    private EntityManager em;
    
    @Override
    public int obtenerIdTipoParte(String nombre)
    {
        int idRes;
        Query query = em.createNamedQuery("TipoParte.findByNombre");
        query.setParameter("nombre", nombre);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            TipoParte tipoParte = (TipoParte) qresul.get(0);
            idRes = tipoParte.getIdTipoParte();
            
        }
        else
            idRes = -1;
            
        return idRes;
    }

    
}
