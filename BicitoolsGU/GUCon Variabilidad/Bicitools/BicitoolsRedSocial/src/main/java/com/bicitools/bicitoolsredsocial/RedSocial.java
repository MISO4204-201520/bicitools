package com.bicitools.bicitoolsredsocial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.bicitools.dao.UsuarioDAOLocal;
import com.bicitools.entity.Usuario;
import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Taidy
 */

@Path("/gestionusuario/redsocial")
public class RedSocial {
  @EJB
private UsuarioDAOLocal usuarioDAOLocal;
    
   
     @POST
    @Path("/loginUsuarioFacebook")
     @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public RespuestaJsonRedSocial loginUsuarioFacebook(LoginUsuarioFacebookJson usuarioJson){
         RespuestaJsonRedSocial respuesta = new RespuestaJsonRedSocial();
   ArrayList<String> datos= new ArrayList<String>();
         try{
             Usuario usuario = new Usuario();
             usuario= usuarioDAOLocal.loginUsuarioFacebook(usuarioJson.getFacebookToken());
             if(usuario!=null){
   datos.add(String.valueOf(usuario.getId()));
   datos.add(usuario.getUsuario());
   respuesta.setCodigo(0);
   respuesta.setValor("info");
   respuesta.setDescripcion("Inicio Correctamente");
   respuesta.setDatos(datos);
   }
   else{
   respuesta.setCodigo(101);
   respuesta.setValor("error");
   respuesta.setDescripcion("Error de login");
   
   }
    }catch(Exception e){
        System.out.println("noguardo");
        
   respuesta.setCodigo(101);
   respuesta.setValor("error");
   respuesta.setDescripcion("Error de login");
    }
    
  return  respuesta;
    }
    @POST
    @Path("/loginUsuarioTwitter")
     @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public RespuestaJsonRedSocial loginUsuarioTwitter(LoginUsuarioTwitterJson usuarioJson){
         RespuestaJsonRedSocial respuesta = new RespuestaJsonRedSocial();
   ArrayList<String> datos= new ArrayList<String>();
         try{
             Usuario usuario = new Usuario();
             usuario= usuarioDAOLocal.loginUsuarioTwitter(usuarioJson.getTwitterToken());
             if(usuario!=null){
   datos.add(String.valueOf(usuario.getId()));
   datos.add(usuario.getUsuario());
   respuesta.setCodigo(0);
   respuesta.setValor("info");
   respuesta.setDescripcion("Inicio Correctamente");
   respuesta.setDatos(datos);
   }
   else{
   respuesta.setCodigo(101);
   respuesta.setValor("error");
   respuesta.setDescripcion("Error de login");
   
   }
    }catch(Exception e){
        System.out.println("noguardo");
        
   respuesta.setCodigo(101);
   respuesta.setValor("error");
   respuesta.setDescripcion("Error de login");
    }
    
  return  respuesta;
    }
    @POST
    @Path("/registrarUsuarioFacebook")
     @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public RespuestaJsonRedSocial registrarUsuarioFacebook(RegistrarUsuarioFacebookJson usuarioFacebookJson){
         RespuestaJsonRedSocial respuesta = new RespuestaJsonRedSocial();
   ArrayList<Integer>dato= new ArrayList<Integer>();
         try{
   int idUusario=  usuarioDAOLocal.registrarUsuarioFacebook(usuarioFacebookJson.getNumeroIdentificacion(), usuarioFacebookJson.getTipoIdentificacion(),
     usuarioFacebookJson.getTipoPerfil(),usuarioFacebookJson.getGenero(),usuarioFacebookJson.getNombres(),usuarioFacebookJson.getApellidos(),
     usuarioFacebookJson.getFoto(),usuarioFacebookJson.getCorreo(),usuarioFacebookJson.getFechaNacimiento(),usuarioFacebookJson.getDireccionCasa(),
     usuarioFacebookJson.getDireccionTrabajo(), usuarioFacebookJson.getTelefonoFijo(),
     usuarioFacebookJson.getTelefonoMovil(),usuarioFacebookJson.getFacebookUser(),usuarioFacebookJson.getFacebookToken(),
     usuarioFacebookJson.getTwitterUser(),usuarioFacebookJson.getUsuario(),usuarioFacebookJson.getContrasenia());
   
   dato.add(idUusario);
   new ArrayList().add(idUusario);
   if(idUusario!=0){
   respuesta= ConstruyeRespuestaRedSocial.construyeRespuestaConDatos(0,"info","Usuario Registrado con Lgin de facebook Correctamente",dato);
   }
   else{
   respuesta= ConstruyeRespuestaRedSocial.construyeRespuestaFalla("Error al insertar",dato);
   }
    }catch(Exception e){
        System.out.println("noguardo");
        dato.add(0);
    respuesta= ConstruyeRespuestaRedSocial.construyeRespuestaFalla("Error al insertar",dato);
    }
    
  return  respuesta;
    }
    @POST
    @Path("/registrarUsuarioTwitter")
 @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    
    public RespuestaJsonRedSocial registrarUsuarioTwitter(RegistrarUsuarioTwitterJson usuarioTwitterJson){
         RespuestaJsonRedSocial respuesta = new RespuestaJsonRedSocial();
   ArrayList<Integer>dato= new ArrayList<Integer>();
         try{
   int idUusario=  usuarioDAOLocal.registrarUsuarioTwitter(usuarioTwitterJson.getNumeroIdentificacion(), usuarioTwitterJson.getTipoIdentificacion(),
     usuarioTwitterJson.getTipoPerfil(),usuarioTwitterJson.getGenero(),usuarioTwitterJson.getNombres(),usuarioTwitterJson.getApellidos(),
     usuarioTwitterJson.getFoto(),usuarioTwitterJson.getCorreo(),usuarioTwitterJson.getFechaNacimiento(),usuarioTwitterJson.getDireccionCasa(),
     usuarioTwitterJson.getDireccionTrabajo(), usuarioTwitterJson.getTelefonoFijo(),
     usuarioTwitterJson.getTelefonoMovil(),usuarioTwitterJson.getFacebookUser(),
     usuarioTwitterJson.getTwitterUser(),usuarioTwitterJson.getTwitterToken(),usuarioTwitterJson.getUsuario(),usuarioTwitterJson.getContrasenia());
     dato.add(idUusario);
   new ArrayList().add(idUusario);
   if(idUusario!=0){
   respuesta= ConstruyeRespuestaRedSocial.construyeRespuestaConDatos(0,"info","Usuario Registrado con Lgin de twitter Correctamente",dato);
   }
   else{
   respuesta= ConstruyeRespuestaRedSocial.construyeRespuestaFalla("Error al insertar",dato);
   }
    }catch(Exception e){
        System.out.println("noguardo");
        dato.add(0);
    respuesta= ConstruyeRespuestaRedSocial.construyeRespuestaFalla("Error al insertar",dato);
    }
    
  return  respuesta;
    }


}
