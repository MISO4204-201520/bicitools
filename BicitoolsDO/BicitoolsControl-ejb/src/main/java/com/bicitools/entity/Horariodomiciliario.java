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
 * @author Ludwing
 */
@Entity
@Table(name = "horariodomiciliario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horariodomiciliario.findAll", query = "SELECT h FROM Horariodomiciliario h"),
    @NamedQuery(name = "Horariodomiciliario.findByIdHorarioDomiciliario", query = "SELECT h FROM Horariodomiciliario h WHERE h.idHorarioDomiciliario = :idHorarioDomiciliario"),
    @NamedQuery(name = "Horariodomiciliario.findByDia", query = "SELECT h FROM Horariodomiciliario h WHERE h.dia = :dia"),
    @NamedQuery(name = "Horariodomiciliario.findByInicio", query = "SELECT h FROM Horariodomiciliario h WHERE h.inicio = :inicio"),
    @NamedQuery(name = "Horariodomiciliario.findByFin", query = "SELECT h FROM Horariodomiciliario h WHERE h.fin = :fin")})
public class Horariodomiciliario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario_domiciliario")
    private Integer idHorarioDomiciliario;
    @Column(name = "dia")
    private Integer dia;
    @Size(max = 5)
    @Column(name = "inicio")
    private String inicio;
    @Size(max = 5)
    @Column(name = "fin")
    private String fin;
    @JoinColumn(name = "id_domiciliario", referencedColumnName = "id_domiciliario")
    @ManyToOne
    private Domiciliario idDomiciliario;

    public Horariodomiciliario() {
    }

    public Horariodomiciliario(Integer idHorarioDomiciliario) {
        this.idHorarioDomiciliario = idHorarioDomiciliario;
    }

    public Integer getIdHorarioDomiciliario() {
        return idHorarioDomiciliario;
    }

    public void setIdHorarioDomiciliario(Integer idHorarioDomiciliario) {
        this.idHorarioDomiciliario = idHorarioDomiciliario;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Domiciliario getIdDomiciliario() {
        return idDomiciliario;
    }

    public void setIdDomiciliario(Domiciliario idDomiciliario) {
        this.idDomiciliario = idDomiciliario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioDomiciliario != null ? idHorarioDomiciliario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horariodomiciliario)) {
            return false;
        }
        Horariodomiciliario other = (Horariodomiciliario) object;
        if ((this.idHorarioDomiciliario == null && other.idHorarioDomiciliario != null) || (this.idHorarioDomiciliario != null && !this.idHorarioDomiciliario.equals(other.idHorarioDomiciliario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Horariodomiciliario[ idHorarioDomiciliario=" + idHorarioDomiciliario + " ]";
    }
    
}
