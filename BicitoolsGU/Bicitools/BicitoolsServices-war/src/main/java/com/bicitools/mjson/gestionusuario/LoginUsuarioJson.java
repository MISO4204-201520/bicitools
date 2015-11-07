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
public class LoginUsuarioJson {
String user, contrasenia;
    /*
     */

    @Override
    public String toString() {
        return "LoginUsuarioJson{" + "user=" + user + ", contrasenia=" + contrasenia + '}';
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
   



    public LoginUsuarioJson() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    

    
}
