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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ludwing
 */
@Entity
@Table(name = "mensajeschat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajeschat.findAll", query = "SELECT m FROM Mensajeschat m"),
    @NamedQuery(name = "Mensajeschat.findByIdmensajeschat", query = "SELECT m FROM Mensajeschat m WHERE m.idmensajeschat = :idmensajeschat"),
    @NamedQuery(name = "Mensajeschat.findByFecha", query = "SELECT m FROM Mensajeschat m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajeschat.findByMensaje", query = "SELECT m FROM Mensajeschat m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajeschat.findByUsuario", query = "SELECT m FROM Mensajeschat m WHERE m.usuario = :usuario"),
    @NamedQuery(name = "Mensajeschat.findByRecibido", query = "SELECT m FROM Mensajeschat m WHERE m.recibido = :recibido"),
    @NamedQuery(name = "Mensajeschat.findByIdchat", query = "SELECT m FROM Mensajeschat m WHERE m.idchat = :idchat")})
public class Mensajeschat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmensajeschat")
    private Integer idmensajeschat;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 500)
    @Column(name = "mensaje")
    private String mensaje;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "recibido")
    private Integer recibido;
    @Column(name = "idchat")
    private Integer idchat;

    public Mensajeschat() {
    }

    public Mensajeschat(Integer idmensajeschat) {
        this.idmensajeschat = idmensajeschat;
    }

    public Integer getIdmensajeschat() {
        return idmensajeschat;
    }

    public void setIdmensajeschat(Integer idmensajeschat) {
        this.idmensajeschat = idmensajeschat;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getRecibido() {
        return recibido;
    }

    public void setRecibido(Integer recibido) {
        this.recibido = recibido;
    }

    public Integer getIdchat() {
        return idchat;
    }

    public void setIdchat(Integer idchat) {
        this.idchat = idchat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmensajeschat != null ? idmensajeschat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensajeschat)) {
            return false;
        }
        Mensajeschat other = (Mensajeschat) object;
        if ((this.idmensajeschat == null && other.idmensajeschat != null) || (this.idmensajeschat != null && !this.idmensajeschat.equals(other.idmensajeschat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Mensajeschat[ idmensajeschat=" + idmensajeschat + " ]";
    }
    
}
