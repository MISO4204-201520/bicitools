/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mibici.general;

import java.util.ArrayList;
import com.bicitools.mibici.general.ParamConfigBici;

/**
 *
 * @author jhony
 */
public class InfoConfigMiBiciJson {
    private String usuario;
    private String consulta;
    private ArrayList<ParamConfigBici> config;            

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
     * @return the consulta
     */
    public String getConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    /**
     * @return the config
     */
    public ArrayList<ParamConfigBici> getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(ArrayList<ParamConfigBici> config) {
        this.config = config;
    }


}
