/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.AlquileresProductos;
import com.bicitools.entity.Ruta;
import com.bicitools.entity.RutaPunto;
import com.bicitools.entity.UsuarioRuta;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ludwing
 */
@Local
public interface RutaDAOLocal {
    public void creaRuta(String usuario, String nombre);

    public void creaRutaPunto(String string, float latitudOrigen, float longitudOrigen);

    public void creaUsuarioARuta(String string, String usuario);

    public List<Ruta> getRutaByUsuario(String usuario);
    
    public List<RutaPunto> getPuntosRuta(String usuario);
    
    public List<UsuarioRuta> getUsuarioRuta(String Ruta);
     
    public void AgregarLogUsuario(String usuario_, Date fechaRegistro_, float latitud_, float longitud_, String rutaActual_, Date fechaInicioRecorrido_);

   public List<AlquileresProductos> getAlquileresProductos();
  
    public void  alquilarProducto( String empresa_ , String articulo_, String usuario_);
      
}
