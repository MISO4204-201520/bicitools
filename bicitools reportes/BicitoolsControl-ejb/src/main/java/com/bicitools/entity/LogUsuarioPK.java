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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jhony
 */
@Embeddable
public class LogUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
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

    public LogUsuarioPK() {
    }

    public LogUsuarioPK(String usuario, Date fechaRegistro, String latitud, String longitud) {
        this.usuario = usuario;
        this.fechaRegistro = fechaRegistro;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
        hash += (usuario != null ? usuario.hashCode() : 0);
        hash += (fechaRegistro != null ? fechaRegistro.hashCode() : 0);
        hash += (latitud != null ? latitud.hashCode() : 0);
        hash += (longitud != null ? longitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogUsuarioPK)) {
            return false;
        }
        LogUsuarioPK other = (LogUsuarioPK) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        if ((this.fechaRegistro == null && other.fechaRegistro != null) || (this.fechaRegistro != null && !this.fechaRegistro.equals(other.fechaRegistro))) {
            return false;
        }
        if ((this.latitud == null && other.latitud != null) || (this.latitud != null && !this.latitud.equals(other.latitud))) {
            return false;
        }
        if ((this.longitud == null && other.longitud != null) || (this.longitud != null && !this.longitud.equals(other.longitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.LogUsuarioPK[ usuario=" + usuario + ", fechaRegistro=" + fechaRegistro + ", latitud=" + latitud + ", longitud=" + longitud + " ]";
    }
    
}
