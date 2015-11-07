package com.bicitools.pruebarest;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.dao.AmigosRedesDAOLocal;
import com.bicitools.dao.ChatsDAOLocal;
import com.bicitools.mjson.ChatInfoJson;
import com.bicitools.mjson.IdEntidadJson;
import com.bicitools.mjson.MensajeChatJson;
import com.bicitools.mjson.NuevoChatJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.UsuarioUsuarioJson;
import com.bicitools.mjson.validator.ValidadorCampos;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/comunicacion")
@Stateless
public class Comunicacion {

    // DAO para el servicio de inscriir y actualizar las comunicaciones
    @EJB
    private AmigosRedesDAOLocal amigos;

    @EJB
    private ChatsDAOLocal chats;

    /**
     * Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     *
     * @return String that will be send back as a response of type "text/plain".
     */
    @POST
    @Path("/agregarUsuarioComoAmigo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson agregaAmigo(UsuarioUsuarioJson info) {
        RespuestaJson res;
        res = amigos.agregaAmigo(info);
        return res;
    }

    @POST
    @Path("/bloquearUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson bloqueaAmigo(UsuarioUsuarioJson info) {
        RespuestaJson res;
        res = amigos.actualizarAmigo(info, 4);
        return res;
    }

    /*@POST
     @Path("/eliminarAmigo")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public RespuestaJson eliminarAmigo(IdEntidadJson info) {
     */
    @GET
    @Path("eliminarAmigo/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson eliminarAmigo(@PathParam("idChat") String idChat) {
        IdEntidadJson info = new IdEntidadJson();
        info.setIdEntidad(Integer.parseInt(idChat));
        RespuestaJson res;
        res = amigos.actualizarIdEntidad(info, 0);
        return res;
    }
    /*
     @POST
     @Path("/desbloquearUsuario")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public RespuestaJson desbloquearUsuario(IdEntidadJson info) {
        
     */

    @GET
    @Path("desbloquearUsuario/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson desbloquearUsuario(@PathParam("idChat") String idChat) {
        IdEntidadJson info = new IdEntidadJson();
        info.setIdEntidad(Integer.parseInt(idChat));

        RespuestaJson res;
        res = amigos.actualizarIdEntidad(info, 2);
        return res;
    }

    /*
     @POST
     @Path("/aceptarSolicitudAmistad")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public RespuestaJson aceptarSolicitudAmistad(IdEntidadJson info) {
     */
    @GET
    @Path("aceptarSolicitudAmistad/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson aceptarSolicitudAmistad(@PathParam("idChat") String idChat) {
        IdEntidadJson info = new IdEntidadJson();
        info.setIdEntidad(Integer.parseInt(idChat));
        RespuestaJson res;
        res = amigos.actualizarIdEntidad(info, 2);
        return res;
    }
    /*
     @POST
     @Path("/rechazarSolicitudAmistad")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public RespuestaJson rechazarSolicitudAmistad(IdEntidadJson info) {
     */

    @GET
    @Path("rechazarSolicitudAmistad/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson rechazarSolicitudAmistad(@PathParam("idChat") String idChat) {
        IdEntidadJson info = new IdEntidadJson();
        info.setIdEntidad(Integer.parseInt(idChat));
        RespuestaJson res;
        res = amigos.actualizarIdEntidad(info, 3);
        return res;
    }

    @POST
    @Path("/crearChat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson crearChat(NuevoChatJson info) {
        RespuestaJson res;
        res = chats.creaChat(info);
        return res;
    }

    @POST
    @Path("/enviarMensaje")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson enviarMensaje(MensajeChatJson info) {
        RespuestaJson res;
        res = chats.enviarMensaje(info);
        return res;
    }

    @GET
    @Path("obtenerChatPorId/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerChatPorId(@PathParam("idChat") String idChat) {
        RespuestaJson res;
        res = chats.obtenerChatPorId(idChat);
        return res;

    }

    @GET
    @Path("obtenerChatsUsuario/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerChatsUsuario(@PathParam("idChat") String idChat) {
        RespuestaJson res;
        res = chats.obtenerChatsUsuario(idChat);
        return res;

    }

    @GET
    @Path("obtenerSolicitudesAmistad/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerSolicitudesAmistad(@PathParam("idChat") String idChat) {
        RespuestaJson res;
        res = chats.obtenerSolicitudesAmistad(idChat, 1);
        return res;

    }

    @GET
    @Path("obtenerUsuariosBloqueados/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerUsuariosBloqueados(@PathParam("idChat") String idChat) {
        RespuestaJson res;
        res = chats.obtenerSolicitudesAmistad(idChat, 4);
        return res;

    }

    @GET
    @Path("obtenerAmigosUsuario/{idChat}")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerAmigosUsuario(@PathParam("idChat") String idChat) {
        RespuestaJson res;
        res = chats.obtenerSolicitudesAmistad(idChat, 2);
        return res;

    }

    @POST
    @Path("/consume")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson consume(IdEntidadJson info) {

        RespuestaJson res = null;

        //    res= ConsumeServicio.consume1(10);
        //res = chats.enviarMensaje(info);
        return res;
    }

}
