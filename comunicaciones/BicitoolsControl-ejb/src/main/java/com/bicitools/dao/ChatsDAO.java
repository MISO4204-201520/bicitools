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
import com.bicitools.entity.Chat;
import com.bicitools.entity.Mensajeschat;
import com.bicitools.entity.Mensajesusuariof;
import com.bicitools.entity.Usuarioschat;
import com.bicitools.mjson.ChatInfoJson;
import com.bicitools.mjson.ChatMensajeInfo;
import com.bicitools.mjson.ChatUsuarioInfo;
import com.bicitools.mjson.ChatsMensajesUsuarioJson;
import com.bicitools.mjson.MensajeChatJson;
import com.bicitools.mjson.NuevoChatJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.UsuarioInfoConJson;
import com.bicitools.mjson.UsuarioJson;
import com.bicitools.mjson.UsuariosAmigosJson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ludwing
 */
@Stateless
public class ChatsDAO implements ChatsDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    @Override
    public RespuestaJson creaChat(NuevoChatJson info) {
        RespuestaJson res;
        Usuarioschat uc;
        String siga = null;
        UsuarioJson uj = new UsuarioJson();
        ArrayList<Usuarioschat> a = new ArrayList<Usuarioschat>();
        ArrayList<String> usus = info.getUsuarios();

        try {
            if (usus.size() > 0) {
                Chat ch = new Chat();
                ch.setNombre(info.getNombre());

                for (String us : usus) {

                    uj.setUsuario(us);
                    res = ConsumeServicios.consume1("usuario", uj);
                    if (res.getCodigo() != 0) {
                        siga = us;
                    } else {
                        uc = new Usuarioschat();
                        uc.setUsuario(us);
                        uc.setIdchat(ch);
                        a.add(uc);
                    }

                }
                if (siga == null) {
                    ch.setUsuarioschatList(a);
                    em.persist(ch);
                    res = ConstruyeRespuesta.construyeRespuestaOk();
                } else {
                    res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erUsuarioVar", siga));

                }

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erNumUsuario"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

        }
        return res;
    }

    @Override
    public RespuestaJson enviarMensaje(MensajeChatJson info) {
        RespuestaJson res;
        Mensajeschat mc;

        try {
            Query qy = em.createNamedQuery("Chat.findByIdchat");
            qy.setParameter("idchat", info.getIdChat());
            if (!qy.getResultList().isEmpty()) {

                mc = new Mensajeschat();
                mc.setFecha(new Date());
                mc.setMensaje(info.getMensaje());
                mc.setUsuario(info.getUsuario());
                mc.setIdchat(Integer.parseInt("" + info.getIdChat()));
                mc.setRecibido(1);
                em.persist(mc);
                res = ConstruyeRespuesta.construyeRespuestaOk();
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erIdChat"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

        }
        return res;
    }

    @Override
    public RespuestaJson obtenerChatPorId(String idChat) {
        RespuestaJson res, tmp2;
        ChatInfoJson chi = new ChatInfoJson();
        ChatMensajeInfo cmi;
        ChatUsuarioInfo cui;
        Chat ch;
        Mensajeschat mc;
        LinkedHashMap iuc;
        ArrayList dat;
        ArrayList<ChatMensajeInfo> lmi;
        ArrayList<ChatUsuarioInfo> lui;
        UsuarioJson j = new UsuarioJson();
        String estado, foto, nombre;

        try {
            lmi = new ArrayList<ChatMensajeInfo>();
            lui = new ArrayList<ChatUsuarioInfo>();
            Query qy = em.createNamedQuery("Chat.findByIdchat");
            qy.setParameter("idchat", Integer.parseInt(idChat));
            if (!qy.getResultList().isEmpty()) {
                ch = (Chat) qy.getSingleResult();
                chi.setNombreChat(ch.getNombre());
                chi.setUsuarioChatId(Integer.parseInt(idChat));
                estado = "estado";
                foto = "";
                nombre = "";
                for (Usuarioschat uc : ch.getUsuarioschatList()) {

                    j.setUsuario(uc.getUsuario());
                    tmp2 = ConsumeServicios.consume1("usuario", j);
                    if (tmp2.getCodigo() == 0) {

                        if (!tmp2.getDatos().isEmpty()) {
                            dat = tmp2.getDatos();
                            iuc = (LinkedHashMap) dat.get(0);
                            foto = (String) iuc.get("foto");
                            nombre = (String) iuc.get("nombres") + " " + (String) iuc.get("apellidos");
                        }
                    }

                    cui = new ChatUsuarioInfo();
                    cui.setEstado(estado);
                    cui.setFoto(foto);
                    cui.setNickname(uc.getUsuario());
                    cui.setNombre(nombre);
                    lui.add(cui);
                }

                qy = em.createNamedQuery("Mensajeschat.findByIdchat");
                qy.setParameter("idchat", Integer.parseInt(idChat));

                for (Object mc2 : qy.getResultList()) {
                    mc = (Mensajeschat) mc2;
                    cmi = new ChatMensajeInfo();
                    cmi.setNombreUsuario(mc.getUsuario());
                    cmi.setFecha(mc.getFecha().getTime());
                    cmi.setRecibido(true);
                    cmi.setTexto(mc.getMensaje());
                    lmi.add(cmi);
                }

                chi.setMensajes(lmi);
                chi.setUsuarios(lui);

                res = ConstruyeRespuesta.construyeRespuestaOk();
                ArrayList ares = new ArrayList();
                ares.add(chi);
                res.setDatos(ares);
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erIdChat"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

        }
        return res;
    }
    
    

    @Override
    public RespuestaJson obtenerChatsUsuario(String usuario) {
        RespuestaJson res;
        Mensajesusuariof mc;
        ArrayList<ChatsMensajesUsuarioJson> lmi;
        ChatsMensajesUsuarioJson tmp;

        try {
            lmi = new ArrayList<ChatsMensajesUsuarioJson>();

            Query qy = em.createNamedQuery("Mensajesusuariof.findByUsuario");
            qy.setParameter("usuario", usuario);
            if (!qy.getResultList().isEmpty()) {
                for (Object mc2 : qy.getResultList()) {

                    mc = (Mensajesusuariof) mc2;
                    tmp = new ChatsMensajesUsuarioJson();
                    tmp.setChatId(mc.getIdchat());
                    tmp.setFechaUltimoMensaje(mc.getFecha().getTime());
                    tmp.setFoto("foto");
                    tmp.setNombre(mc.getNombre());
                    if (mc.getRecibido()==1)
                        tmp.setRecibido(true);
                    else
                        tmp.setRecibido(false);
                    tmp.setUltimoMensaje(mc.getMensaje());
                    lmi.add(tmp);
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();
                res.setDatos(lmi);
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erUsuarioChat"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return res;
    }

    @Override
    public RespuestaJson obtenerSolicitudesAmistad(String usuario, int estado) {
        RespuestaJson res, tmp2;
        String foto, nombre;
        Amigosredes am;
        UsuariosAmigosJson ua;        
        UsuarioJson j = new UsuarioJson();
        ArrayList<UsuariosAmigosJson> lmi;
        ArrayList dat;
        LinkedHashMap iuc;
        try {
            lmi = new ArrayList<UsuariosAmigosJson>();
            nombre="";
            foto="";
            Query qy = em.createNamedQuery("Amigosredes.findByOrigenEstado");
            qy.setParameter("usuario1", usuario);
            qy.setParameter("estado", estado);
            if (!qy.getResultList().isEmpty()) {
                
                for (Object uc : qy.getResultList()) {
                    am= (Amigosredes) uc;
                    
                    j.setUsuario(am.getUsuario2());
                    tmp2 = ConsumeServicios.consume1("usuario", j);
                    if (tmp2.getCodigo() == 0) {

                        if (!tmp2.getDatos().isEmpty()) {
                            dat = tmp2.getDatos();
                            iuc = (LinkedHashMap) dat.get(0);
                            foto = (String) iuc.get("foto");
                            nombre = (String) iuc.get("nombres") + " " + (String) iuc.get("apellidos");
                        }
                        ua=new UsuariosAmigosJson();
                        ua.setAmistadId(am.getIdAmigosredes());
                        ua.setFoto(foto);
                        ua.setNickname(am.getUsuario2());
                        ua.setNombre(nombre);
                        lmi.add(ua);
                    }
                }

                res = ConstruyeRespuesta.construyeRespuestaOk();
                ArrayList ares = new ArrayList();
                ares.add(lmi);
                res.setDatos(ares);
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erUsuarioAmigos",usuario));
                
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

        }
        return res;
    }

}
