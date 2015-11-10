/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.entity.Usuario;
import com.bicitools.entity.Vendedor;
import javax.ejb.Local;

/**
 *
 * @author TaidyJohana
 */
@Local
public interface VendedorDAOLocal {
    
    public String registrarPerfilVendedor(Integer id_usuario, String nombre_establecimiento,
    String direccion_establecimiento, String telefono_establecimiento, String celular_establecimiento,
    String correo_establecimiento, String nit_establecimiento, String foto_establecimiento);

public String actualizarPerfilVendedor(
     Integer id_usuario, String nombre_establecimiento,
    String direccion_establecimiento, String telefono_establecimiento, String celular_establecimiento,
    String correo_establecimiento, String nit_establecimiento, String foto_establecimiento
);
     public Vendedor obtenerDetallesUsuario(Integer id_usuario);
}
