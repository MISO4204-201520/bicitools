/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;
import com.bicitools.entity.Conexiones;
import com.bicitools.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author TaidyJohana
 */
@Local
public interface UsuarioDAOLocal {
     public int  registrarUsuario(Integer numeroIdentificacion,
             Integer tipoIdentificacion,Integer tipoPerfil, Integer genero,
             String nombres, String apellidos,String foto,String correo,String fechaNacimiento ,
             String direccionCasa, String direccionTrabajo,String telefonoFijo,String telefonoMovil,
             String facebookUser, String twitterUser,String usuario,String contrasenia
        );
     
     public int registrarUsuarioFacebook(
     Integer numeroIdentificacion,
             Integer tipoIdentificacion,Integer tipoPerfil, Integer genero,
             String nombres, String apellidos,String foto,String correo,String fechaNacimiento ,
             String direccionCasa, String direccionTrabajo,String telefonoFijo,String telefonoMovil,
             String facebookUser, String facebookToken,String twitterUser,String usuario,String contrasenia
     );
     
     
     public int registrarUsuarioTwitter(
     
             Integer numeroIdentificacion,
             Integer tipoIdentificacion,Integer tipoPerfil, Integer genero,
             String nombres, String apellidos,String foto,String correo,String fechaNacimiento ,
             String direccionCasa, String direccionTrabajo,String telefonoFijo,String telefonoMovil,
             String facebookUser, String twitterUser,String twitterToken,String usuario,String contrasenia
     );
     
     public String actualizarPerfil(
     Integer id_usuario, Integer numeroIdentificacion, Integer tipoIdentificacion, Integer tipoPerfil,
Integer genero, String nombres,String apellidos, String foto, String correo, String fechaNacimiento,
String direccionCasa, String direccionTrabajo, String telefonoFijo, String telefonoMovil, String contrasenia
);
     public Usuario obtenerDetallesUsuario(Integer id_usuario);
     
     public int loginUsuario(String usuario, String contrasenia);
     
     public Usuario loginUsuarioFacebook(String tokenFacebook);
     
     public Usuario loginUsuarioTwitter(String tokenTwitter);
     
     public ArrayList<Usuario>  getUsuariobyUsername(String usuario);
     
     public String recuperarClave(String correo);
}
