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
public class DatosEntradaServicioJson {
    private String usuario;
    private long idServicio;
    private String estimado;

    /**
     * @return the userio
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param userio the userio to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the idServicio
     */
    public long getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(long idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the estimado
     */
    public String getEstimado() {
        return estimado;
    }

    /**
     * @param estimado the estimado to set
     */
    public void setEstimado(String estimado) {
        this.estimado = estimado;
    }

  
}
