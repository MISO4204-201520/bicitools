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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ludwing
 */
@Entity
@Table(name = "usuarioschat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarioschat.findAll", query = "SELECT u FROM Usuarioschat u"),
    @NamedQuery(name = "Usuarioschat.findByIdusuarioschat", query = "SELECT u FROM Usuarioschat u WHERE u.idusuarioschat = :idusuarioschat"),
    @NamedQuery(name = "Usuarioschat.findByUsuario", query = "SELECT u FROM Usuarioschat u WHERE u.usuario = :usuario")})
public class Usuarioschat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuarioschat")
    private Integer idusuarioschat;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @JoinColumn(name = "idchat", referencedColumnName = "idchat")
    @ManyToOne
    private Chat idchat;

    public Usuarioschat() {
    }

    public Usuarioschat(Integer idusuarioschat) {
        this.idusuarioschat = idusuarioschat;
    }

    public Integer getIdusuarioschat() {
        return idusuarioschat;
    }

    public void setIdusuarioschat(Integer idusuarioschat) {
        this.idusuarioschat = idusuarioschat;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Chat getIdchat() {
        return idchat;
    }

    public void setIdchat(Chat idchat) {
        this.idchat = idchat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuarioschat != null ? idusuarioschat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarioschat)) {
            return false;
        }
        Usuarioschat other = (Usuarioschat) object;
        if ((this.idusuarioschat == null && other.idusuarioschat != null) || (this.idusuarioschat != null && !this.idusuarioschat.equals(other.idusuarioschat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Usuarioschat[ idusuarioschat=" + idusuarioschat + " ]";
    }
    
}
