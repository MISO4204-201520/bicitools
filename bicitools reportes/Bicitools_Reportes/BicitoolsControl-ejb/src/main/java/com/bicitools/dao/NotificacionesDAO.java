/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.entity.Notificaciones;
import com.bicitools.mjson.DatosNotificacionesReportesJson;
import com.bicitools.mjson.InfoRepoCantNotificaJson;
import com.bicitools.mjson.InfoRepoGenJson;
import com.bicitools.mjson.RespuestaJson;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author jhony
 */
@Stateless
public class NotificacionesDAO implements NotificacionesDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    @Override
    public RespuestaJson obtenerNotoficacionesUsuario(InfoRepoCantNotificaJson info) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<DatosNotificacionesReportesJson> rta = new ArrayList<DatosNotificacionesReportesJson>();
        DatosNotificacionesReportesJson lista = new DatosNotificacionesReportesJson();
        RespuestaJson res = new RespuestaJson();
        Query query;
        Vector qresul;

        Date fechaUno, fechaDos;
        try {
            fechaUno = formatter.parse(info.getInicio());
            fechaDos = formatter.parse(info.getFin());
        } catch (ParseException ex) {
            String inputStr = "01-01-1900";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                fechaUno = dateFormat.parse(inputStr);
            } catch (Exception miex) {
                fechaUno = new java.util.Date();
            }
            fechaDos = new java.util.Date();
        }

        try{
        if (!"1".equals(info.getTipo()) && !"2".equals(info.getTipo()) && !"3".equals(info.getTipo())) {
            query = em.createNamedQuery("Notificaciones.obtenerNotiUsuario");
            query.setParameter("usuario", info.getUsuario());
            query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
            qresul = (Vector) query.getResultList();

            if (qresul.size() > 0) {

                lista.setCantidad(""+qresul.size());
                lista.setTipo("0");
                rta.add(lista);
                
                for(int i=1;i<4;i++){
                    DatosNotificacionesReportesJson listaTipo = new DatosNotificacionesReportesJson();
                    query = em.createNamedQuery("Notificaciones.obtenerNotiUsuarioTipo");
                    query.setParameter("usuario", info.getUsuario());
                    query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
                    query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
                    query.setParameter("tipoNotificacion", i);
                    qresul = (Vector) query.getResultList();
                    
                    if (qresul.size() > 0) {
                        listaTipo.setCantidad("" + qresul.size());
                        listaTipo.setTipo(""+i);
                        rta.add(listaTipo);
                    }
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();
                res.setDatos(rta);
            }
            else
            {
                res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos");
            }
        }
            
        else {
            query = em.createNamedQuery("Notificaciones.obtenerNotiUsuarioTipo");
            query.setParameter("usuario", info.getUsuario());
            query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
            query.setParameter("tipoNotificacion", Integer.parseInt(info.getTipo()));
            qresul = (Vector) query.getResultList();
            
            if (qresul.size() > 0) {

                lista.setCantidad("" + qresul.size());
                lista.setTipo(info.getTipo());
                rta.add(lista);
                res = ConstruyeRespuesta.construyeRespuestaOk();
                res.setDatos(rta);
            }
            else
            {
                res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos");
            }

        }
        }catch(Exception ex){
                res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public RespuestaJson registrarNotificacion(String usuario, String tipo) {
        RespuestaJson res;
        Notificaciones notiNueva = new Notificaciones();
        Date fecha = new java.util.Date();
        
        notiNueva.setIdNotificacion(0);
        notiNueva.setTipoNotificacion(Integer.parseInt(tipo));
        notiNueva.setUsuario(usuario);
        notiNueva.setFechaHora(fecha);

        try {
            em.persist(notiNueva);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }
    
    
}
