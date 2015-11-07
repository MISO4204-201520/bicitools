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
 * @author Ludwing
 */
@Entity
@Table(name = "serviciodomicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serviciodomicilio.findAll", query = "SELECT s FROM Serviciodomicilio s"),
    @NamedQuery(name = "Serviciodomicilio.findByIdServiciodomicilio", query = "SELECT s FROM Serviciodomicilio s WHERE s.idServiciodomicilio = :idServiciodomicilio"),
    @NamedQuery(name = "Serviciodomicilio.findByIdUsuario", query = "SELECT s FROM Serviciodomicilio s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "Serviciodomicilio.findByEmailUsuario", query = "SELECT s FROM Serviciodomicilio s WHERE s.emailUsuario = :emailUsuario"),
    @NamedQuery(name = "Serviciodomicilio.findByOrigen", query = "SELECT s FROM Serviciodomicilio s WHERE s.origen = :origen"),
    @NamedQuery(name = "Serviciodomicilio.findByDestino", query = "SELECT s FROM Serviciodomicilio s WHERE s.destino = :destino"),
    @NamedQuery(name = "Serviciodomicilio.findByIdDomiciliario", query = "SELECT s FROM Serviciodomicilio s WHERE s.idDomiciliario = :idDomiciliario"),
    @NamedQuery(name = "Serviciodomicilio.findByPuntos", query = "SELECT s FROM Serviciodomicilio s WHERE s.puntos = :puntos"),
    @NamedQuery(name = "Serviciodomicilio.findByComentario", query = "SELECT s FROM Serviciodomicilio s WHERE s.comentario = :comentario"),
    @NamedQuery(name = "Serviciodomicilio.findByTreal", query = "SELECT s FROM Serviciodomicilio s WHERE s.treal = :treal"),
    @NamedQuery(name = "Serviciodomicilio.findByTestimado", query = "SELECT s FROM Serviciodomicilio s WHERE s.testimado = :testimado"),
    @NamedQuery(name = "Serviciodomicilio.findByEstado", query = "SELECT s FROM Serviciodomicilio s WHERE s.estado = :estado")})
public class Serviciodomicilio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue()
    @Basic(optional = false)
    @Column(name = "id_serviciodomicilio")
    private Integer idServiciodomicilio;
    @Size(max = 45)
    @Column(name = "id_usuario")
    private String idUsuario;
    @Size(max = 200)
    @Column(name = "emailUsuario")
    private String emailUsuario;
    @Size(max = 200)
    @Column(name = "origen")
    private String origen;
    @Size(max = 200)
    @Column(name = "destino")
    private String destino;
    @Size(max = 45)
    @Column(name = "id_domiciliario")
    private String idDomiciliario;
    @Column(name = "puntos")
    private Integer puntos;
    @Size(max = 1000)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 5)
    @Column(name = "treal")
    private String treal;
    @Size(max = 5)
    @Column(name = "testimado")
    private String testimado;
    @Column(name = "estado")
    private Integer estado;

    public Serviciodomicilio() {
    }

    public Serviciodomicilio(Integer idServiciodomicilio) {
        this.idServiciodomicilio = idServiciodomicilio;
    }

    public Integer getIdServiciodomicilio() {
        return idServiciodomicilio;
    }

    public void setIdServiciodomicilio(Integer idServiciodomicilio) {
        this.idServiciodomicilio = idServiciodomicilio;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getIdDomiciliario() {
        return idDomiciliario;
    }

    public void setIdDomiciliario(String idDomiciliario) {
        this.idDomiciliario = idDomiciliario;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTreal() {
        return treal;
    }

    public void setTreal(String treal) {
        this.treal = treal;
    }

    public String getTestimado() {
        return testimado;
    }

    public void setTestimado(String testimado) {
        this.testimado = testimado;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiciodomicilio != null ? idServiciodomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serviciodomicilio)) {
            return false;
        }
        Serviciodomicilio other = (Serviciodomicilio) object;
        if ((this.idServiciodomicilio == null && other.idServiciodomicilio != null) || (this.idServiciodomicilio != null && !this.idServiciodomicilio.equals(other.idServiciodomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Serviciodomicilio[ idServiciodomicilio=" + idServiciodomicilio + " ]";
    }
    
}
