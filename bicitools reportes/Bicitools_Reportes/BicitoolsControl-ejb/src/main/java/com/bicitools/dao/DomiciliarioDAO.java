/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.entity.Domiciliario;
import com.bicitools.entity.Horariodomiciliario;
import com.bicitools.entity.Serviciodomicilio;
import com.bicitools.mjson.CalificacionServicioJson;
import com.bicitools.mjson.DatosCalificacionServicioJson;
import com.bicitools.mjson.InfoDomiciliarioJson;
import com.bicitools.mjson.InfoHorarioJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.UsuarioJson;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ludwing
 */
@Stateless
public class DomiciliarioDAO implements DomiciliarioDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    //LBD:
    //Logica de negocio para crear el domiciliario
    //En la capa de presentación se valido previamente todos lso campos.
    //el campo dia es un sring que contiene separados por comas los días de la smena que esta disponible el domiciliario
    @Override
    public RespuestaJson creaDomiciliario(InfoDomiciliarioJson info) {
        Domiciliario dom = new Domiciliario();
        Horariodomiciliario hd;
        RespuestaJson res = new RespuestaJson();
        Query query = em.createNamedQuery("Domiciliario.findByIdDomiciliario");
        query.setParameter("idDomiciliario", info.getUsuario());
        if (query.getResultList().isEmpty()) {
            try {
                dom.setIdDomiciliario(info.getUsuario());
                dom.setDireccion(info.getDireccion());
                dom.setEmail(info.getEmail());
                dom.setNombre("XXXXX");
                dom.setApellido("XXXXX");
                dom.setCelular("XXXX");
                dom.setEstado(0);
                em.persist(dom);  //persiste la tabla domiciliario
                for (InfoHorarioJson dato : info.getHorario()) {
                    StringTokenizer st = new StringTokenizer(dato.getDia(), ",");
                    while (st.hasMoreElements()) {
                        hd = new Horariodomiciliario();
                        hd.setDia(Integer.parseInt((String) st.nextElement()));
                        hd.setIdDomiciliario(dom);
                        hd.setInicio(dato.getInicio());
                        hd.setFin(dato.getFin());
                        em.persist(hd);  //persiste la tabla horriodomiciliario. La PK de la tabal es autogenerada. 
                    }
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();
            } catch (Exception ex) {
                res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

            }
        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erDomiciliarioCreado"));
        }

        return res;
    }

    //LBD:
    //Logica de negocio para actualizar la información del domiciliario
    //En la capa de presentación se valido previamente todos los campos.
    //el campo dia es un sring que contiene separados por comas los días de la smena que esta disponible el domiciliario
    @Override
    public RespuestaJson actualizaDomiciliario(InfoDomiciliarioJson info) {
        Domiciliario dom = new Domiciliario();
        Horariodomiciliario hd;
        RespuestaJson res = new RespuestaJson();
        Query query = em.createNamedQuery("Domiciliario.findByIdDomiciliario");
        query.setParameter("idDomiciliario", info.getUsuario());
        List dlist = query.getResultList();

        if (!dlist.isEmpty()) {
            try {
                dom = (Domiciliario) dlist.get(0);
                em.refresh(dom);

                for (Horariodomiciliario pd : dom.getHorariodomiciliarioCollection()) {
                    em.remove(pd);
                }

                dom.setDireccion(info.getDireccion());
                dom.setEmail(info.getEmail());
                em.persist(dom);  //persiste la tabla domiciliario
                for (InfoHorarioJson dato : info.getHorario()) {
                    StringTokenizer st = new StringTokenizer(dato.getDia(), ",");
                    while (st.hasMoreElements()) {
                        hd = new Horariodomiciliario();
                        hd.setDia(Integer.parseInt((String) st.nextElement()));
                        hd.setIdDomiciliario(dom);
                        hd.setInicio(dato.getInicio());
                        hd.setFin(dato.getFin());
                        em.persist(hd);  //persiste la tabla horriodomiciliario. La PK de la tabal es autogenerada. 
                    }
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();
            } catch (Exception ex) {
                res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

            }
        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erDomiciliarioNoCreado"));
        }

        return res;
    }

    //LBD:
    //Logica de negocio para actualizar la información del domiciliario
    //En la capa de presentación se valido previamente todos los campos.
    //el campo dia es un sring que contiene separados por comas los días de la smena que esta disponible el domiciliario
    @Override
    public RespuestaJson detalleDomiciliario(UsuarioJson info) {
        Domiciliario dom = new Domiciliario();

        RespuestaJson res = new RespuestaJson();
        Query query = em.createNamedQuery("Domiciliario.findByIdDomiciliario");
        query.setParameter("idDomiciliario", info.getUsuario());
        List dlist = query.getResultList();
        Query query2 = em.createNamedQuery("Serviciodomicilio.findByIdDomiciliario");
        query2.setParameter("idDomiciliario", info.getUsuario());
        List<Serviciodomicilio> dlist2 = query2.getResultList();

        CalificacionServicioJson sc;
        DatosCalificacionServicioJson sd = new DatosCalificacionServicioJson();
        ArrayList<CalificacionServicioJson> ar = new ArrayList<CalificacionServicioJson>();
        ArrayList rss = new ArrayList();

        if (!dlist.isEmpty()) {
            try {
                dom = (Domiciliario) dlist.get(0);
                sd.setNombre(dom.getNombre());
                sd.setApellido(dom.getApellido());
                sd.setMail(dom.getEmail());
                sd.setCelular(dom.getCelular());
                for (Serviciodomicilio sedo : dlist2) {
                    if (sedo.getEstado() == 3) {
                        sc = new CalificacionServicioJson();
                        sc.setComentarios(sedo.getComentario());
                        sc.setIdServicio(sedo.getIdServiciodomicilio());
                        sc.setPuntos(sedo.getPuntos());
                        ar.add(sc);
                    }

                }
                res = ConstruyeRespuesta.construyeRespuestaOk();

                sd.setPuntuacion(ar);
                rss.add(sd);
                res.setDatos(rss);
            } catch (Exception ex) {
                res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

            }
        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erDomiciliarioNoCreado"));
        }

        return res;
    }

}
