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
 * @author jhony
 */
@Entity
@Table(name = "PartesGenericas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartesGenericas.findAll", query = "SELECT p FROM PartesGenericas p"),
    @NamedQuery(name = "PartesGenericas.findByIdParte", query = "SELECT p FROM PartesGenericas p WHERE p.idParte = :idParte"),
    @NamedQuery(name = "PartesGenericas.findByIdTipo", query = "SELECT p FROM PartesGenericas p WHERE p.idTipo = :idTipo"),
    @NamedQuery(name = "PartesGenericas.findByNombre", query = "SELECT p FROM PartesGenericas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PartesGenericas.findByDescripcion", query = "SELECT p FROM PartesGenericas p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PartesGenericas.findByValor", query = "SELECT p FROM PartesGenericas p WHERE p.valor = :valor")})
public class PartesGenericas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParte")
    private Integer idParte;
    @Column(name = "idTipo")
    private Integer idTipo;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "valor")
    private String valor;

    public PartesGenericas() {
    }

    public PartesGenericas(Integer idParte) {
        this.idParte = idParte;
    }

    public Integer getIdParte() {
        return idParte;
    }

    public void setIdParte(Integer idParte) {
        this.idParte = idParte;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParte != null ? idParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartesGenericas)) {
            return false;
        }
        PartesGenericas other = (PartesGenericas) object;
        if ((this.idParte == null && other.idParte != null) || (this.idParte != null && !this.idParte.equals(other.idParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.PartesGenericas[ idParte=" + idParte + " ]";
    }
    
}
