/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.gestionusuario;

/**
 *
 * @author TaidyJohana
 */
public class GetConexionJson {

    Integer id_usuario;

    public GetConexionJson() {
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "GetConexionJson{" + "id_usuario=" + id_usuario + '}';
    }
    
}
