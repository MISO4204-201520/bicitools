/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;


import com.bicitools.entity.Conexiones;
import com.bicitools.entity.Usuario;
import com.bicitools.uploaderfiles.MultipartFileUploader;
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
public class UsuarioDAO implements UsuarioDAOLocal{
 @PersistenceContext(unitName="com.bicitools_unit")
    EntityManager em;
    
    @Override
    public int registrarUsuario(Integer numeroIdentificacion,
            Integer tipoIdentificacion, Integer tipoPerfil, Integer genero, 
            String nombres, String apellidos, String foto, String correo, 
            String fechaNacimiento, String direccionCasa, String direccionTrabajo, 
            String telefonoFijo, String telefonoMovil, String facebookUser, String twitterUser, 
            String usuario, String contrasenia) {
        int resString=0;
        try{
            Usuario u = new Usuario();
        u.setNumeroIdentificacion(numeroIdentificacion);
        
        u.setTipoIdentificacion(tipoIdentificacion);
        
        u.setTipoPerfil(tipoPerfil);
        
        u.setGenero(genero);
        
        u.setNombres(nombres);
        
        u.setApellidos(apellidos);
        
        u.setFoto(foto);
        
        u.setCorreo(correo);
        
        u.setFechaNacimiento(fechaNacimiento);
        
        u.setDireccionCasa(direccionCasa);
        
        u.setDireccionTrabajo(direccionTrabajo);
        
        u.setTelefonoFijo(telefonoFijo);
        
        u.setTelefonoMovil(telefonoMovil);
        
        u.setFacebookUser(facebookUser);
        
        u.setTwitterUser(twitterUser);
        
        u.setUsuario(usuario);
        
        u.setContrasenia(contrasenia);
        
        em.persist(u);
        em.flush();
        resString=u.getId();
        
           // MultipartFileUploader fileUploader = new MultipartFileUploader();
            //String respuesta=fileUploader.uploadFile(foto, "http://localhost/bicitools_pictures/profile_user");
        
        }catch(Exception e){
        resString=0;
        }
        
        return resString;
    }

    @Override
    public int registrarUsuarioFacebook(Integer numeroIdentificacion, Integer tipoIdentificacion, Integer tipoPerfil, Integer genero, String nombres, String apellidos, String foto, String correo, String fechaNacimiento, String direccionCasa, String direccionTrabajo, String telefonoFijo, String telefonoMovil, String facebookUser, String facebookToken, String twitterUser, String usuario, String contrasenia) {
        int resString=0;
        try{
            Usuario u = new Usuario();
        u.setNumeroIdentificacion(numeroIdentificacion);
        
        u.setTipoIdentificacion(tipoIdentificacion);
        
        u.setTipoPerfil(tipoPerfil);
        
        u.setGenero(genero);
        
        u.setNombres(nombres);
        
        u.setApellidos(apellidos);
        
        u.setFoto(foto);
        
        u.setCorreo(correo);
        
        u.setFechaNacimiento(fechaNacimiento);
        
        u.setDireccionCasa(direccionCasa);
        
        u.setDireccionTrabajo(direccionTrabajo);
        
        u.setTelefonoFijo(telefonoFijo);
        
        u.setTelefonoMovil(telefonoMovil);
        
        u.setFacebookUser(facebookUser);
        u.setFacebookToken(facebookToken);
        u.setTwitterUser(twitterUser);
        
        u.setUsuario(usuario);
        
        u.setContrasenia(contrasenia);
        
        em.persist(u);
        em.flush();
        resString=u.getId();
        }catch(Exception e){
        resString=0;
        }
        
        return resString;
    }

    @Override
    public int registrarUsuarioTwitter(Integer numeroIdentificacion, Integer tipoIdentificacion, Integer tipoPerfil, Integer genero, String nombres, String apellidos, String foto, String correo, String fechaNacimiento, String direccionCasa, String direccionTrabajo, String telefonoFijo, String telefonoMovil, String facebookUser, String twitterUser, String twitterToken, String usuario, String contrasenia) {
int resString=0;
        try{
            Usuario u = new Usuario();
        u.setNumeroIdentificacion(numeroIdentificacion);
        
        u.setTipoIdentificacion(tipoIdentificacion);
        
        u.setTipoPerfil(tipoPerfil);
        
        u.setGenero(genero);
        
        u.setNombres(nombres);
        
        u.setApellidos(apellidos);
        
        u.setFoto(foto);
        
        u.setCorreo(correo);
        
        u.setFechaNacimiento(fechaNacimiento);
        
        u.setDireccionCasa(direccionCasa);
        
        u.setDireccionTrabajo(direccionTrabajo);
        
        u.setTelefonoFijo(telefonoFijo);
        
        u.setTelefonoMovil(telefonoMovil);
        
        u.setFacebookUser(facebookUser);
        
        u.setTwitterUser(twitterUser);
        u.setTwitterToken(twitterToken);
        u.setUsuario(usuario);
        
        u.setContrasenia(contrasenia);
        
        em.persist(u);
        em.flush();
        resString=u.getId();
        }catch(Exception e){
        resString=0;
        }
        
        return resString;
    }

