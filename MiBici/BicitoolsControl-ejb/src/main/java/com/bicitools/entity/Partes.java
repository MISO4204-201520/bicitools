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
@Table(name = "Partes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partes.findAll", query = "SELECT p FROM Partes p"),
    @NamedQuery(name = "Partes.findByIdParte", query = "SELECT p FROM Partes p WHERE p.idParte = :idParte"),
    @NamedQuery(name = "Partes.findByIdTipo", query = "SELECT p FROM Partes p WHERE p.idTipo = :idTipo"),
    @NamedQuery(name = "Partes.findByNombre", query = "SELECT p FROM Partes p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Partes.findByDescripcion", query = "SELECT p FROM Partes p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Partes.findByIdProveedor", query = "SELECT p FROM Partes p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Partes.findByValor", query = "SELECT p FROM Partes p WHERE p.valor = :valor"),
    @NamedQuery(name = "Partes.findByCantidad", query = "SELECT p FROM Partes p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Partes.findByImagen", query = "SELECT p FROM Partes p WHERE p.imagen = :imagen")})
public class Partes implements Serializable {
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
    @Column(name = "idProveedor")
    private Integer idProveedor;
    @Size(max = 45)
    @Column(name = "valor")
    private String valor;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 200)
    @Column(name = "imagen")
    private String imagen;

    public Partes() {
    }

    public Partes(Integer idParte) {
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
        if (!(object instanceof Partes)) {
            return false;
        }
        Partes other = (Partes) object;
        if ((this.idParte == null && other.idParte != null) || (this.idParte != null && !this.idParte.equals(other.idParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Partes[ idParte=" + idParte + " ]";
    }
    
}
