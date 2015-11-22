/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.Usuario;
import com.bicitools.entity.Vendedor;
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
public class VendedorDAO implements VendedorDAOLocal{
 @PersistenceContext(unitName="com.bicitools_unit")
    EntityManager em;

    @Override
    public String registrarPerfilVendedor(Integer id_usuario, String nombre_establecimiento, String direccion_establecimiento, String telefono_establecimiento, String celular_establecimiento, String correo_establecimiento, String nit_establecimiento, String foto_establecimiento) {
        String respuesta="";
    try{
    Vendedor v= new Vendedor();     
    v.setNombreEstablecimiento(nombre_establecimiento);
    
    v.setDireccionEstablecimiento(direccion_establecimiento);
    v.setTelefonoEstablecimiento(telefono_establecimiento);
    v.setCelularEstablecimiento(celular_establecimiento);
    v.setCorreoEstablecimiento(correo_establecimiento);
    v.setNitEstablecimiento(nit_establecimiento);
    v.setFotoEstablecimiento(foto_establecimiento);
    v.setIdUsuario(id_usuario);
            em.persist(v);
            em.flush();    
        respuesta="ok";
    }catch(Exception e){
    respuesta="error";
    }
    return respuesta;
    }

    @Override
    public String actualizarPerfilVendedor(Integer id_usuario, String nombre_establecimiento, String direccion_establecimiento, String telefono_establecimiento, String celular_establecimiento, String correo_establecimiento, String nit_establecimiento, String foto_establecimiento) {
        String res="";
        Vendedor vendedor = new Vendedor();
        
        Query query = em.createNamedQuery("Vendedor.findByIdUsuario");
        query.setParameter("idUsuario", id_usuario);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        
            vendedor=(Vendedor)dlist.get(0);
            em.refresh(vendedor);
            
    vendedor.setNombreEstablecimiento(nombre_establecimiento);
    
    vendedor.setDireccionEstablecimiento(direccion_establecimiento);
    vendedor.setTelefonoEstablecimiento(telefono_establecimiento);
    vendedor.setCelularEstablecimiento(celular_establecimiento);
    vendedor.setCorreoEstablecimiento(correo_establecimiento);
    vendedor.setNitEstablecimiento(nit_establecimiento);
    vendedor.setFotoEstablecimiento(foto_establecimiento);
            em.persist(vendedor);
            
            res="ok";
            
        }catch(Exception e){
        res="vacio";
        }    
        
        }else{
        
        res="vacio";
        }

        
        
        return res;
    }

    @Override
    public Vendedor obtenerDetallesUsuario(Integer id_usuario) {
        String res="";
    Vendedor v= new Vendedor();
            
        
        Query query = em.createNamedQuery("Vendedor.findByIdUsuario");
        query.setParameter("idUsuario", id_usuario);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        v=(Vendedor)dlist.get(0);
            
            
        }catch(Exception e){
        v=null;
        }    
        
        }else{
        
        v=null;
        }
return v;
    }

    
}
