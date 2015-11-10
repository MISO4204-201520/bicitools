/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.gestionusuario;

import java.util.Date;

/**
 *
 * @author TaidyJohana
 */
public class CerrarSesionJson {
    
    Integer id_usuario;
    Date fechaConexion;
    int tipoConexion;

    public int getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(int tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    public CerrarSesionJson() {
    }

    @Override
    public String toString() {
        return "SetConexionJson{" + "id_usuario=" + id_usuario + ", fechaConexion=" + fechaConexion + '}';
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFechaConexion() {
        return fechaConexion;
    }

    public void setFechaConexion(Date fechaConexion) {
        this.fechaConexion = fechaConexion;
    }
    
    
}
