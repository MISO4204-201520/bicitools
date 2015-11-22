/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.cliente.ConsumeServicios;
import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.entity.Domiciliario;
import com.bicitools.entity.Horariodomiciliario;
import com.bicitools.entity.Serviciodomicilio;
import com.bicitools.mjson.CalificacionUsuarioServicioJson;
import com.bicitools.mjson.DatosEntradaServicioJson;
import com.bicitools.mjson.DatosSalidaServicioJson;
import com.bicitools.mjson.InfoEntregaJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.ServicioJson;
import com.bicitools.mjson.UsuarioJson;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ludwing
 */
@Stateless
public class ServicioDomicilioDAO implements ServicioDomicilioDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    //LBD:
    //Logica de negocio para crear un servicio a domicilio
    //En la capa de presentación se valido previamente todos los campos.
    //Los campos estimado y real son string que contiene el tiempo estimado y rel tomado para l a entrega
    @Override
    public RespuestaJson creaServicio(InfoEntregaJson info) {
        RespuestaJson res = new RespuestaJson();
        ServicioJson ser;
        Serviciodomicilio dom = new Serviciodomicilio();
        UsuarioJson j = new UsuarioJson();
        RespuestaJson tmp2 = new RespuestaJson();
        ArrayList dat;
        LinkedHashMap iuc;
        try {
            dom.setIdUsuario(info.getUsuario());
            dom.setOrigen(info.getOrigen());
            dom.setDestino(info.getDestino());

            j.setUsuario(info.getUsuario());
            tmp2 = ConsumeServicios.consume1("usuario", j);
            if (tmp2.getCodigo() == 0) {

                if (!tmp2.getDatos().isEmpty()) {
                    dat = tmp2.getDatos();
                    iuc = (LinkedHashMap) dat.get(0);
                    dom.setEmailUsuario((String) iuc.get("correo"));
                    dom.setEstado(1);
                    em.persist(dom);  //persiste la tabla serviciodomicilio

                    ArrayList<ServicioJson> ar = new ArrayList();
                    ser = new ServicioJson();
                    ser.setIdServicio(dom.getIdServiciodomicilio());
                    res = enviaSolicitud(ser);

                    ar.add(ser);
                    res.setDatos(ar);

                }
            }
            else{
                res = tmp2;
                res.setDescripcion("Usuario no registrado");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());

        }
        return res;
    }

    //LBD:
    //Logica de negocio para generar solicitudes a los domiciliarios dao un servicio 
    //En caso de no poder enviar solicitudes queda cancelado el servicio
    @Override
    public RespuestaJson enviaSolicitud(ServicioJson info) {

        RespuestaJson res = new RespuestaJson();
        String temp = "";
        boolean esActivo = true;
        try {
            Query query2 = em.createNamedQuery("Serviciodomicilio.findByIdServiciodomicilio");
            query2.setParameter("idServiciodomicilio", info.getIdServicio());
            Serviciodomicilio sd = (Serviciodomicilio) query2.getSingleResult();
            Query query = em.createNamedQuery("Domiciliario.findByEstado");
            query.setParameter("estado", 0);
            List<Domiciliario> list = query.getResultList();
            Address[] listA = new Address[1];
            Horariodomiciliario pd;
            Object[] col;
            int i = 0;
            int j = 0;
            for (Domiciliario dom : list) {
                col = dom.getHorariodomiciliarioCollection().toArray();
                for (j = 0; j < col.length; j++) {
                    pd = (Horariodomiciliario) col[j];
                    esActivo = estaActivo(pd.getDia(), pd.getInicio(), pd.getFin());
                    if (esActivo) {
                        j = dom.getHorariodomiciliarioCollection().size() + 1;
                        listA[0] = new InternetAddress(dom.getEmail());

                        i++;
                    }
                }
            }

            if (i > 0) {

                temp = enviaCorreo(listA,
                        creaMensajeCorreoSolicitud(info.getIdServicio(), sd.getOrigen(), sd.getDestino()),
                        creaSubjectCorreoSolicitud());
            }
            if (temp.equals("OK")) {
                sd.setEstado(1);
                em.persist(sd);
                res = ConstruyeRespuesta.construyeRespuestaOk();
            } else {
                sd.setEstado(4);
                em.persist(sd);
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erNoDispDom"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.toString());

        }

        return res;
    }

    //LBD:
    //Logica de negocio para aceptar solicitudes de los domiciliarios dado un servicio 
    @Override
    public RespuestaJson aceptaServicio(DatosEntradaServicioJson info) {
        RespuestaJson res = new RespuestaJson();
        String temp = "";

        try {
            Query query1 = em.createNamedQuery("Domiciliario.findByIdDomiciliario");
            query1.setParameter("idDomiciliario", info.getUsuario());
            Domiciliario dom = (Domiciliario) query1.getSingleResult();
            dom.setEstado(1);
            Query query2 = em.createNamedQuery("Serviciodomicilio.findByIdServiciodomicilio");
            query2.setParameter("idServiciodomicilio", info.getIdServicio());
            Serviciodomicilio sd = (Serviciodomicilio) query2.getSingleResult();
            sd.setIdDomiciliario(info.getUsuario());
            sd.setTestimado(info.getEstimado());
            sd.setEstado(2);
            Address[] listA = new Address[1];
            listA[0] = new InternetAddress(sd.getEmailUsuario());
            temp = enviaCorreo(listA,
                    creaMensajeCorreoAcepta(info.getIdServicio(), info.getEstimado()),
                    creaSubjectCorreoSolicitud());
            if (temp.equals("OK")) {
                em.persist(sd);
                em.persist(dom);
                res = ConstruyeRespuesta.construyeRespuestaOk();
            } else {

                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erEmailUsuario"));
            }

            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {

            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.toString());

        }

        return res;

    }

    //LBD:
    //Logica de negocio para aceptar cancelar solicitudes de un servicio 
    @Override
    public RespuestaJson cancelaServicio(ServicioJson info) {
        RespuestaJson res;
        try {
            String temp = "";
            Query query2 = em.createNamedQuery("Serviciodomicilio.findByIdServiciodomicilio");
            query2.setParameter("idServiciodomicilio", info.getIdServicio());
            Serviciodomicilio sd = (Serviciodomicilio) query2.getSingleResult();
            sd.setEstado(4);
            Query query1 = em.createNamedQuery("Domiciliario.findByIdDomiciliario");
            query1.setParameter("idDomiciliario", sd.getIdDomiciliario());
            Domiciliario dom = (Domiciliario) query1.getSingleResult();
            dom.setEstado(0);
            em.persist(sd);
            em.persist(dom);

            Address[] listA = new Address[1];
            listA[0] = new InternetAddress(dom.getEmail());
            temp = enviaCorreo(listA,
                    creaMensajeCorreoCancela(info.getIdServicio()),
                    creaSubjectCorreoSolicitud());
            if (temp.equals("OK")) {
                res = ConstruyeRespuesta.construyeRespuestaOk();
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erCancelaNoEnviado"));
            }

        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla("El Servicio no Existe o No fue atendido por ningun Domiciliario");
        }
        return res;
    }

    //LBD:
    //Logica de negocio para que un domiciliario registre un servicio una vez terminado
    @Override
    public RespuestaJson registraServicio(DatosSalidaServicioJson info) {
        RespuestaJson res;
        try {
            Query query2 = em.createNamedQuery("Serviciodomicilio.findByIdServiciodomicilio");
            query2.setParameter("idServiciodomicilio", info.getIdServicio());
            Serviciodomicilio sd = (Serviciodomicilio) query2.getSingleResult();
            sd.setEstado(3);
            sd.setTreal(info.getReal());
            Query query1 = em.createNamedQuery("Domiciliario.findByIdDomiciliario");
            query1.setParameter("idDomiciliario", sd.getIdDomiciliario());
            Domiciliario dom = (Domiciliario) query1.getSingleResult();
            dom.setEstado(0);
            em.persist(sd);
            em.persist(dom);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.toString());
        }
        return res;
    }

    //LBD:
    //Logica de negocio para calificar un servicio 

    @Override
    public RespuestaJson calificaServicio(CalificacionUsuarioServicioJson info) {
        RespuestaJson res;
        try {
            Query query2 = em.createNamedQuery("Serviciodomicilio.findByIdServiciodomicilio");
            query2.setParameter("idServiciodomicilio", info.getIdServicio());
            Serviciodomicilio sd = (Serviciodomicilio) query2.getSingleResult();
            sd.setComentario(info.getComentarios());
            sd.setPuntos(info.getPuntos());
            em.persist(sd);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.toString());
        }
        return res;

    }

    //LBD:
    //Validación de la disponibilidad de un domiciliario verificando su horario.
    public boolean estaActivo(int dia, String inicio, String fin) {
        boolean res = false;
        int hini, mini, hfin, mfin;
        Calendar choy = Calendar.getInstance();
        Calendar cini = Calendar.getInstance();
        Calendar cfin = Calendar.getInstance();
        choy.setTime(new Date());
        if (choy.get(Calendar.DAY_OF_WEEK) == dia) {
            StringTokenizer st = new StringTokenizer(inicio, ":");
            hini = Integer.parseInt(st.nextToken());
            mini = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(fin, ":");
            hfin = Integer.parseInt(st.nextToken());
            mfin = Integer.parseInt(st.nextToken());
            cini.set(Calendar.HOUR_OF_DAY, hini);
            cini.set(Calendar.MINUTE, mini);
            cfin.set(Calendar.HOUR_OF_DAY, hfin);
            cfin.set(Calendar.MINUTE, mfin);
            if (choy.compareTo(cini) >= 0 && choy.compareTo(cfin) <= 0) {
                res = true;
            }
        }
        return res;
    }

    public String creaMensajeCorreoSolicitud(long idServicio, String dirOrigen, String dirDestino) {
        return "Se ha generado el siguiente servicio:\nId:" + idServicio + "\nOrigen: " + dirOrigen + "\nDestino: " + dirDestino;

    }

    public String creaMensajeCorreoAcepta(long idServicio, String estimado) {
        return "El servicio:\nId:" + idServicio + "\nSera entregado en un tiempo estimado: " + estimado;

    }

    public String creaMensajeCorreoCancela(long idServicio) {
        return "El servicio:\nId:" + idServicio + "\nHa sido cancelado";

    }

    public String creaSubjectCorreoSolicitud() {
        return "Solicitud Servicio Bicitools";
    }

    //LBD:
    //Envío de correos electrónicos desde la plataforma
    public String enviaCorreo(Address[] mail, String mensaje, String subject) {
        String respuesta = "OK";
        Properties props = new Properties();
        try {
            List<String> list = Collections.list(MessagesBicitools.getCorreoProperties());
            for (String a : list) {
                props.put(a, MessagesBicitools.getCorreo(a));
            }
            SecurityManager security = System.getSecurityManager();

            Authenticator auth = new autentificadorSMTP();
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(mensaje);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(MessagesBicitools.getCorreo("usuario")));

            msg.addRecipients(Message.RecipientType.TO, mail);
            Transport.send(msg);
        } catch (FileNotFoundException | MessagingException mex) {
            respuesta = mex.getMessage();
        }
        return respuesta;

    }

    private class autentificadorSMTP extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MessagesBicitools.getCorreo("usuario"), MessagesBicitools.getCorreo("clave"));
        }
    }

}
