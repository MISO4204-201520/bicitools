package com.bicitools.bicitoolsredsocial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author TaidyJohana
 */
import com.bicitools.entity.Conexiones;
import java.util.ArrayList;


public class RespuestaJsonRedSocial {

    private int codigo;
    private String valor;
    private String descripcion;
    private ArrayList datos;
   private ArrayList<Conexiones> datosCOnexion;
    public RespuestaJsonRedSocial() {

    }

    public RespuestaJsonRedSocial(int _codigo, String _valor, String _descripcion) {
        codigo = _codigo;
        valor = _valor;
        descripcion = _descripcion;
    }

    public RespuestaJsonRedSocial(int codigo, String valor, String descripcion, ArrayList datos) {
        this.codigo = codigo;
        this.valor = valor;
        this.descripcion = descripcion;
        this.datos = datos;
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

    /**
     * @return the datos
     */
    public ArrayList getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(ArrayList datos) {
        this.datos = datos;
    }
 public void setDatosConexion( ArrayList<Conexiones> datosCOnexion) {
     System.out.println(datosCOnexion.get(0).getFechaConexion()+ " : fecha conexion respuesta");
        this.datos = datosCOnexion;
    }

}
