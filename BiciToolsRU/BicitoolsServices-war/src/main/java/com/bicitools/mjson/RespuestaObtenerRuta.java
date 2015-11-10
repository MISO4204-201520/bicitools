/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson;

import com.bicitools.entity.Ruta;
import com.bicitools.mjson.googleJson.Steps;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class RespuestaObtenerRuta {
    

    private int codigo;
    private String valor;
    private String descripcion;
    private Object[]  data;
    public RespuestaObtenerRuta() {

    }

    public RespuestaObtenerRuta(int _code, String _value, String _description,Object[] _data ) {
        codigo = _code;
        valor = _value;
        descripcion = _description;
       
        data =  _data;
    }

    
    @Override
    public String toString() {
       
        return new StringBuffer(" codigo : ").append(this.getCodigo())
                .append(" valor : ").append(this.getValor())
                .append(" descripcion : ").append(this.getDescripcion())
                 .append(" rutas : ").append(this.getData()).toString();
        
    }

    /**
     * @return the data
     */
    public Object[]  getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData( Object[] data) {
        this.data = data;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}