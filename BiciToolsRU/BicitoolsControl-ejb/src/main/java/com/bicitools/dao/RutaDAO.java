 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao; 

import com.bicitools.common.MessagesBicitools;
import com.bicitools.entity.Alquileres;
import com.bicitools.entity.AlquileresProductos;
import com.bicitools.entity.LogsUsuario;
import com.bicitools.entity.Ruta;
import com.bicitools.entity.RutaPunto;
import com.bicitools.entity.UsuarioRuta;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pedro Pineda
 */
@Stateless
public class RutaDAO implements RutaDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
        EntityManager em;

    @Override
    public void creaRuta(String usuario, String nombre) {

        Ruta t = new Ruta();
        t.setNombre(nombre);
        t.setUsuario(usuario);

        em.persist(t);

    }

    @Override
    public void creaRutaPunto(String nombre_, float latitudOrigen_, float longitudOrigen_) {

        RutaPunto t = new RutaPunto();
        t.setNombre(nombre_);
        t.setLatitud(String.valueOf(latitudOrigen_));
        t.setLongitud(String.valueOf(longitudOrigen_));
        
            TypedQuery<Ruta> query = em.createNamedQuery(
      "Ruta.findAll", Ruta.class);
  List<Ruta> results = query.getResultList();
  for (Ruta result : results) {
      System.out.println("Nombre: " + result.getNombre() + ", Usuario: " + result.getUsuario());
  }
        em.persist(t);

    }

    @Override
    public void creaUsuarioARuta(String nombre, String usuario) {
        UsuarioRuta t = new UsuarioRuta();
        t.setNombre(nombre);
        t.setUsuario(usuario);
        em.persist(t);

    }
    
     @Override
    public List<Ruta> getRutaByUsuario(String usuario){
        Ruta r = new Ruta();
      
        Query query = em.createNamedQuery("Ruta.findByUsuario");
        query.setParameter("usuario", usuario);
        return  query.getResultList();
        
    }

    @Override
    public List<RutaPunto> getPuntosRuta(String ruta) {
     RutaPunto r = new RutaPunto();
      
        Query query = em.createNamedQuery("RutaPunto.findByRuta");
        query.setParameter("ruta", ruta);
        System.out.println("ruta" + ruta);
        return  query.getResultList();
           
    }

    @Override
    public List<UsuarioRuta> getUsuarioRuta(String ruta) {
        UsuarioRuta r = new UsuarioRuta();
      
        Query query = em.createNamedQuery("UsuarioRuta.findByRuta");
        query.setParameter("ruta", ruta);
        return  query.getResultList();  }

    @Override
    public void AgregarLogUsuario(String usuario_, Date fechaRegistro_, float latitud_, float longitud_, String rutaActual_, Date fechaInicioRecorrido_) {
        LogsUsuario t = new LogsUsuario( usuario_,  fechaRegistro_,  String.valueOf(latitud_),  String.valueOf(longitud_),  rutaActual_,  fechaInicioRecorrido_);
     
        em.persist(t);
    }

    @Override
    public List<AlquileresProductos> getAlquileresProductos() {
      AlquileresProductos r = new AlquileresProductos();
      
        Query query = em.createNamedQuery("AlquileresProductos.findAll");
        return  query.getResultList();
        
        
        
    }

    @Override
    public void alquilarProducto(String empresa_, String articulo_, String usuario_) {
      Alquileres t = new Alquileres(empresa_,  articulo_,  usuario_, new Date());
        em.persist(t);
     }
}
