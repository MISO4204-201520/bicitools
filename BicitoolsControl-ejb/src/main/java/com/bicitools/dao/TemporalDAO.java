/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.MessagesBicitools;
import com.bicitools.entity.Persona;
import com.bicitools.entity.Temporal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ludwing
 */
@Stateless
public class TemporalDAO implements TemporalDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public int creaTemp(String dato) {
        int res = -1;
        EntityManagerFactory conn = Persistence.createEntityManagerFactory("com.bicitools_unit");
        this.emf = conn;
        Temporal t = new Temporal();
        t.setColumnpk(1);
        t.setEstring(MessagesBicitools.getMessage("mensaje1"));
        t.setEstring2(dato);
        t.setEdate(new Date());

        Persona tmp = new Persona();
        tmp.setId(1);
        tmp.setName(dato);

        EntityManager em = null;
        try {

            em = getEntityManager();
            //em.getTransaction().begin();
            em.persist(t);

            res = t.getColumnpk();
            

        } catch (Exception ex) {
            System.out.println("error creando persona ex: " + ex.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
        }

        //em.getTransaction().begin();
        //em.persist(t);
        //em.getTransaction().commit();
        PersonaJpaController person = new PersonaJpaController(conn);
        try {
            person.create(tmp);
        } catch (Exception ex) {
            Logger.getLogger(TemporalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            person.create(tmp);
        } catch (Exception e) {
            System.out.println("error en: " + e.getMessage());
        }
        return res;
    }

}
