/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ludwing
 */
@Entity
@Table(name = "domiciliario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domiciliario.findAll", query = "SELECT d FROM Domiciliario d"),
    @NamedQuery(name = "Domiciliario.findByIdDomiciliario", query = "SELECT d FROM Domiciliario d WHERE d.idDomiciliario = :idDomiciliario"),
    @NamedQuery(name = "Domiciliario.findByDireccion", query = "SELECT d FROM Domiciliario d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Domiciliario.findByEmail", query = "SELECT d FROM Domiciliario d WHERE d.email = :email"),
    @NamedQuery(name = "Domiciliario.findByEstado", query = "SELECT d FROM Domiciliario d WHERE d.estado = :estado")})
public class Domiciliario implements Serializable {
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 16)
    @Column(name = "celular")
    private String celular;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id_domiciliario")
    private String idDomiciliario;
    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(mappedBy = "idDomiciliario")
    private Collection<Horariodomiciliario> horariodomiciliarioCollection;

    public Domiciliario() {
    }

    public Domiciliario(String idDomiciliario) {
        this.idDomiciliario = idDomiciliario;
    }

    public String getIdDomiciliario() {
        return idDomiciliario;
    }

    public void setIdDomiciliario(String idDomiciliario) {
        this.idDomiciliario = idDomiciliario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Horariodomiciliario> getHorariodomiciliarioCollection() {
        return horariodomiciliarioCollection;
    }

    public void setHorariodomiciliarioCollection(Collection<Horariodomiciliario> horariodomiciliarioCollection) {
        this.horariodomiciliarioCollection = horariodomiciliarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDomiciliario != null ? idDomiciliario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domiciliario)) {
            return false;
        }
        Domiciliario other = (Domiciliario) object;
        if ((this.idDomiciliario == null && other.idDomiciliario != null) || (this.idDomiciliario != null && !this.idDomiciliario.equals(other.idDomiciliario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Domiciliario[ idDomiciliario=" + idDomiciliario + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
}
