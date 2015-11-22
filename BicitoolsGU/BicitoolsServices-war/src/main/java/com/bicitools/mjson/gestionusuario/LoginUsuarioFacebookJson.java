package com.bicitools.mjson.gestionusuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author TaidyJohana
 */
public class LoginUsuarioFacebookJson {
String facebookToken;
    /*
     */

    @Override
    public String toString() {
        return "LoginUsuarioFacebookJson{" + "facebookToken=" + facebookToken + '}';
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }
    
    public LoginUsuarioFacebookJson() {
    }

}
