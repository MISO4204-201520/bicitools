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
public class InfoHorarioJson {

    private ArrayList<String> dia;
    private String inicio;
    private String fin;

    /**
     * @return the dia
     */
    public ArrayList<String> getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(ArrayList<String> dia) {
        this.dia = dia;
    }

    /**
     * @return the inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
    }

    

}