    @Override
    public String actualizarPerfil(Integer id_usuario, Integer numeroIdentificacion, 
            Integer tipoIdentificacion, Integer tipoPerfil, Integer genero, String nombres, 
            String apellidos, String foto, String correo, String fechaNacimiento,
            String direccionCasa, String direccionTrabajo, String telefonoFijo, 
            String telefonoMovil, String contrasenia) {
        
        String res="";
        Usuario usuario = new Usuario();
        Query query = em.createNamedQuery("Usuario.findById");
        query.setParameter("id", id_usuario);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        
            usuario=(Usuario)dlist.get(0);
            em.refresh(usuario);
            usuario.setNumeroIdentificacion(numeroIdentificacion);
            usuario.setTipoIdentificacion(tipoIdentificacion);
            usuario.setTipoPerfil(tipoPerfil);
            usuario.setGenero(genero);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setFoto(foto);
            usuario.setCorreo(correo);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setDireccionCasa(direccionCasa);
            usuario.setDireccionTrabajo(direccionTrabajo);
            usuario.setTelefonoFijo(telefonoFijo);
            usuario.setTelefonoMovil(telefonoMovil);
            usuario.setContrasenia(contrasenia);
            em.persist(usuario);
            
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
    public Usuario obtenerDetallesUsuario(Integer id_usuario) {
        
        String res="";
        Usuario usuario = new Usuario();
        Query query = em.createNamedQuery("Usuario.findById");
        query.setParameter("id", id_usuario);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        
            usuario=(Usuario)dlist.get(0);
            
            
        }catch(Exception e){
        usuario=null;
        }    
        
        }else{
        
        usuario=null;
        }
        return usuario;
    }

    @Override
    public int loginUsuario(String user, String contrasenia) {
        int id_usuario=0;
        Usuario usuario = new Usuario();
        String sentencia="SELECT u FROM Usuario u WHERE u.usuario = '"+user+"' AND u.contrasenia = '"+contrasenia+"'";
        Query query = em.createQuery(sentencia);
        List dlist = query.getResultList();
        if (!dlist.isEmpty()) {
        try{
            usuario=(Usuario)dlist.get(0);
            id_usuario=usuario.getId();
        }catch(Exception e){
        id_usuario=0;
            System.err.println("errorlogin_"+e.toString());
        }    
        }else{
        id_usuario=0;
        System.err.println("errorlogin_vacio");
        }
        return id_usuario;
    }

    @Override
    public Usuario loginUsuarioFacebook(String tokenFacebook) {
        
        Usuario usuario = new Usuario();
        Query query = em.createNamedQuery("Usuario.findByFacebookToken");
        query.setParameter("facebookToken", tokenFacebook);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        
            usuario=(Usuario)dlist.get(0);
            
            
        }catch(Exception e){
        usuario=null;
        }    
        
        }else{
        
        usuario=null;
        }
        return usuario;
    }

    @Override
    public Usuario loginUsuarioTwitter(String tokenTwitter) {
        Usuario usuario = new Usuario();
        Query query = em.createNamedQuery("Usuario.findByTwitterToken");
        query.setParameter("twitterToken", tokenTwitter);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
        
            usuario=(Usuario)dlist.get(0);
            
            
        }catch(Exception e){
        usuario=null;
        }    
        
        }else{
        
        usuario=null;
        }
        return usuario;
    }

    @Override
    public ArrayList<Usuario>  getUsuariobyUsername(String username) {
           ArrayList<Usuario> usuario= new ArrayList<Usuario>();
        
        Query query = em.createNamedQuery("Usuario.findByUsuario");
        query.setParameter("usuario", username);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
            Usuario usuario1 = new Usuario();
         usuario1= (Usuario)dlist.get(0);
            usuario.add(usuario1);
        }catch(Exception e){
        usuario=null;
        }    
        
        }else{
        
        usuario=null;
        }
        return usuario;
    }

    @Override
    public String recuperarClave(String correo) {
        String contrasenia="";
        
        Usuario usuario = new Usuario();
        Query query = em.createNamedQuery("Usuario.findByCorreo");
        query.setParameter("correo", correo);
        List dlist = query.getResultList();
        
        if (!dlist.isEmpty()) {
        try{
            usuario=(Usuario)dlist.get(0);
            contrasenia= usuario.getContrasenia();
        }catch(Exception e){
        contrasenia="error";
            System.err.println("errorlogin_"+e.toString());
        }    
        }else{
        contrasenia="error";
        System.err.println("errorlogin_vacio");
        }
        return contrasenia;
    }
    
}
