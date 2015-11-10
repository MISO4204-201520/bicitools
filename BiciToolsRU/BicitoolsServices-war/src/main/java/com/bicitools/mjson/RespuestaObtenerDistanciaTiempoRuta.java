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
public class RespuestaObtenerDistanciaTiempoRuta {

    private int codigo;
    private String valor;
    private String descripcion;
    private DistanciaTiempoRuta data; 
    public RespuestaObtenerDistanciaTiempoRuta() {

    }

    public RespuestaObtenerDistanciaTiempoRuta(int _code, String _value, String _description,DistanciaTiempoRuta _distanciaTiempoRuta ) {
        codigo = _code;
        valor = _value;
        descripcion = _description;
        data = _distanciaTiempoRuta;
    }

    @Override
    public String toString() {
        return new StringBuffer(" codigo : ").append(this.getCodigo())
                .append(" valor : ").append(this.getValor())
                .append(" descripcion : ").append(this.getDescripcion())
                 .append(" data : ").append(this.data).toString();
    }

    /**
     * @return the data
     */
    public DistanciaTiempoRuta getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(DistanciaTiempoRuta data) {
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