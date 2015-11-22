/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.ws;

/**
 *
 * @author jhony
 */
public class TiempoDistanciaInfo {

    private String tiempo;
    private String distancia;

    public TiempoDistanciaInfo() {

    }

    public TiempoDistanciaInfo(String tiempo, String distancia) {
        this.tiempo = tiempo;
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

}
