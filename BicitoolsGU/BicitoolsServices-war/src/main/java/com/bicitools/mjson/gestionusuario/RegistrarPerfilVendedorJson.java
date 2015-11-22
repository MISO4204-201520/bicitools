/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.mjson.gestionusuario;

/**
 *
 * @author TaidyJohana
 */
public class RegistrarPerfilVendedorJson {
    Integer id_usuario;

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
    String nombre_establecimiento,direccion_establecimiento,telefono_establecimiento,celular_establecimiento,
            correo_establecimiento,nit_establecimiento,foto_establecimiento;

    public RegistrarPerfilVendedorJson() {
    }

    @Override
    public String toString() {
        return "RegistrarPerfilVendedorJson{" + "idUsuario=" + id_usuario + ", nombre_establecimiento=" + nombre_establecimiento + ", direccion_establecimiento=" + direccion_establecimiento + ", telefono_establecimiento=" + telefono_establecimiento + ", celular_establecimiento=" + celular_establecimiento + ", correo_establecimiento=" + correo_establecimiento + ", nit_establecimiento=" + nit_establecimiento + ", foto_establecimiento=" + foto_establecimiento + '}';
    }

   

    public String getNombre_establecimiento() {
        return nombre_establecimiento;
    }

    public void setNombre_establecimiento(String nombre_establecimiento) {
        this.nombre_establecimiento = nombre_establecimiento;
    }

    public String getDireccion_establecimiento() {
        return direccion_establecimiento;
    }

    public void setDireccion_establecimiento(String direccion_establecimiento) {
        this.direccion_establecimiento = direccion_establecimiento;
    }

    public String getTelefono_establecimiento() {
        return telefono_establecimiento;
    }

    public void setTelefono_establecimiento(String telefono_establecimiento) {
        this.telefono_establecimiento = telefono_establecimiento;
    }

    public String getCelular_establecimiento() {
        return celular_establecimiento;
    }

    public void setCelular_establecimiento(String celular_establecimiento) {
        this.celular_establecimiento = celular_establecimiento;
    }

    public String getCorreo_establecimiento() {
        return correo_establecimiento;
    }

    public void setCorreo_establecimiento(String correo_establecimiento) {
        this.correo_establecimiento = correo_establecimiento;
    }

    public String getNit_establecimiento() {
        return nit_establecimiento;
    }

    public void setNit_establecimiento(String nit_establecimiento) {
        this.nit_establecimiento = nit_establecimiento;
    }

    public String getFoto_establecimiento() {
        return foto_establecimiento;
    }

    public void setFoto_establecimiento(String foto_establecimiento) {
        this.foto_establecimiento = foto_establecimiento;
    }
    
}