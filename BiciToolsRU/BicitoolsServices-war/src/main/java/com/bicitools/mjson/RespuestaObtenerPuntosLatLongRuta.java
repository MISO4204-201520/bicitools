/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import com.bicitools.mjson.googleJson.Steps;

/**
 *
 * @author Pedro
 */
public class RespuestaObtenerPuntosLatLongRuta {
    

    private int code;
    private String value;
    private String description;
    private Steps[] data;
    public RespuestaObtenerPuntosLatLongRuta() {

    }

    public RespuestaObtenerPuntosLatLongRuta(int _code, String _value, String _description,Steps[] _data ) {
        code = _code;
        value = _value;
        description = _description;
        data = _data;
    }

    public void setCode(int _code) {
        code = _code;
    }

    public int getCode() {
        return code;
    }

    public void setValue(String _value) {
        value = _value;
    }

    public String getValue() {
        return value;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return new StringBuffer(" codigo : ").append(this.code)
                .append(" valor : ").append(this.value)
                .append(" descripcion : ").append(this.description)
                 .append(" steps : ").append(this.getData()).toString();
    }

    /**
     * @return the data
     */
    public Steps[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Steps[] data) {
        this.data = data;
    }

}