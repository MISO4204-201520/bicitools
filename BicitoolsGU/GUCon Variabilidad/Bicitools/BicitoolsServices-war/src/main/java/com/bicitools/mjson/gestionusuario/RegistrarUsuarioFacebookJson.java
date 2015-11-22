package com.bicitools.mjson.gestionusuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author TaidyJohana
 */
public class RegistrarUsuarioFacebookJson {
    
    Integer numeroIdentificacion,tipoIdentificacion,  tipoPerfil,  genero;
    
    String nombres,  apellidos,  foto,  correo, direccionCasa,  direccionTrabajo,  fechaNacimiento,
    telefonoFijo,  telefonoMovil,  facebookUser, facebookToken, twitterUser, usuario,  contrasenia;

    public RegistrarUsuarioFacebookJson() {
    }

    public Integer getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Integer numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Integer getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Integer tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccionCasa() {
        return direccionCasa;
    }

    public void setDireccionCasa(String direccionCasa) {
        this.direccionCasa = direccionCasa;
    }

    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getFacebookUser() {
        return facebookUser;
    }

    public void setFacebookUser(String facebookUser) {
        this.facebookUser = facebookUser;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public String getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(String twitterUser) {
        this.twitterUser = twitterUser;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    
    @Override
    public String toString() {
        return "RegistrarUsuarioFacebookJson{" + "numeroIdentificacion=" + numeroIdentificacion + ", tipoIdentificacion=" + tipoIdentificacion + ", tipoPerfil=" + tipoPerfil + ", genero=" + genero + ", nombres=" + nombres + ", apellidos=" + apellidos + ", foto=" + foto + ", correo=" + correo + ", direccionCasa=" + direccionCasa + ", direccionTrabajo=" + direccionTrabajo + ", fechaNacimiento=" + fechaNacimiento + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", facebookUser=" + facebookUser + ", facebookToken=" + facebookToken + ", twitterUser=" + twitterUser + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
    


}
