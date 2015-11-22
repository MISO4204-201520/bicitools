/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.mjson.MensajeChatJson;
import com.bicitools.mjson.NuevoChatJson;
import com.bicitools.mjson.RespuestaJson;
import javax.ejb.Local;

/**
 *
 * @author Ludwing
 */
@Local
public interface ChatsDAOLocal {

    public RespuestaJson creaChat(NuevoChatJson info);
    public RespuestaJson enviarMensaje(MensajeChatJson info);

    RespuestaJson obtenerChatPorId(String idChat);

    RespuestaJson obtenerChatsUsuario(String usuario);

    RespuestaJson obtenerSolicitudesAmistad(String usuario, int estado);
    
}
