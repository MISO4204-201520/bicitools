/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.entity.LogUsuario;
import com.bicitools.entity.RutaPunto;
import com.bicitools.entity.Ruta;
import com.bicitools.mjson.DatosConsumoPuntoRutaJson;
import com.bicitools.mjson.DatosLugaresJson;
import com.bicitools.mjson.DatosMetricasReportesJson;
import com.bicitools.mjson.DatosMetricasUsuarioJson;
import com.bicitools.mjson.DatosRutasJson;
import com.bicitools.mjson.DatosRutasRecorridosJson;
import com.bicitools.mjson.DatosRutasReportesJson;
import com.bicitools.mjson.MetricasUsuario;
import com.bicitools.mjson.RespuestaJson;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.bicitools.mjson.RutasUsuarioJson;
import com.bicitools.ws.ConsumeServicios;
import com.bicitools.ws.TiempoDistanciaInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;

/**
 *
 * @author jhony
 */
@Stateless
public class RutasDAO implements RutasDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    public RutasDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.bicitools_unit");
        em = emf.createEntityManager();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public RespuestaJson obtenerRutasUsuario(String usuario) {
        Ruta r = new Ruta();

        RespuestaJson res = new RespuestaJson();
        Query query = em.createNamedQuery("Ruta.findByUsuario");
        query.setParameter("usuario", usuario);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            int i = 0;
            ArrayList<DatosRutasReportesJson> rta = new ArrayList<DatosRutasReportesJson>();

            for (i = 0; i < qresul.size(); i++) {
                Ruta miRuta = (Ruta) qresul.get(i);
                RutaPunto puntos = new RutaPunto();
                RutaPunto puntoUno = new RutaPunto();
                RutaPunto puntoDos = new RutaPunto();
                DatosRutasReportesJson rutaSalida = new DatosRutasReportesJson();
                //ruta 1
                rutaSalida.setNombre(miRuta.getNombre());
                // Create an instance of SimpleDateFormat used for formatting 
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                rutaSalida.setFechaHora(df.format(miRuta.getFechaCreacion()));

                //rutaSalida.setFechaHora(miRuta.getFechaCreacion().toString());
                puntoUno = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), 0);
                puntoDos = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), -1);

                final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                DatosConsumoPuntoRutaJson entrada = new DatosConsumoPuntoRutaJson();

                entrada.setLatitudOrigen(Double.parseDouble(puntoUno.getLatitud()));
                entrada.setLongitudOrigen(Double.parseDouble(puntoUno.getLongitud()));
                entrada.setLatitudDestino(Double.parseDouble(puntoDos.getLatitud()));
                entrada.setLongitudDestino(Double.parseDouble(puntoDos.getLongitud()));

                final String representacionBonita = prettyGson.toJson(entrada);

                res = ConsumeServicios.consumeTiempoDist(representacionBonita);

                //rutaSalida.setDistancia(miRuta.getDistancia());
                //rutaSalida.setTiempo(miRuta.getTiempo());
                //ArrayList<DatosLugaresJson> lugares = obtenerPuntosRutaUsuario(miRuta.getId());
                //rutaSalida.setLugares(lugares);
                rta.add(rutaSalida);

            }

            res = ConstruyeRespuesta.construyeRespuestaOk();
            res.setDatos(rta);
        }

        res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    @Override
    public RespuestaJson obtenerRutasUsuarioFechas(String usuario, String fechaIni, String fechaFin) {
        Ruta r = new Ruta();
        RespuestaJson res = new RespuestaJson();
        Vector qresul = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaUno, fechaDos;
        try {
            fechaUno = formatter.parse(fechaIni);
            fechaDos = formatter.parse(fechaFin);
        } catch (ParseException ex) {
            String inputStr = "01-01-1900 00:00:00";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                fechaUno = dateFormat.parse(inputStr);
            } catch (Exception miex) {
                fechaUno = new java.util.Date();
            }
            fechaDos = new java.util.Date();
        }

        try {

            Query query = em.createNamedQuery("Ruta.findByUsuarioFechas");
            query.setParameter("usuario", usuario);
            query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
            qresul = (Vector) query.getResultList();

            if (qresul.size() > 0) {
                int i = 0;
                ArrayList<DatosRutasReportesJson> rta = new ArrayList<DatosRutasReportesJson>();

                for (i = 0; i < qresul.size(); i++) {
                    Ruta miRuta = (Ruta) qresul.get(i);
                    RutaPunto puntos = new RutaPunto();
                    DatosRutasReportesJson rutaSalida = new DatosRutasReportesJson();
                    //ruta

                    ArrayList<DatosLugaresJson> lugares = obtenerPuntosRutaUsuario(miRuta.getNombre());
                    rutaSalida.setLugares(lugares);
                    RutaPunto puntoUno = new RutaPunto();
                    RutaPunto puntoDos = new RutaPunto();

                    //ruta 1
                    rutaSalida.setNombre(miRuta.getNombre());

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    rutaSalida.setFechaHora(df.format(miRuta.getFechaCreacion()));
                    //rutaSalida.setFechaHora(miRuta.getFechaCreacion().toString());

                    //obtiene primer y ultimo punto
                    puntoUno = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), 0);
                    puntoDos = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), -1);

                    //consumeServicio Tiempo y distancia
                    final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                    DatosConsumoPuntoRutaJson entrada = new DatosConsumoPuntoRutaJson();

                    entrada.setLatitudOrigen(Double.parseDouble(puntoUno.getLatitud()));
                    entrada.setLongitudOrigen(Double.parseDouble(puntoUno.getLongitud()));
                    entrada.setLatitudDestino(Double.parseDouble(puntoDos.getLatitud()));
                    entrada.setLongitudDestino(Double.parseDouble(puntoDos.getLongitud()));
                    final String representacionBonita = prettyGson.toJson(entrada);

                    res = ConsumeServicios.consumeTiempoDist(representacionBonita);

                    if (res.getCodigo() == 0) {

                        ArrayList<TiempoDistanciaInfo> datos = new ArrayList<>();
                        TiempoDistanciaInfo infoRuta = new TiempoDistanciaInfo();

                        datos = res.getDatos();

                        infoRuta = (TiempoDistanciaInfo) datos.get(0);
                        rutaSalida.setDistancia(infoRuta.getDistancia().replace("\"", ""));
                        rutaSalida.setTiempo(infoRuta.getTiempo().replace("\"", ""));

                        rta.add(rutaSalida);
                        res = ConstruyeRespuesta.construyeRespuestaOk();
                        res.setDatos(rta);
                    }
                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla("error " + ex.getMessage());

        }
        //res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    @Override
    public RespuestaJson obtenerRecorridosUsuarioFechas(String usuario, String fechaIni, String fechaFin) {
        RespuestaJson res =null;
        return res;
    }

    public ArrayList obtenerRecoRuta(String usuario, String fechaIni, String fechaFin, String ruta) {
        Ruta r = new Ruta();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaUno, fechaDos;
        try {
            fechaUno = formatter.parse(fechaIni);
            fechaDos = formatter.parse(fechaFin);
        } catch (ParseException ex) {
            String inputStr = "01-01-1900 00:00:00";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                fechaUno = dateFormat.parse(inputStr);
            } catch (Exception miex) {
                fechaUno = new java.util.Date();
            }
            fechaDos = new java.util.Date();
        }

        ArrayList res = new ArrayList();
        Query query = em.createNamedQuery("LogUsuario.findRecorridosRuta");
        query.setParameter("usuario", usuario);
        query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
        query.setParameter("rutaActual", ruta);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            int i;
            ArrayList rta = new ArrayList<>();

            for (i = 0; i < qresul.size(); i++) {
                Object[] obj = (Object[]) qresul.get(i);
                //LogUsuario datosRecorrido = (LogUsuario) qresul.get(i);

                RutaPunto puntos = new RutaPunto();
                DatosRutasJson rutaSalida = new DatosRutasJson();

                Date fechaInicio = (Date) obj[1];
                String nombreRecorrido = (String) obj[0];

                if (nombreRecorrido.isEmpty()) {
                    nombreRecorrido = "Recorrido_" + (i + 1);
                }

                rutaSalida.setNombre(nombreRecorrido);

                // Create an instance of SimpleDateFormat used for formatting 
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                rutaSalida.setFechaHora(df.format(fechaInicio));

                TiempoDistanciaInfo distRecorrido = obtenerTiempoDistanciaRecorrido(
                        (String) obj[2],
                        (Date) obj[1],
                        (String) obj[0]);
                rutaSalida.setDistancia(distRecorrido.getDistancia());
                rutaSalida.setTiempo(distRecorrido.getTiempo());

                /*ArrayList<DatosLugaresJson> lugares = obtenerPuntosRecorrido(
                 (String) obj[2],
                 (Date) obj[1],
                 (String) obj[0]);*/
                //rutaSalida.setLugares(lugares);
                rta.add(rutaSalida);

            }

            //res = ConstruyeRespuesta.construyeRespuestaOk();
            res.add(rta);
        } else {
            res = null;
        }

        //res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    @Override
    public RespuestaJson obtenerRecorridosRuta(String usuario, String fechaIni, String fechaFin) {
        Ruta r = new Ruta();
        RespuestaJson res = new RespuestaJson();
        Vector qresul = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaUno, fechaDos;
        try {
            fechaUno = formatter.parse(fechaIni);
            fechaDos = formatter.parse(fechaFin);
        } catch (ParseException ex) {
            String inputStr = "01-01-1900 00:00:00";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                fechaUno = dateFormat.parse(inputStr);
            } catch (Exception miex) {
                fechaUno = new java.util.Date();
            }
            fechaDos = new java.util.Date();
        }

        try {

            Query query = em.createNamedQuery("Ruta.findByUsuarioFechas");
            query.setParameter("usuario", usuario);
            query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
            qresul = (Vector) query.getResultList();

            if (qresul.size() > 0) {
                int i;
                ArrayList rta = new ArrayList();

                for (i = 0; i < qresul.size(); i++) {
                    Ruta miRuta = (Ruta) qresul.get(i);
                    RutaPunto puntos = new RutaPunto();
                    DatosRutasRecorridosJson rutaSalida = new DatosRutasRecorridosJson();
                    //ruta

                    //ArrayList<DatosLugaresJson> lugares = obtenerPuntosRutaUsuario(miRuta.getNombre());
                    //rutaSalida.setLugares(lugares);
                    RutaPunto puntoUno;
                    RutaPunto puntoDos;

                    //ruta 1
                    rutaSalida.setNombre(miRuta.getNombre());
                    // Create an instance of SimpleDateFormat used for formatting 
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    rutaSalida.setFechaHora(df.format(miRuta.getFechaCreacion()));

                    //obtiene primer y ultimo punto
                    puntoUno = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), 0);
                    puntoDos = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), -1);

                    //consumeServicio Tiempo y distancia
                    final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                    DatosConsumoPuntoRutaJson entrada = new DatosConsumoPuntoRutaJson();

                    entrada.setLatitudOrigen(Double.parseDouble(puntoUno.getLatitud()));
                    entrada.setLongitudOrigen(Double.parseDouble(puntoUno.getLongitud()));
                    entrada.setLatitudDestino(Double.parseDouble(puntoDos.getLatitud()));
                    entrada.setLongitudDestino(Double.parseDouble(puntoDos.getLongitud()));
                    final String representacionBonita = prettyGson.toJson(entrada);

                    res = ConsumeServicios.consumeTiempoDist(representacionBonita);

                    if (res.getCodigo() == 0) {

                        ArrayList<TiempoDistanciaInfo> datos;
                        TiempoDistanciaInfo infoRuta;

                        datos = res.getDatos();

                        infoRuta = (TiempoDistanciaInfo) datos.get(0);
                        rutaSalida.setDistancia(infoRuta.getDistancia().replace("\"", ""));
                        rutaSalida.setTiempo(infoRuta.getTiempo().replace("\"", ""));

                        rutaSalida.setRecorridos(obtenerRecoRuta(usuario, fechaIni, fechaFin, miRuta.getNombre()));

                        rta.add(rutaSalida);
                        res = ConstruyeRespuesta.construyeRespuestaOk();
                        res.setDatos(rta);
                    }
                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla("error " + ex.getMessage());

        }
        //res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    public TiempoDistanciaInfo obtenerTiempoDistanciaRecorrido(String usuario, Date fechaInicioRecorrido, String nombreRuta) {
        TiempoDistanciaInfo res = null;
        double distanciaTotal = 0;
        double distanciaParcial = 0;
        try {

            res = new TiempoDistanciaInfo();

            Query query = em.createNamedQuery("LogUsuario.findPuntosRecorrido");
            query.setParameter("usuario", usuario);
            query.setParameter("fechaInicioRecorrido", fechaInicioRecorrido, TemporalType.TIMESTAMP);
            query.setParameter("rutaActual", nombreRuta);

            Vector qresul = (Vector) query.getResultList();
            if (qresul.size() > 0) {
                LogUsuario datoRecoIni = (LogUsuario) qresul.get(0);
                LogUsuario datoRecoFin = (LogUsuario) qresul.get(qresul.size() - 1);

                int mins = diferenciaEnMinutos(datoRecoFin.getLogUsuarioPK().getFechaRegistro(),
                        datoRecoIni.getLogUsuarioPK().getFechaRegistro());

                res.setTiempo(String.valueOf(mins) + " mins.");

                for (int i = 0; i < qresul.size(); i++) {
                    LogUsuario datoRecoUno = (LogUsuario) qresul.get(i);
                    LogUsuario datoRecoDos;
                    if (i + 1 < qresul.size()) {
                        datoRecoDos = (LogUsuario) qresul.get(i + 1);
                    } else {
                        break;
                    }

                    final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                    DatosConsumoPuntoRutaJson entrada = new DatosConsumoPuntoRutaJson();

                    entrada.setLatitudOrigen(Double.parseDouble(datoRecoUno.getLogUsuarioPK().getLatitud()));
                    entrada.setLongitudOrigen(Double.parseDouble(datoRecoUno.getLogUsuarioPK().getLongitud()));
                    entrada.setLatitudDestino(Double.parseDouble(datoRecoDos.getLogUsuarioPK().getLatitud()));
                    entrada.setLongitudDestino(Double.parseDouble(datoRecoDos.getLogUsuarioPK().getLongitud()));

                    final String representacionBonita = prettyGson.toJson(entrada);

                    RespuestaJson rta = ConsumeServicios.consumeTiempoDist(representacionBonita);

                    if (rta.getCodigo() == 0) {
                        ArrayList<TiempoDistanciaInfo> datos = new ArrayList<>();
                        TiempoDistanciaInfo infoRuta = new TiempoDistanciaInfo();
                        datos = rta.getDatos();
                        infoRuta = (TiempoDistanciaInfo) datos.get(0);

                        String dist = infoRuta.getDistancia();
                        dist = dist.replace("\"", "");
                        dist = dist.replace(" ", "");
                        boolean kilometros = false;
                        if (dist.contains("km")) {
                            dist = dist.replace("k", "");
                            kilometros = true;
                        }

                        dist = dist.replace("m", "");

                        distanciaParcial = Double.parseDouble(dist);
                        if (kilometros) {
                            distanciaParcial *= 1000;
                        }

                        distanciaTotal += distanciaParcial;

                    }

                }

                res.setDistancia(String.valueOf(distanciaTotal) + " mts.");

            }
        } catch (Exception ex) {

        }

        return res;
    }

    public ArrayList obtenerPuntosRecorrido(String usuario, Date fechaInicioRecorrido, String nombreRuta) {
        ArrayList res = null;

        try {

            res = new ArrayList();
            res.clear();

            Query query = em.createNamedQuery("LogUsuario.findPuntosRecorrido");
            query.setParameter("usuario", usuario);
            query.setParameter("fechaInicioRecorrido", fechaInicioRecorrido, TemporalType.TIMESTAMP);
            query.setParameter("rutaActual", nombreRuta);

            Vector qresul = (Vector) query.getResultList();
            if (qresul.size() > 0) {
                for (int i = 0; i < qresul.size(); i++) {
                    DatosLugaresJson lugar = new DatosLugaresJson();
                    LogUsuario datoReco = (LogUsuario) qresul.get(i);

                    lugar.setLatitud(datoReco.getLogUsuarioPK().getLatitud());
                    lugar.setLongitud(datoReco.getLogUsuarioPK().getLongitud());

                    res.add(lugar);

                }

            }
        } catch (Exception ex) {

        }

        return res;
    }

    public static int diferenciaEnMinutos(Date fechaMayor, Date fechaMenor) {

        long diff = fechaMayor.getTime();
        long diff2 = fechaMenor.getTime();

        diff = diff - diff2;
        long minutos = diff / (1000 * 60);
        return (int) minutos;
    }

    @Override
    public RespuestaJson crearRutasUsuario(RutasUsuarioJson info) {
        RespuestaJson res;
        Ruta rutaNueva = new Ruta();
        Date fecha = new java.util.Date();
        rutaNueva.setUsuario(info.getUsuario());
        //rutaNueva.setNombreRuta(info.getNombre());

        //rutaNueva.setLatitudIni(info.getLatIni());
        /*rutaNueva.setLongitudIni(info.getLngIni());
         rutaNueva.setLatitudFin(info.getLatFin());
         rutaNueva.setLongitudFin(info.getLngFin());

         rutaNueva.setDistancia(info.getDistancia());
         rutaNueva.setTiempo(info.getTiempo());

         rutaNueva.setFechaHora(fecha);
         rutaNueva.setTipo(0);

         rutaNueva.setId(0);*/
        try {
            em.persist(rutaNueva);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;

    }

    @Override
    public RespuestaJson crearRecorridoUsuario(RutasUsuarioJson info) {
        RespuestaJson res;
        Ruta rutaNueva = new Ruta();
        Date fecha = new java.util.Date();
        rutaNueva.setUsuario(info.getUsuario());
        /*
         rutaNueva.setNombreRuta(info.getNombre());

         rutaNueva.setLatitudIni(info.getLatIni());
         rutaNueva.setLongitudIni(info.getLngIni());
         rutaNueva.setLatitudFin(info.getLatFin());
         rutaNueva.setLongitudFin(info.getLngFin());

         rutaNueva.setDistancia(info.getDistancia());
         rutaNueva.setTiempo(info.getTiempo());

         rutaNueva.setFechaHora(fecha);
         rutaNueva.setTipo(1);

         rutaNueva.setId(0);
         */
        try {
            em.persist(rutaNueva);
            res = ConstruyeRespuesta.construyeRespuestaOk();
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;

    }

    @Override
    public ArrayList obtenerPuntosRutaUsuario(String nombreRuta) {
        ArrayList res = new ArrayList();
        Query query = em.createNamedQuery("RutaPunto.findByNombre");
        query.setParameter("nombre", nombreRuta);
        Vector qresul = (Vector) query.getResultList();

        res.clear();

        if (qresul.size() > 0) {
            int i = 0;
            for (i = 0; i < qresul.size(); i++) {
                DatosLugaresJson puntoJson = new DatosLugaresJson();
                RutaPunto punto = (RutaPunto) qresul.get(i);
                //puntoJson.setNombre(punto.getNombre());
                puntoJson.setLatitud(punto.getLatitud());
                puntoJson.setLongitud(punto.getLongitud());
                res.add(puntoJson);
            }
        }

        return res;
    }

    @Override
    public RutaPunto obtenerPuntoRutaIndiceUsuario(String nombreRuta, int indice) {

        RutaPunto punto = null;
        Query query = em.createNamedQuery("RutaPunto.findByNombre");
        query.setParameter("nombre", nombreRuta);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            int i = 0;
            DatosLugaresJson puntoJson = new DatosLugaresJson();

            if (indice == -1) {
                punto = (RutaPunto) qresul.get(qresul.size() - 1);
            } else {
                punto = (RutaPunto) qresul.get(indice);
            }
            //puntoJson.setNombre(punto.getNombre());
            puntoJson.setLatitud(punto.getLatitud());
            puntoJson.setLongitud(punto.getLongitud());
        }

        return punto;
    }

    @Override
    public RespuestaJson obtenerMetricasUsuario(String usuario, String fechaIni, String fechaFin) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaUno, fechaDos;
        try {
            fechaUno = formatter.parse(fechaIni);
            fechaDos = formatter.parse(fechaFin);
        } catch (ParseException ex) {
            String inputStr = "01-01-1900 00:00:00";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                fechaUno = dateFormat.parse(inputStr);
            } catch (Exception miex) {
                fechaUno = new java.util.Date();
            }
            fechaDos = new java.util.Date();
        }

        RespuestaJson res = new RespuestaJson();
        Query query = em.createNamedQuery("Rutas.findMetricasByUsuario");
        query.setParameter("usuario", usuario);
        query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
        List<Object[]> qresul = query.getResultList();

        if (qresul.size() > 0) {
            int distanciaTotal = 0, tiempoTotal = 0;
            ArrayList<DatosMetricasReportesJson> rta = new ArrayList<>();
            ArrayList<DatosMetricasUsuarioJson> lista = new ArrayList<>();
            DatosMetricasReportesJson salida = new DatosMetricasReportesJson();
            MetricasUsuario total = new MetricasUsuario();
            lista.clear();

            for (Object[] object : qresul) {

                DatosMetricasUsuarioJson metricas = new DatosMetricasUsuarioJson();
                Date fechaSalida = (Date) object[2];

                // Create an instance of SimpleDateFormat used for formatting 
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String reportDate = df.format(fechaSalida);

                metricas.setFecha(reportDate);
                metricas.setTiempo((String) object[1]);
                metricas.setDistancia((String) object[0]);
                lista.add(metricas);

                distanciaTotal += Integer.parseInt(metricas.getDistancia());
                tiempoTotal += Integer.parseInt(metricas.getTiempo());

            }

            salida.setLista(lista);

            total.setTiempo("" + tiempoTotal);
            total.setDistancia("" + distanciaTotal);

            salida.setTotal(total);

            rta.add(salida);
            res = ConstruyeRespuesta.construyeRespuestaOk();
            res.setDatos(rta);

        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
        }

        res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

 

    public ArrayList obtenerRecoRuta(String usuario, String fechaIni, String fechaFin, String ruta, Document doc) {
        Ruta r = new Ruta();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaUno, fechaDos;
        try {
            fechaUno = formatter.parse(fechaIni);
            fechaDos = formatter.parse(fechaFin);
        } catch (ParseException ex) {
            String inputStr = "01-01-1900 00:00:00";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                fechaUno = dateFormat.parse(inputStr);
            } catch (Exception miex) {
                fechaUno = new java.util.Date();
            }
            fechaDos = new java.util.Date();
        }

        ArrayList res = new ArrayList();
        Query query = em.createNamedQuery("LogUsuario.findRecorridosRuta");
        query.setParameter("usuario", usuario);
        query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
        query.setParameter("rutaActual", ruta);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            try {
                int i;
                ArrayList rta = new ArrayList<>();

                PdfPTable tabla = new PdfPTable(3);

                tabla.addCell("Fecha");
                tabla.addCell("Distancia");
                tabla.addCell("Tiempo");

                for (i = 0; i < qresul.size(); i++) {
                    Object[] obj = (Object[]) qresul.get(i);
                    //LogUsuario datosRecorrido = (LogUsuario) qresul.get(i);

                    RutaPunto puntos = new RutaPunto();
                    DatosRutasJson rutaSalida = new DatosRutasJson();

                    Date fechaInicio = (Date) obj[1];
                    String nombreRecorrido = (String) obj[0];

                    if (nombreRecorrido.isEmpty()) {
                        nombreRecorrido = "Recorrido_" + (i + 1);
                    }

                    rutaSalida.setNombre(nombreRecorrido);

                    // Create an instance of SimpleDateFormat used for formatting
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    rutaSalida.setFechaHora(df.format(fechaInicio));

                    TiempoDistanciaInfo distRecorrido = obtenerTiempoDistanciaRecorrido(
                            (String) obj[2],
                            (Date) obj[1],
                            (String) obj[0]);
                    rutaSalida.setDistancia(distRecorrido.getDistancia());
                    rutaSalida.setTiempo(distRecorrido.getTiempo());

                    /*ArrayList<DatosLugaresJson> lugares = obtenerPuntosRecorrido(
                     (String) obj[2],
                     (Date) obj[1],
                     (String) obj[0]);*/
                    //rutaSalida.setLugares(lugares);
                    rta.add(rutaSalida);

                    tabla.addCell(df.format(fechaInicio));
                    tabla.addCell(distRecorrido.getDistancia().replace("\"", ""));
                    tabla.addCell(distRecorrido.getDistancia().replace("\"", ""));

                }
                doc.add(tabla);

                //res = ConstruyeRespuesta.construyeRespuestaOk();
                res.add(rta);
            } catch (DocumentException ex) {
                Logger.getLogger(RutasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            res = null;
        }

        //res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    @Override
    public RespuestaJson exportarReporteMetricasUsuario(String usuario, String fechaIni, String fechaFin, String archivo) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespuestaJson exportarRutasUsuario(String usuario, String fechaIni, String fechaFin, String archivo) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespuestaJson exportarRecorridosUsuario(String usuario, String fechaIni, String fechaFin, String archivo) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespuestaJson exportarRecorridosRuta(String usuario, String fechaIni, String fechaFin, String archivo) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
