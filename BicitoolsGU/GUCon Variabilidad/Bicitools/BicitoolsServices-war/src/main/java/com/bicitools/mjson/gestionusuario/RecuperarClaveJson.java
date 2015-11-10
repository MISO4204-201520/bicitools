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
public class RecuperarClaveJson {
    String correo;

    public RecuperarClaveJson() {
    }

    @Override
    public String toString() {
        return "RecuperarClaveJson{" + "correo=" + correo + '}';
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
