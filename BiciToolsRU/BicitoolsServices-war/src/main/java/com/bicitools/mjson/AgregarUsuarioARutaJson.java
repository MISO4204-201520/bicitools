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
public class AgregarUsuarioARutaJson {
    private String usuario;
    private String nombre;
    
    public AgregarUsuarioARutaJson(String usuario_, String nombre_){
     this.usuario=usuario_; 
      this.nombre=nombre_; 
    }

    public AgregarUsuarioARutaJson() {
    }
    
    

     @Override
    public String toString() {
        return new StringBuffer(" usuario : ").append(this.usuario)
                .append(" nombre : ").append(this.nombre).toString();
    }

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
            
    
}
