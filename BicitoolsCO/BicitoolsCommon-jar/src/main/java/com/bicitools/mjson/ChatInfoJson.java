/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import java.util.ArrayList;

/**
 *
 * @author Ludwing
 */
public class ChatInfoJson {
    private String nombreChat;
    private int usuarioChatId;
    private ArrayList<ChatUsuarioInfo> usuarios;
    private ArrayList<ChatMensajeInfo> mensajes;

    /**
     * @return the nombreChat
     */
    public String getNombreChat() {
        return nombreChat;
    }

    /**
     * @param nombreChat the nombreChat to set
     */
    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    /**
     * @return the usuarioChatId
     */
    public int getUsuarioChatId() {
        return usuarioChatId;
    }

    /**
     * @param usuarioChatId the usuarioChatId to set
     */
    public void setUsuarioChatId(int usuarioChatId) {
        this.usuarioChatId = usuarioChatId;
    }

    /**
     * @return the usuarios
     */
    public ArrayList<ChatUsuarioInfo> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(ArrayList<ChatUsuarioInfo> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the mensajes
     */
    public ArrayList<ChatMensajeInfo> getMensajes() {
        return mensajes;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public void setMensajes(ArrayList<ChatMensajeInfo> mensajes) {
        this.mensajes = mensajes;
    }
    
    
}
