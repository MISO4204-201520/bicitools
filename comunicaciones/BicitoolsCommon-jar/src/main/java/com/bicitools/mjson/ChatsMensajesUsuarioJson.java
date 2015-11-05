/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

/**
 *
 * @author Ludwing
 */
public class ChatsMensajesUsuarioJson {
    private long chatId;
    private String nombre;
    private String foto;
    private long fechaUltimoMensaje;
    private String ultimoMensaje;
    private boolean recibido;

    /**
     * @return the chatId
     */
    public long getChatId() {
        return chatId;
    }

    /**
     * @param chatId the chatId to set
     */
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the fechaUltimoMensaje
     */
    public long getFechaUltimoMensaje() {
        return fechaUltimoMensaje;
    }

    /**
     * @param fechaUltimoMensaje the fechaUltimoMensaje to set
     */
    public void setFechaUltimoMensaje(long fechaUltimoMensaje) {
        this.fechaUltimoMensaje = fechaUltimoMensaje;
    }

    /**
     * @return the ultimoMensaje
     */
    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    /**
     * @param ultimoMensaje the ultimoMensaje to set
     */
    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    /**
     * @return the recibido
     */
    public boolean isRecibido() {
        return recibido;
    }

    /**
     * @param recibido the recibido to set
     */
    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }
    
}
