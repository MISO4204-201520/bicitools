/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "LogsUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogsUsuario.findAll", query = "SELECT t FROM LogsUsuario t"),
    @NamedQuery(name = "LogsUsuario.findByUsuario", query = "SELECT t FROM LogsUsuario t WHERE t.usuario = :usuario")})
public class LogsUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)

    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;
    @Size(max = 45)

    @NotNull
    @Column(name = "latitud", columnDefinition = "DECIMAL(20,10)")
    private String latitud;
    @Size(max = 30)
    @NotNull
    @Column(name = "longitud", columnDefinition = "DECIMAL(20,10)")
    private String longitud;
    @Size(max = 30)
    @NotNull
    
    @Column(name = "rutaActual", columnDefinition = "DECIMAL(20,10)")
    private String rutaActual;
    @Size(max = 30)
    @NotNull
    @Column(name = "fechaInicioRecorrido")
    private Date fechaInicioRecorrido;
    @Size(max = 45)
    
    
    public LogsUsuario() {
    }

    public LogsUsuario(String usuario_, Date fechaRegistro_, String latitud_, String longitud_, String rutaActual_, Date fechaInicioRecorrido_) {
      this.usuario = usuario_;
      this.fechaRegistro = fechaRegistro_;
      this.latitud = latitud_;
      this.longitud = longitud_;
      this.rutaActual = rutaActual_;
      this.fechaInicioRecorrido = fechaInicioRecorrido_;
      
      
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUsuario() != null ? getUsuario().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        LogsUsuario other = (LogsUsuario) object;
        if ((this.getUsuario() == null && other.getUsuario() != null)  ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Ruta[ nombre=" + getUsuario() + " ]";
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
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    /**
     * @return the rutaActual
     */
    public String getRutaActual() {
        return rutaActual;
    }

    /**
     * @param rutaActual the rutaActual to set
     */
    public void setRutaActual(String rutaActual) {
        this.rutaActual = rutaActual;
    }

    /**
     * @return the fechaInicioRecorrido
     */
    public Date getFechaInicioRecorrido() {
        return fechaInicioRecorrido;
    }

    /**
     * @param fechaInicioRecorrido the fechaInicioRecorrido to set
     */
    public void setFechaInicioRecorrido(Date fechaInicioRecorrido) {
        this.fechaInicioRecorrido = fechaInicioRecorrido;
    }

}
