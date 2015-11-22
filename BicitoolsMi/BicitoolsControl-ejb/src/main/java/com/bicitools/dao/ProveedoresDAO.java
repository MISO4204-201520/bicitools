/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.entity.Proveedores;
import com.bicitools.mjson.DatosSalidaProveedorJson;
import com.bicitools.mjson.InfoInsertaProvJson;
import com.bicitools.mjson.InfoRolProveedorJson;
import com.bicitools.mjson.RespuestaJson;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhony
 */
@Stateless
public class ProveedoresDAO implements ProveedoresDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;
    
    @Override
    public int obtenerIdProveedor(String usuario){
        
        int idRes;
        Query query = em.createNamedQuery("Proveedores.findByUsuario");
        query.setParameter("usuario", usuario);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            Proveedores miUsuario = (Proveedores) qresul.get(0);
            idRes = miUsuario.getIdProveedor();
            
        }
        else
            idRes = -1;
            
        return idRes;
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public RespuestaJson verificarProveedor(InfoRolProveedorJson info) {
        
        RespuestaJson res;
        ArrayList salida=new ArrayList();
        Proveedores proveedor = new Proveedores();
        DatosSalidaProveedorJson valido= new DatosSalidaProveedorJson();
        int idRes = obtenerIdProveedor(info.getUsuario());
        
        if (idRes != -1) {
            res = ConstruyeRespuesta.construyeRespuestaOk();
            valido.setEsProveedor("true");
            salida.add(valido);
            res.setDatos(salida);
        } else {
            res = ConstruyeRespuesta.construyeRespuestaOk();
            valido.setEsProveedor("false");
            salida.add(valido);
            res.setDatos(salida);
            //res = ConstruyeRespuesta.construyeRespuestaFalla("No es un Proveedor de Bicitools");
        }
        return res;
    }
    
    @Override
    public RespuestaJson crearProveedor(InfoInsertaProvJson info) {
        
        RespuestaJson res;
        Proveedores proveedor = new Proveedores();
        
        proveedor.setIdProveedor(0);
        proveedor.setCorreo(info.getCorreo());
        proveedor.setDireccion(info.getDireccionOficina());
        proveedor.setTelefono(info.getTelefono());
        proveedor.setUsuario(info.getUsuario());
        
        try {
            em.persist(proveedor);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }
}
