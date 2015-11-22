/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.PartesGenericas;
import java.util.ArrayList;
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
public class ParteGenericaDAO implements ParteGenericaDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int obtenerValorParte(int idTipo) {

        int res = -1;
        Query query = em.createNamedQuery("PartesGenericas.findByIdTipo");

        int idTipoParte = idTipo;
        query.setParameter("idTipo", idTipoParte);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            PartesGenericas miParte = (PartesGenericas) qresul.get(0);
            try {
                String valorString = miParte.getValor();
                valorString = valorString.replace(".", "");
                valorString = valorString.replace(",", "");
                res = Integer.parseInt(valorString);
            } catch (Exception ex) {
                System.out.println("error en parte generica {" + idTipo + "} " + ex.getMessage());
            }
        }
        return res;
    }
    
    @Override
    public PartesGenericas obtenerInfoParte(int idTipo) {

        PartesGenericas res = null;
        Query query = em.createNamedQuery("PartesGenericas.findByIdTipo");

        int idTipoParte = idTipo;
        query.setParameter("idTipo", idTipoParte);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            PartesGenericas miParte = (PartesGenericas) qresul.get(0);
            res = miParte;
        }
        return res;
    }
}
