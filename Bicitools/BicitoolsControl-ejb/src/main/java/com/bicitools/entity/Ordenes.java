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
 * @author jhony
 */
@Entity
@Table(name = "Ordenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenes.findAll", query = "SELECT o FROM Ordenes o"),
    @NamedQuery(name = "Ordenes.findByIdOrden", query = "SELECT o FROM Ordenes o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Ordenes.findByUsuario", query = "SELECT o FROM Ordenes o WHERE o.usuario = :usuario"),
    @NamedQuery(name = "Ordenes.findByIdMarco", query = "SELECT o FROM Ordenes o WHERE o.idMarco = :idMarco"),
    @NamedQuery(name = "Ordenes.findByIdTijera", query = "SELECT o FROM Ordenes o WHERE o.idTijera = :idTijera"),
    @NamedQuery(name = "Ordenes.findByIdFrenos", query = "SELECT o FROM Ordenes o WHERE o.idFrenos = :idFrenos"),
    @NamedQuery(name = "Ordenes.findByIrRuedas", query = "SELECT o FROM Ordenes o WHERE o.irRuedas = :irRuedas"),
    @NamedQuery(name = "Ordenes.findByIdManubrio", query = "SELECT o FROM Ordenes o WHERE o.idManubrio = :idManubrio"),
    @NamedQuery(name = "Ordenes.findByIdSillin", query = "SELECT o FROM Ordenes o WHERE o.idSillin = :idSillin"),
    @NamedQuery(name = "Ordenes.findByIdTuboSillin", query = "SELECT o FROM Ordenes o WHERE o.idTuboSillin = :idTuboSillin"),
    @NamedQuery(name = "Ordenes.findByIdCambios", query = "SELECT o FROM Ordenes o WHERE o.idCambios = :idCambios"),
    @NamedQuery(name = "Ordenes.findByIdPlatoDelantero", query = "SELECT o FROM Ordenes o WHERE o.idPlatoDelantero = :idPlatoDelantero"),
    @NamedQuery(name = "Ordenes.findByIdPlatoTrasero", query = "SELECT o FROM Ordenes o WHERE o.idPlatoTrasero = :idPlatoTrasero"),
    @NamedQuery(name = "Ordenes.findByIdPedales", query = "SELECT o FROM Ordenes o WHERE o.idPedales = :idPedales"),
    @NamedQuery(name = "Ordenes.findByIdCadena", query = "SELECT o FROM Ordenes o WHERE o.idCadena = :idCadena")})
public class Ordenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrden")
    private Integer idOrden;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "idMarco")
    private Integer idMarco;
    @Column(name = "idTijera")
    private Integer idTijera;
    @Column(name = "idFrenos")
    private Integer idFrenos;
    @Column(name = "irRuedas")
    private Integer irRuedas;
    @Column(name = "idManubrio")
    private Integer idManubrio;
    @Column(name = "idSillin")
    private Integer idSillin;
    @Column(name = "idTuboSillin")
    private Integer idTuboSillin;
    @Column(name = "idCambios")
    private Integer idCambios;
    @Column(name = "idPlatoDelantero")
    private Integer idPlatoDelantero;
    @Column(name = "idPlatoTrasero")
    private Integer idPlatoTrasero;
    @Column(name = "idPedales")
    private Integer idPedales;
    @Column(name = "idCadena")
    private Integer idCadena;
    @JoinColumn(name = "estado", referencedColumnName = "idEstado")
    @ManyToOne
    private EstadoOrden estado;

    public Ordenes() {
    }

    public Ordenes(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdMarco() {
        return idMarco;
    }

    public void setIdMarco(Integer idMarco) {
        this.idMarco = idMarco;
    }

    public Integer getIdTijera() {
        return idTijera;
    }

    public void setIdTijera(Integer idTijera) {
        this.idTijera = idTijera;
    }

    public Integer getIdFrenos() {
        return idFrenos;
    }

    public void setIdFrenos(Integer idFrenos) {
        this.idFrenos = idFrenos;
    }

    public Integer getIrRuedas() {
        return irRuedas;
    }

    public void setIrRuedas(Integer irRuedas) {
        this.irRuedas = irRuedas;
    }

    public Integer getIdManubrio() {
        return idManubrio;
    }

    public void setIdManubrio(Integer idManubrio) {
        this.idManubrio = idManubrio;
    }

    public Integer getIdSillin() {
        return idSillin;
    }

    public void setIdSillin(Integer idSillin) {
        this.idSillin = idSillin;
    }

    public Integer getIdTuboSillin() {
        return idTuboSillin;
    }

    public void setIdTuboSillin(Integer idTuboSillin) {
        this.idTuboSillin = idTuboSillin;
    }

    public Integer getIdCambios() {
        return idCambios;
    }

    public void setIdCambios(Integer idCambios) {
        this.idCambios = idCambios;
    }

    public Integer getIdPlatoDelantero() {
        return idPlatoDelantero;
    }

    public void setIdPlatoDelantero(Integer idPlatoDelantero) {
        this.idPlatoDelantero = idPlatoDelantero;
    }

    public Integer getIdPlatoTrasero() {
        return idPlatoTrasero;
    }

    public void setIdPlatoTrasero(Integer idPlatoTrasero) {
        this.idPlatoTrasero = idPlatoTrasero;
    }

    public Integer getIdPedales() {
        return idPedales;
    }

    public void setIdPedales(Integer idPedales) {
        this.idPedales = idPedales;
    }

    public Integer getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Integer idCadena) {
        this.idCadena = idCadena;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenes)) {
            return false;
        }
        Ordenes other = (Ordenes) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Ordenes[ idOrden=" + idOrden + " ]";
    }
    
}
