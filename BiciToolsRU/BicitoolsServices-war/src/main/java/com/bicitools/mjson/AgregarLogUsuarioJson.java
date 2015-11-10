/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import java.util.Date;

/**
 *
 * @author Ludwing
 */
public class AgregarLogUsuarioJson {
    
    private String nombre;
    private float latitudOrigen;
    private float longitudOrigen;
    private String rutaActual;
    private Date fechaInicioRecorrido;
    
  
    public AgregarLogUsuarioJson() {
    }

    
     @Override
    public String toString() {
        return new StringBuffer(" nombre : ").append(this.nombre)
                .append(" latitudOrigen : ").append(this.getLatitudOrigen())
                .append(" longitudOrigen : ").append(this.getLongitudOrigen()).toString();
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
     * @return the latitudOrigen
     */
    public float getLatitudOrigen() {
        return latitudOrigen;
    }

    /**
     * @param latitudOrigen the latitudOrigen to set
     */
    public void setLatitudOrigen(float latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    /**
     * @return the longitudOrigen
     */
    public float getLongitudOrigen() {
        return longitudOrigen;
    }

    /**
     * @param longitudOrigen the longitudOrigen to set
     */
    public void setLongitudOrigen(float longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    /**
     * @return the rutaActual
     */
    public String getRutaActual() {
        return rutaActual;
    }

    /**
     * @param rutaActual the rutaActual to set
     */
    public void setRutaActual(String rutaActual) {
        this.rutaActual = rutaActual;
    }

    /**
     * @return the fechaInicioRecorrido
     */
    public Date getFechaInicioRecorrido() {
        return fechaInicioRecorrido;
    }

    /**
     * @param fechaInicioRecorrido the fechaInicioRecorrido to set
     */
    public void setFechaInicioRecorrido(Date fechaInicioRecorrido) {
        this.fechaInicioRecorrido = fechaInicioRecorrido;
    }
    
            
    
}
