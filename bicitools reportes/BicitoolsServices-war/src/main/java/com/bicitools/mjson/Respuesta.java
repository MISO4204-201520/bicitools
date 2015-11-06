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
public class Respuesta {

    private int code;
    private String value;
    private String description;

    public Respuesta() {

    }

    public Respuesta(int _code, String _value, String _description) {
        code = _code;
        value = _value;
        description = _description;
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
        return new StringBuffer(" code : ").append(this.code)
                .append(" value : ").append(this.value)
                .append(" description : ").append(this.description).toString();
    }

}
