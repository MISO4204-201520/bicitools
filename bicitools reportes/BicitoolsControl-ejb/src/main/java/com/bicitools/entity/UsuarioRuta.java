/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhony
 */
@Entity
@Table(name = "usuarioRuta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRuta.findAll", query = "SELECT u FROM UsuarioRuta u"),
    @NamedQuery(name = "UsuarioRuta.findByNombre", query = "SELECT u FROM UsuarioRuta u WHERE u.usuarioRutaPK.nombre = :nombre"),
    @NamedQuery(name = "UsuarioRuta.findByUsuario", query = "SELECT u FROM UsuarioRuta u WHERE u.usuarioRutaPK.usuario = :usuario")})
public class UsuarioRuta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRutaPK usuarioRutaPK;

    public UsuarioRuta() {
    }

    public UsuarioRuta(UsuarioRutaPK usuarioRutaPK) {
        this.usuarioRutaPK = usuarioRutaPK;
    }

    public UsuarioRuta(String nombre, String usuario) {
        this.usuarioRutaPK = new UsuarioRutaPK(nombre, usuario);
    }

    public UsuarioRutaPK getUsuarioRutaPK() {
        return usuarioRutaPK;
    }

    public void setUsuarioRutaPK(UsuarioRutaPK usuarioRutaPK) {
        this.usuarioRutaPK = usuarioRutaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRutaPK != null ? usuarioRutaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRuta)) {
            return false;
        }
        UsuarioRuta other = (UsuarioRuta) object;
        if ((this.usuarioRutaPK == null && other.usuarioRutaPK != null) || (this.usuarioRutaPK != null && !this.usuarioRutaPK.equals(other.usuarioRutaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.UsuarioRuta[ usuarioRutaPK=" + usuarioRutaPK + " ]";
    }
    
}
