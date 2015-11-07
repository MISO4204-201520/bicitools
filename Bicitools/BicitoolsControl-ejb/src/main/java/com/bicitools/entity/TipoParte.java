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
@Table(name = "TipoParte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoParte.findAll", query = "SELECT t FROM TipoParte t"),
    @NamedQuery(name = "TipoParte.findByIdTipoParte", query = "SELECT t FROM TipoParte t WHERE t.idTipoParte = :idTipoParte"),
    @NamedQuery(name = "TipoParte.findByNombre", query = "SELECT t FROM TipoParte t WHERE t.nombre = :nombre")})
public class TipoParte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoParte")
    private Integer idTipoParte;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;

    public TipoParte() {
    }

    public TipoParte(Integer idTipoParte) {
        this.idTipoParte = idTipoParte;
    }

    public Integer getIdTipoParte() {
        return idTipoParte;
    }

    public void setIdTipoParte(Integer idTipoParte) {
        this.idTipoParte = idTipoParte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoParte != null ? idTipoParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoParte)) {
            return false;
        }
        TipoParte other = (TipoParte) object;
        if ((this.idTipoParte == null && other.idTipoParte != null) || (this.idTipoParte != null && !this.idTipoParte.equals(other.idTipoParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.TipoParte[ idTipoParte=" + idTipoParte + " ]";
    }
    
}
