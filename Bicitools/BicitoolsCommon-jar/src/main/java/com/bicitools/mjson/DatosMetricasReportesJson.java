/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import java.util.ArrayList;

/**
 *
 * @author jhony
 */
public class DatosMetricasReportesJson {
   
    private MetricasUsuario total;
    private ArrayList<DatosMetricasUsuarioJson> lista;

    /**
     * @return the total
     */
    public MetricasUsuario getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(MetricasUsuario total) {
        this.total = total;
    }

    /**
     * @return the lista
     */
    public ArrayList<DatosMetricasUsuarioJson> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ArrayList<DatosMetricasUsuarioJson> lista) {
        this.lista = lista;
    }
    
}
