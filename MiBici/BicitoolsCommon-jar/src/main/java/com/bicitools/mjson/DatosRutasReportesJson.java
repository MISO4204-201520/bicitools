/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import com.bicitools.mjson.DatosLugaresJson;
import java.util.ArrayList;

/**
 *
 * @author jhony
 */
public class DatosRutasReportesJson extends DatosRutasJson{

    private ArrayList<DatosLugaresJson> lugares;

    /**
     * @return the lugares
     */
    public ArrayList<DatosLugaresJson> getLugares() {
        return lugares;
    }

    /**
     * @param lugares the lugares to set
     */
    public void setLugares(ArrayList<DatosLugaresJson> lugares) {
        this.lugares = lugares;
    }
  
}

