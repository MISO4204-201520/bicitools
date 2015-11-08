/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.entity.Partes;
import com.bicitools.entity.Proveedores;
import com.bicitools.mjson.InfoInsertaProductoJson;
import com.bicitools.mjson.InfoConsultaPartesJson;
import com.bicitools.mjson.InfoRespuestaPartesJson;
import com.bicitools.mjson.RespuestaJson;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhony
 */
@Stateless
public class PartesDAO implements PartesDAOLocal {

    @EJB
    //public RutasFacadeImpl rutasFacade;
    ProveedoresDAOLocal proveedorDAO;

    @EJB
    //public RutasFacadeImpl rutasFacade;
    TipoParteDAOLocal tipoPartesDAO;

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    @Override
    public RespuestaJson crearParte(InfoInsertaProductoJson info) {
        RespuestaJson res;
        int idProveedor, idTipoParte;
        res = new RespuestaJson();

        Partes parte = new Partes();

        parte.setIdParte(0);
        try {
            parte.setCantidad(Integer.parseInt(info.getCantidad()));
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
            parte.setCantidad(0);
        }

        parte.setNombre(info.getNombre());
        parte.setDescripcion(info.getDescripcion());

        idProveedor = proveedorDAO.obtenerIdProveedor(info.getUsuario());
        if (idProveedor != -1) {
            parte.setIdProveedor(idProveedor);
        } else {
            parte.setIdProveedor(0);
        }

        idTipoParte = tipoPartesDAO.obtenerIdTipoParte(info.getTipoParte());
        if (idTipoParte != -1) {
            parte.setIdTipo(idTipoParte);
        } else {
            parte.setIdTipo(0);
        }

        parte.setValor(info.getPrecio());

        try {
            em.persist(parte);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    @Override
    public RespuestaJson obtenerPartesPorTipo(InfoConsultaPartesJson info) {

        int idTipoParte;
        RespuestaJson res = null;
        
        Query query = em.createNamedQuery("Partes.findByIdTipo");

        idTipoParte = tipoPartesDAO.obtenerIdTipoParte(info.getTipoParte());
        if (idTipoParte != -1) {
            query.setParameter("idTipo", idTipoParte);
            Vector qresul = (Vector) query.getResultList();
            
            ArrayList arr = new ArrayList();

            if (qresul.size() > 0) {

                for (int i = 0; i < qresul.size(); i++) {
                    Partes miParte = (Partes) qresul.get(i);
                    InfoRespuestaPartesJson parteSalida = new InfoRespuestaPartesJson();

                    parteSalida.setIdParte(miParte.getIdParte().toString());
                    parteSalida.setNombre(miParte.getNombre());
                    parteSalida.setDescripcion(miParte.getDescripcion());
                    parteSalida.setImagen(null);
                    parteSalida.setValor(miParte.getValor());
                    arr.add(parteSalida);
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();
                res.setDatos(arr);

            //int idRes = miUsuario.getIdProveedor();
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla("no hay partes para este tipo");
            }
        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla("tipo incorrecto");
        }

        return res;
    }

    @Override
    public Partes obtenerInfoParte(int idParte) {

        RespuestaJson res = null;
        Partes miParte = null;
        
        Query query = em.createNamedQuery("Partes.findByIdParte");

        if (idParte != -1) {
            query.setParameter("idParte", idParte);
            ArrayList qresul = (ArrayList) query.getResultList();
            
            ArrayList arr = new ArrayList();

            if (qresul.size() > 0) {

                    miParte = (Partes) qresul.get(0);

            //int idRes = miUsuario.getIdProveedor();
            } else {
                System.out.println("no hay partes para este tipo "+idParte);
            }
        }
        return miParte;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
