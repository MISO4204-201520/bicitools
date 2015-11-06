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
@Table(name = "Metricas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metricas.findAll", query = "SELECT m FROM Metricas m"),
    @NamedQuery(name = "Metricas.findById", query = "SELECT m FROM Metricas m WHERE m.id = :id"),
    @NamedQuery(name = "Metricas.findByUsuario", query = "SELECT m FROM Metricas m WHERE m.usuario = :usuario"),
    @NamedQuery(name = "Metricas.findByTiempo", query = "SELECT m FROM Metricas m WHERE m.tiempo = :tiempo"),
    @NamedQuery(name = "Metricas.findByDistancia", query = "SELECT m FROM Metricas m WHERE m.distancia = :distancia"),
    @NamedQuery(name = "Metricas.findByFechaHora", query = "SELECT m FROM Metricas m WHERE m.fechaHora = :fechaHora")})
public class Metricas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)
    @Column(name = "tiempo")
    private String tiempo;
    @Size(max = 45)
    @Column(name = "distancia")
    private String distancia;
    @Size(max = 45)
    @Column(name = "fechaHora")
    private String fechaHora;

    public Metricas() {
    }

    public Metricas(Integer id) {
        this.id = id;
    }

    public Metricas(Integer id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metricas)) {
            return false;
        }
        Metricas other = (Metricas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Metricas[ id=" + id + " ]";
    }
    
}
