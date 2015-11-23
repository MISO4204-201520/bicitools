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
 * @author Ludwing
 */
@Entity
@Table(name = "Alquileres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquileres.findAll", query = "SELECT t FROM Alquileres t"),
    @NamedQuery(name = "Alquileres.findByUsuario", query = "SELECT t FROM Alquileres t WHERE t.usuario = :usuario")})
public class Alquileres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 45)

    @Column(name = "articulo")
    private String articulo;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)

    @Column(name = "fechaHora")
    private Date fechaHora;

    public Alquileres() {
    }

    public Alquileres(String empresa_, String articulo_, String usuario_, Date fechaHora_) {
        this.empresa = empresa_;
        this.articulo = articulo_;
        this.usuario = usuario_;
        this.fechaHora = fechaHora_;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getEmpresa() != null ? getEmpresa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Alquileres other = (Alquileres) object;
        if ((this.getEmpresa() == null && other.getEmpresa() != null) || (this.getEmpresa() != null && !this.empresa.equals(other.empresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Alquileres[ empresa=" + getEmpresa() + " ]";
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the articulo
     */
    public String getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(String articulo) {
        this.articulo = articulo;
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
     * @return the fechaHora
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

}
