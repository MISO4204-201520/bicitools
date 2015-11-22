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
 * @author Ludwing
 */
@Entity
@Table(name = "mensajesusuariof")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajesusuariof.findAll", query = "SELECT m FROM Mensajesusuariof m"),
    @NamedQuery(name = "Mensajesusuariof.findByIdmensajeschat", query = "SELECT m FROM Mensajesusuariof m WHERE m.idmensajeschat = :idmensajeschat"),
    @NamedQuery(name = "Mensajesusuariof.findByFecha", query = "SELECT m FROM Mensajesusuariof m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mensajesusuariof.findByMensaje", query = "SELECT m FROM Mensajesusuariof m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensajesusuariof.findByUsuario", query = "SELECT m FROM Mensajesusuariof m WHERE m.usuario = :usuario"),
    @NamedQuery(name = "Mensajesusuariof.findByRecibido", query = "SELECT m FROM Mensajesusuariof m WHERE m.recibido = :recibido"),
    @NamedQuery(name = "Mensajesusuariof.findByIdchat", query = "SELECT m FROM Mensajesusuariof m WHERE m.idchat = :idchat"),
    @NamedQuery(name = "Mensajesusuariof.findByNombre", query = "SELECT m FROM Mensajesusuariof m WHERE m.nombre = :nombre")})
public class Mensajesusuariof implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmensajeschat")
    private int idmensajeschat;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 500)
    @Column(name = "mensaje")
    private String mensaje;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "recibido")
    private Integer recibido;
    @Column(name = "idchat")
    private Integer idchat;
    @Size(max = 500)
    @Column(name = "nombre")
    private String nombre;

    public Mensajesusuariof() {
    }

    public int getIdmensajeschat() {
        return idmensajeschat;
    }

    public void setIdmensajeschat(int idmensajeschat) {
        this.idmensajeschat = idmensajeschat;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getRecibido() {
        return recibido;
    }

    public void setRecibido(Integer recibido) {
        this.recibido = recibido;
    }

    public Integer getIdchat() {
        return idchat;
    }

    public void setIdchat(Integer idchat) {
        this.idchat = idchat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
