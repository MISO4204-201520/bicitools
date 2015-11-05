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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Taidy
 */
@Entity
@Table(name = "conexiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conexiones.findAll", query = "SELECT c FROM Conexiones c"),
    @NamedQuery(name = "Conexiones.findByIdConexion", query = "SELECT c FROM Conexiones c WHERE c.idConexion = :idConexion"),
    @NamedQuery(name = "Conexiones.findByIdUsuario", query = "SELECT c FROM Conexiones c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Conexiones.findByFechaConexion", query = "SELECT c FROM Conexiones c WHERE c.fechaConexion = :fechaConexion"),
    @NamedQuery(name = "Conexiones.findByTipoConexion", query = "SELECT c FROM Conexiones c WHERE c.tipoConexion = :tipoConexion")})
public class Conexiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_conexion")
    private Integer idConexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaConexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoConexion")
    private int tipoConexion;

    public Conexiones() {
    }

    public Conexiones(Integer idConexion) {
        this.idConexion = idConexion;
    }

    public Conexiones(Integer idConexion, int idUsuario, Date fechaConexion, int tipoConexion) {
        this.idConexion = idConexion;
        this.idUsuario = idUsuario;
        this.fechaConexion = fechaConexion;
        this.tipoConexion = tipoConexion;
    }

    public Integer getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(Integer idConexion) {
        this.idConexion = idConexion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaConexion() {
        return fechaConexion;
    }

    public void setFechaConexion(Date fechaConexion) {
        this.fechaConexion = fechaConexion;
    }

    public int getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(int tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConexion != null ? idConexion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conexiones)) {
            return false;
        }
        Conexiones other = (Conexiones) object;
        if ((this.idConexion == null && other.idConexion != null) || (this.idConexion != null && !this.idConexion.equals(other.idConexion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Conexiones[ idConexion=" + idConexion + " ]";
    }
    
}
