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
 * @author TaidyJohana
 */
@Entity
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v"),
    @NamedQuery(name = "Vendedor.findByIdEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.idEstablecimiento = :idEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByNombreEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.nombreEstablecimiento = :nombreEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByDireccionEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.direccionEstablecimiento = :direccionEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByTelefonoEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.telefonoEstablecimiento = :telefonoEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByCelularEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.celularEstablecimiento = :celularEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByCorreoEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.correoEstablecimiento = :correoEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByNitEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.nitEstablecimiento = :nitEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByFotoEstablecimiento", query = "SELECT v FROM Vendedor v WHERE v.fotoEstablecimiento = :fotoEstablecimiento"),
    @NamedQuery(name = "Vendedor.findByIdUsuario", query = "SELECT v FROM Vendedor v WHERE v.idUsuario = :idUsuario")})
public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_establecimiento")
    private Integer idEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_establecimiento")
    private String nombreEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion_establecimiento")
    private String direccionEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefono_establecimiento")
    private String telefonoEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "celular_establecimiento")
    private String celularEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_establecimiento")
    private String correoEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nit_establecimiento")
    private String nitEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "foto_establecimiento")
    private String fotoEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;

    public Vendedor() {
    }

    public Vendedor(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public Vendedor(Integer idEstablecimiento, String nombreEstablecimiento, String direccionEstablecimiento, String telefonoEstablecimiento, String celularEstablecimiento, String correoEstablecimiento, String nitEstablecimiento, String fotoEstablecimiento, int idUsuario) {
        this.idEstablecimiento = idEstablecimiento;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.direccionEstablecimiento = direccionEstablecimiento;
        this.telefonoEstablecimiento = telefonoEstablecimiento;
        this.celularEstablecimiento = celularEstablecimiento;
        this.correoEstablecimiento = correoEstablecimiento;
        this.nitEstablecimiento = nitEstablecimiento;
        this.fotoEstablecimiento = fotoEstablecimiento;
        this.idUsuario = idUsuario;
    }

    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getDireccionEstablecimiento() {
        return direccionEstablecimiento;
    }

    public void setDireccionEstablecimiento(String direccionEstablecimiento) {
        this.direccionEstablecimiento = direccionEstablecimiento;
    }

    public String getTelefonoEstablecimiento() {
        return telefonoEstablecimiento;
    }

    public void setTelefonoEstablecimiento(String telefonoEstablecimiento) {
        this.telefonoEstablecimiento = telefonoEstablecimiento;
    }

    public String getCelularEstablecimiento() {
        return celularEstablecimiento;
    }

    public void setCelularEstablecimiento(String celularEstablecimiento) {
        this.celularEstablecimiento = celularEstablecimiento;
    }

    public String getCorreoEstablecimiento() {
        return correoEstablecimiento;
    }

    public void setCorreoEstablecimiento(String correoEstablecimiento) {
        this.correoEstablecimiento = correoEstablecimiento;
    }

    public String getNitEstablecimiento() {
        return nitEstablecimiento;
    }

    public void setNitEstablecimiento(String nitEstablecimiento) {
        this.nitEstablecimiento = nitEstablecimiento;
    }

    public String getFotoEstablecimiento() {
        return fotoEstablecimiento;
    }

    public void setFotoEstablecimiento(String fotoEstablecimiento) {
        this.fotoEstablecimiento = fotoEstablecimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstablecimiento != null ? idEstablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        if ((this.idEstablecimiento == null && other.idEstablecimiento != null) || (this.idEstablecimiento != null && !this.idEstablecimiento.equals(other.idEstablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Vendedor[ idEstablecimiento=" + idEstablecimiento + " ]";
    }
    
}
