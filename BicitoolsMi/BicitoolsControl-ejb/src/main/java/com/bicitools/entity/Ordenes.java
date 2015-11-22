/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Ordenes.findByIdRuedas", query = "SELECT o FROM Ordenes o WHERE o.idRuedas = :idRuedas"),
    @NamedQuery(name = "Ordenes.findByIdManubrio", query = "SELECT o FROM Ordenes o WHERE o.idManubrio = :idManubrio"),
    @NamedQuery(name = "Ordenes.findByIdSillin", query = "SELECT o FROM Ordenes o WHERE o.idSillin = :idSillin"),
    @NamedQuery(name = "Ordenes.findByIdCambios", query = "SELECT o FROM Ordenes o WHERE o.idCambios = :idCambios"),
    @NamedQuery(name = "Ordenes.findByIdPlatoDelantero", query = "SELECT o FROM Ordenes o WHERE o.idPlatoDelantero = :idPlatoDelantero"),
    @NamedQuery(name = "Ordenes.findByIdPlatoTrasero", query = "SELECT o FROM Ordenes o WHERE o.idPlatoTrasero = :idPlatoTrasero"),
    @NamedQuery(name = "Ordenes.findByIdPedales", query = "SELECT o FROM Ordenes o WHERE o.idPedales = :idPedales"),
    @NamedQuery(name = "Ordenes.findByIdCadena", query = "SELECT o FROM Ordenes o WHERE o.idCadena = :idCadena"),
    @NamedQuery(name = "Ordenes.findByEstado", query = "SELECT o FROM Ordenes o WHERE o.estado = :estado"),
    @NamedQuery(name = "Ordenes.findByValor", query = "SELECT o FROM Ordenes o WHERE o.valor = :valor"),
    @NamedQuery(name = "Ordenes.findByFechaHora", query = "SELECT o FROM Ordenes o WHERE o.fechaHora = :fechaHora")})
public class Ordenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOrden")
    private Integer idOrden;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 10)
    @Column(name = "idMarco")
    private String idMarco;
    @Size(max = 10)
    @Column(name = "idTijera")
    private String idTijera;
    @Size(max = 10)
    @Column(name = "idFrenos")
    private String idFrenos;
    @Size(max = 10)
    @Column(name = "idRuedas")
    private String idRuedas;
    @Size(max = 10)
    @Column(name = "idManubrio")
    private String idManubrio;
    @Size(max = 10)
    @Column(name = "idSillin")
    private String idSillin;
    @Size(max = 10)
    @Column(name = "idCambios")
    private String idCambios;
    @Size(max = 10)
    @Column(name = "idPlatoDelantero")
    private String idPlatoDelantero;
    @Size(max = 10)
    @Column(name = "idPlatoTrasero")
    private String idPlatoTrasero;
    @Size(max = 10)
    @Column(name = "idPedales")
    private String idPedales;
    @Size(max = 10)
    @Column(name = "idCadena")
    private String idCadena;
    @Size(max = 2)
    @Column(name = "estado")
    private String estado;
    @Size(max = 10)
    @Column(name = "valor")
    private String valor;
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

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

    public String getIdMarco() {
        return idMarco;
    }

    public void setIdMarco(String idMarco) {
        this.idMarco = idMarco;
    }

    public String getIdTijera() {
        return idTijera;
    }

    public void setIdTijera(String idTijera) {
        this.idTijera = idTijera;
    }

    public String getIdFrenos() {
        return idFrenos;
    }

    public void setIdFrenos(String idFrenos) {
        this.idFrenos = idFrenos;
    }

    public String getIdRuedas() {
        return idRuedas;
    }

    public void setIdRuedas(String idRuedas) {
        this.idRuedas = idRuedas;
    }

    public String getIdManubrio() {
        return idManubrio;
    }

    public void setIdManubrio(String idManubrio) {
        this.idManubrio = idManubrio;
    }

    public String getIdSillin() {
        return idSillin;
    }

    public void setIdSillin(String idSillin) {
        this.idSillin = idSillin;
    }

    public String getIdCambios() {
        return idCambios;
    }

    public void setIdCambios(String idCambios) {
        this.idCambios = idCambios;
    }

    public String getIdPlatoDelantero() {
        return idPlatoDelantero;
    }

    public void setIdPlatoDelantero(String idPlatoDelantero) {
        this.idPlatoDelantero = idPlatoDelantero;
    }

    public String getIdPlatoTrasero() {
        return idPlatoTrasero;
    }

    public void setIdPlatoTrasero(String idPlatoTrasero) {
        this.idPlatoTrasero = idPlatoTrasero;
    }

    public String getIdPedales() {
        return idPedales;
    }

    public void setIdPedales(String idPedales) {
        this.idPedales = idPedales;
    }

    public String getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(String idCadena) {
        this.idCadena = idCadena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
