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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ludwing
 */
@Entity
@Table(name = "amigosredes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amigosredes.findAll", query = "SELECT a FROM Amigosredes a"),
    @NamedQuery(name = "Amigosredes.findByUsuarios", query = "SELECT a FROM Amigosredes a WHERE a.usuario2 = :usuario2 and a.usuario1 = :usuario1"),
    @NamedQuery(name = "Amigosredes.findByOrigenEstado", query = "SELECT a FROM Amigosredes a WHERE a.usuario1 = :usuario1 and a.estado = :estado"),
    @NamedQuery(name = "Amigosredes.findByIdAmigosredes", query = "SELECT a FROM Amigosredes a WHERE a.idAmigosredes = :idAmigosredes"),
    @NamedQuery(name = "Amigosredes.findByEstado", query = "SELECT a FROM Amigosredes a WHERE a.estado = :estado"),
    @NamedQuery(name = "Amigosredes.findByUsuario2", query = "SELECT a FROM Amigosredes a WHERE a.usuario2 = :usuario2"),
    @NamedQuery(name = "Amigosredes.findByUsuario1", query = "SELECT a FROM Amigosredes a WHERE a.usuario1 = :usuario1")})
public class Amigosredes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_amigosredes")
    private Integer idAmigosredes;
    @Column(name = "estado")
    private Integer estado;
    @Size(max = 45)
    @Column(name = "usuario2")
    private String usuario2;
    @Size(max = 45)
    @Column(name = "usuario1")
    private String usuario1;

    public Amigosredes() {
    }

    public Amigosredes(Integer idAmigosredes) {
        this.idAmigosredes = idAmigosredes;
    }

    public Integer getIdAmigosredes() {
        return idAmigosredes;
    }

    public void setIdAmigosredes(Integer idAmigosredes) {
        this.idAmigosredes = idAmigosredes;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAmigosredes != null ? idAmigosredes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amigosredes)) {
            return false;
        }
        Amigosredes other = (Amigosredes) object;
        if ((this.idAmigosredes == null && other.idAmigosredes != null) || (this.idAmigosredes != null && !this.idAmigosredes.equals(other.idAmigosredes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Amigosredes[ idAmigosredes=" + idAmigosredes + " ]";
    }
    
}
