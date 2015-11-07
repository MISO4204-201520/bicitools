/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.cliente.ConsumeServicios;
import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.entity.Amigosredes;
import com.bicitools.mjson.IdEntidadJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.UsuarioJson;
import com.bicitools.mjson.UsuarioUsuarioJson;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ludwing
 */
@Stateless
public class AmigosRedesDAO implements AmigosRedesDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    @Override
    public RespuestaJson agregaAmigo(UsuarioUsuarioJson info) {
        RespuestaJson res;
        List a;
        int siga = 0;
        try {
            UsuarioJson j = new UsuarioJson();
            j.setUsuario(info.getUsuarioOrigen());
            res = ConsumeServicios.consume1("usuario", j);
            if (res.getCodigo() != 0) {
                siga = 1;
                res.setDescripcion(MessagesBicitools.getMessage("erUsuarioOrigen"));
            } else if (res.getDatos().isEmpty()) {
                res.setDescripcion(MessagesBicitools.getMessage("erUsuarioOrigen"));
                siga = 1;
            }
            j.setUsuario(info.getUsuarioDestino());
            res = ConsumeServicios.consume1("usuario", j);
            if (res.getCodigo() != 0) {
                res.setDescripcion(MessagesBicitools.getMessage("erUsuarioDestino"));
                siga = 2;
            } else if (res.getDatos().isEmpty()) {
                res.setDescripcion(MessagesBicitools.getMessage("erUsuarioDestino"));
                siga = 2;
            }
            if (siga == 0) {

                Amigosredes ar = new Amigosredes();
                Query query = em.createNamedQuery("Amigosredes.findByUsuarios");
                query.setParameter("usuario1", info.getUsuarioOrigen());
                query.setParameter("usuario2", info.getUsuarioDestino());
                a = query.getResultList();
                if (a.isEmpty()) {

                    ar.setEstado(1);
                    ar.setUsuario1(info.getUsuarioOrigen());
                    ar.setUsuario2(info.getUsuarioDestino());

                    em.persist(ar);

                    res = ConstruyeRespuesta.construyeRespuestaOk();
                } else {
                    ar = (Amigosredes) a.get(0);
                    ar.setEstado(1);
                    em.persist(ar);
                    res = ConstruyeRespuesta.construyeRespuestaOk();
                }
            }
        }catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        

        return res;

    }

    @Override
    public RespuestaJson actualizarAmigo(UsuarioUsuarioJson info, int i) {
        RespuestaJson res;
        try {
            Amigosredes ar = new Amigosredes();
            Query query = em.createNamedQuery("Amigosredes.findByUsuarios");
            query.setParameter("usuario1", info.getUsuarioOrigen());
            query.setParameter("usuario2", info.getUsuarioDestino());
            ar = (Amigosredes) query.getSingleResult();
            ar.setEstado(i);
            em.persist(ar);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return res;
    }

    @Override
    public RespuestaJson actualizarIdEntidad(IdEntidadJson info, int i) {
        RespuestaJson res;
        try {
            Amigosredes ar = new Amigosredes();
            Query query = em.createNamedQuery("Amigosredes.findByIdAmigosredes");
            query.setParameter("idAmigosredes", info.getIdEntidad());
            ar = (Amigosredes) query.getSingleResult();
            ar.setEstado(i);
            em.persist(ar);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return res;
    }

}
