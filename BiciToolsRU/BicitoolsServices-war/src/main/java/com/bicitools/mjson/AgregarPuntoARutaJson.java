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
public class AgregarPuntoARutaJson {
    
    private String nombre;
    private float latitudOrigen;
    private float longitudOrigen;
    
  
    public AgregarPuntoARutaJson() {
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
    
            
    
}
