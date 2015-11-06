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
public class InfoDomiciliarioJson {
    private String usuario;
    private String direccion;
    private String email;
    private ArrayList<InfoHorarioJson> horario;

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the horario
     */
    public ArrayList<InfoHorarioJson> getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(ArrayList<InfoHorarioJson> horario) {
        this.horario = horario;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

  
}
