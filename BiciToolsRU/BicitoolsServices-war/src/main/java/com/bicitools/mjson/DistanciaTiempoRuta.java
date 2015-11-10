/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

/**
 *
 * @author Pedro
 */
public class DistanciaTiempoRuta {
   
    private String distancia;
    private String tiempo;

    public DistanciaTiempoRuta( String _distancia, String _tiempo) {
        distancia= _distancia;
        tiempo= _tiempo;
    }
    
    /**
     * @return the distancia
     */
    public String getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the tiempo
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
   
    
      @Override
    public String toString() {
        return new StringBuffer(" distancia : ").append(this.distancia)
                .append(" tiempo : ").append(this.tiempo).toString();
    }

    
    
}
