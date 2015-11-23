/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao; 

import com.bicitools.common.MessagesBicitools;
import com.bicitools.entity.Temporal;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ludwing
 */
@Stateless
public class TemporalDAO implements TemporalDAOLocal {

    @PersistenceContext(unitName="com.bicitools_unit")
    EntityManager em;
    
    
    
    @Override
    public void creaTemp(Integer pk, String dato){
        Temporal t= new Temporal();
        t.setColumnpk(pk);
        t.setEstring(MessagesBicitools.getMessage("mensaje1"));
        t.setEstring2(dato);
        t.setEdate(new Date());
        
        
        
       
        em.persist(t);
        
    }
    
}
