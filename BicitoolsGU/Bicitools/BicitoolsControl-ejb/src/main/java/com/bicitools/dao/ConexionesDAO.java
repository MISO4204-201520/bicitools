/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.Conexiones;
import com.bicitools.entity.Vendedor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TaidyJohana
 */

@Stateless
public class ConexionesDAO implements ConexionesDAOLocal{
 @PersistenceContext(unitName="com.bicitools_unit")
    EntityManager em;

    @Override
    public String setConexion(Integer id_usuario, Date fechaConexion,int tipoConexion) {
        String respuesta="";
    try{
        Conexiones conexiones = new Conexiones();
    conexiones.setIdUsuario(id_usuario);
    conexiones.setFechaConexion(fechaConexion);
    conexiones.setTipoConexion(tipoConexion);
            em.persist(conexiones);
            em.flush();    
        respuesta="ok";
    }catch(Exception e){
    respuesta="error";
    }
    return respuesta;
    }

    @Override
    public ArrayList<Conexiones> getConexion(Integer id_usuario) {
        ArrayList<Conexiones> conexiones= new ArrayList<Conexiones>();
        
        Query query = em.createNamedQuery("Conexiones.findByIdUsuario");
        query.setParameter("idUsuario", id_usuario);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        
            for(int i=0; i<dlist.size();i++){
                
               
            Conexiones conexiones1= new Conexiones();
            conexiones1= (Conexiones)dlist.get(i);
            conexiones.add(conexiones1);
            
            }
            
        }catch(Exception e){
            
        }
        
        }
        return conexiones;
    }

    @Override
    public String cerrarSesion(Integer id_usuario, Date fechaConexion, int tipoConexion) {
         String respuesta="";
    try{
        Conexiones conexiones = new Conexiones();
    conexiones.setIdUsuario(id_usuario);
    conexiones.setFechaConexion(fechaConexion);
    conexiones.setTipoConexion(tipoConexion);
            em.persist(conexiones);
            em.flush();    
        respuesta="ok";
    }catch(Exception e){
    respuesta="error";
    }
    return respuesta;
    }
    
}
