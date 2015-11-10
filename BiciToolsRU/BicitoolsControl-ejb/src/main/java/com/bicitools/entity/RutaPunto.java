/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ludwing
 */
@Entity
@Table(name = "rutaPunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaPunto.findAll", query = "SELECT t FROM RutaPunto t"),
    @NamedQuery(name = "RutaPunto.findByRuta", query = "SELECT t FROM RutaPunto t WHERE t.nombre = :ruta")})
public class RutaPunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id 
    @Column(name="nombre")
    private String nombre;
    @Size(max = 45)
    
    @NotNull
        @Column(name = "latitud",columnDefinition = "DECIMAL(20,10)")
    private String latitud;
     @Size(max = 30)
    @NotNull
    @Column(name = "longitud",columnDefinition = "DECIMAL(20,10)")
    private String longitud;
      @Size(max = 30)
    @NotNull
    
    public RutaPunto(String nombre_, String latitud_, String longitud_) {
        this.nombre = nombre_;
        this.latitud=latitud_;
        this.longitud=longitud_;
    }

    public RutaPunto() {
        
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.nombre == null && other.getNombre() != null) || (this.nombre != null && !this.nombre.equals(other.getNombre()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Ruta[ nombre=" + nombre + " ]";
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


    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

}
