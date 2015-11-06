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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhony
 */
@Entity
@Table(name = "logUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogUsuario.findAll", query = "SELECT l FROM LogUsuario l"),
    @NamedQuery(name = "LogUsuario.findByUsuario", query = "SELECT l FROM LogUsuario l WHERE l.logUsuarioPK.usuario = :usuario"),
    @NamedQuery(name = "LogUsuario.findByFechaRegistro", query = "SELECT l FROM LogUsuario l WHERE l.logUsuarioPK.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "LogUsuario.findByLatitud", query = "SELECT l FROM LogUsuario l WHERE l.logUsuarioPK.latitud = :latitud"),
    @NamedQuery(name = "LogUsuario.findByLongitud", query = "SELECT l FROM LogUsuario l WHERE l.logUsuarioPK.longitud = :longitud"),
    @NamedQuery(name = "LogUsuario.findByRutaActual", query = "SELECT l FROM LogUsuario l WHERE l.rutaActual = :rutaActual"),
    @NamedQuery(name = "LogUsuario.findPuntosRecorrido", query = "SELECT l FROM LogUsuario l WHERE l.rutaActual = :rutaActual AND (l.logUsuarioPK.usuario = :usuario) AND (l.fechaInicioRecorrido = :fechaInicioRecorrido) ORDER BY l.logUsuarioPK.fechaRegistro ASC"),
    @NamedQuery(name = "LogUsuario.findRecorridos", query = "SELECT DISTINCT l.rutaActual, l.fechaInicioRecorrido, l.logUsuarioPK.usuario FROM LogUsuario l WHERE l.logUsuarioPK.usuario = :usuario AND(l.fechaInicioRecorrido BETWEEN :fechaIni AND :fechaFin)"),
    @NamedQuery(name = "LogUsuario.findRecorridosRuta", query = "SELECT DISTINCT l.rutaActual, l.fechaInicioRecorrido, l.logUsuarioPK.usuario FROM LogUsuario l WHERE l.logUsuarioPK.usuario = :usuario AND(l.fechaInicioRecorrido BETWEEN :fechaIni AND :fechaFin) AND l.rutaActual = :rutaActual"),
    @NamedQuery(name = "LogUsuario.findByFechaInicioRecorrido", query = "SELECT l FROM LogUsuario l WHERE l.fechaInicioRecorrido = :fechaInicioRecorrido")})
public class LogUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LogUsuarioPK logUsuarioPK;
    @Size(max = 45)
    @Column(name = "rutaActual")
    private String rutaActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicioRecorrido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioRecorrido;

    public LogUsuario() {
    }

    public LogUsuario(LogUsuarioPK logUsuarioPK) {
        this.logUsuarioPK = logUsuarioPK;
    }

    public LogUsuario(LogUsuarioPK logUsuarioPK, Date fechaInicioRecorrido) {
        this.logUsuarioPK = logUsuarioPK;
        this.fechaInicioRecorrido = fechaInicioRecorrido;
    }

    public LogUsuario(String usuario, Date fechaRegistro, String latitud, String longitud) {
        this.logUsuarioPK = new LogUsuarioPK(usuario, fechaRegistro, latitud, longitud);
    }

    public LogUsuarioPK getLogUsuarioPK() {
        return logUsuarioPK;
    }

    public void setLogUsuarioPK(LogUsuarioPK logUsuarioPK) {
        this.logUsuarioPK = logUsuarioPK;
    }

    public String getRutaActual() {
        return rutaActual;
    }

    public void setRutaActual(String rutaActual) {
        this.rutaActual = rutaActual;
    }

    public Date getFechaInicioRecorrido() {
        return fechaInicioRecorrido;
    }

    public void setFechaInicioRecorrido(Date fechaInicioRecorrido) {
        this.fechaInicioRecorrido = fechaInicioRecorrido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logUsuarioPK != null ? logUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogUsuario)) {
            return false;
        }
        LogUsuario other = (LogUsuario) object;
        if ((this.logUsuarioPK == null && other.logUsuarioPK != null) || (this.logUsuarioPK != null && !this.logUsuarioPK.equals(other.logUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.LogUsuario[ logUsuarioPK=" + logUsuarioPK + " ]";
    }
    
}
