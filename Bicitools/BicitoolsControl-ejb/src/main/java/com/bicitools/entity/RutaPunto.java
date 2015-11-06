/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhony
 */
@Entity
@Table(name = "rutaPunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaPunto.findAll", query = "SELECT r FROM RutaPunto r"),
    @NamedQuery(name = "RutaPunto.findByIdPunto", query = "SELECT r FROM RutaPunto r WHERE r.idPunto = :idPunto"),
    @NamedQuery(name = "RutaPunto.findByNombre", query = "SELECT r FROM RutaPunto r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RutaPunto.findByLatitud", query = "SELECT r FROM RutaPunto r WHERE r.latitud = :latitud"),
    @NamedQuery(name = "RutaPunto.findByLongitud", query = "SELECT r FROM RutaPunto r WHERE r.longitud = :longitud")})
public class RutaPunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPunto")
    private Long idPunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "latitud")
    private String latitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "longitud")
    private String longitud;

    public RutaPunto() {
    }

    public RutaPunto(Long idPunto) {
        this.idPunto = idPunto;
    }

    public RutaPunto(Long idPunto, String nombre, String latitud, String longitud) {
        this.idPunto = idPunto;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Long idPunto) {
        this.idPunto = idPunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPunto != null ? idPunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaPunto)) {
            return false;
        }
        RutaPunto other = (RutaPunto) object;
        if ((this.idPunto == null && other.idPunto != null) || (this.idPunto != null && !this.idPunto.equals(other.idPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.RutaPunto[ idPunto=" + idPunto + " ]";
    }
    
}
