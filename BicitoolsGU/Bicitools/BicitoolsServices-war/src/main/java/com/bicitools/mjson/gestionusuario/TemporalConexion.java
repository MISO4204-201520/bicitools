/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.gestionusuario;

import java.util.Date;

/**
 *
 * @author Taidy
 */
public class TemporalConexion {
     private Integer idConexion;
        private int idUsuario;
          private String fechaConexion;

    public int getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(int tipoConexion) {
        this.tipoConexion = tipoConexion;
    }
          private int tipoConexion;
          
          

    public TemporalConexion(Integer idConexion, int idUsuario, String fechaConexion, int tipoConexion) {
        this.idConexion = idConexion;
        this.idUsuario = idUsuario;
        this.fechaConexion = fechaConexion;
        this.tipoConexion=tipoConexion;
    }

    public Integer getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(Integer idConexion) {
        this.idConexion = idConexion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaConexion() {
        return fechaConexion;
    }

    public void setFechaConexion(String fechaConexion) {
        this.fechaConexion = fechaConexion;
    }

    
    
}
