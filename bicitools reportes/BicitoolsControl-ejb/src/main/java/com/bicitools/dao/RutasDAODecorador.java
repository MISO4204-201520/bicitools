/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import static com.bicitools.dao.RutasDAO.diferenciaEnMinutos;
import com.bicitools.entity.LogUsuario;
import com.bicitools.entity.Ruta;
import com.bicitools.entity.RutaPunto;
import com.bicitools.mjson.DatosConsumoPuntoRutaJson;
import com.bicitools.mjson.DatosLugaresJson;
import com.bicitools.mjson.DatosMetricasReportesJson;
import com.bicitools.mjson.DatosMetricasUsuarioJson;
import com.bicitools.mjson.DatosRutasJson;
import com.bicitools.mjson.DatosRutasRecorridosJson;
import com.bicitools.mjson.DatosRutasReportesJson;
import com.bicitools.mjson.MetricasUsuario;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.RutasUsuarioJson;
import com.bicitools.ws.ConsumeServicios;
import com.bicitools.ws.TiempoDistanciaInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author jhony
 */
//@Stateless
public class RutasDAODecorador extends RutasAbstractDAO{
    
    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;
    
    public RutasDAODecorador(RutasDAOLocal rutas){
        super(rutas);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.bicitools_unit");
        em = emf.createEntityManager();
    }

    @Override
    public RespuestaJson obtenerRutasUsuario(String usuario) {
        return getRutasDAO().obtenerRutasUsuario(usuario);
    }

    @Override
    public RespuestaJson obtenerRutasUsuarioFechas(String usuario, String fechaIni, String fechaFin) {
        return getRutasDAO().obtenerRutasUsuarioFechas(usuario,fechaIni,fechaFin);
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

    @Override
    public RespuestaJson obtenerRecorridosUsuarioFechas(String usuario, String fechaIni, String fechaFin) {
        RespuestaJson res = new RespuestaJson();
        res = getRutasDAO().obtenerRecorridosUsuarioFechas(usuario, fechaIni, fechaFin);

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

        Query query = em.createNamedQuery("LogUsuario.findRecorridos");
        query.setParameter("usuario", usuario);
        query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            int i;
            ArrayList<DatosRutasReportesJson> rta = new ArrayList<>();

            for (i = 0; i < qresul.size(); i++) {
                Object[] obj = (Object[]) qresul.get(i);
                //LogUsuario datosRecorrido = (LogUsuario) qresul.get(i);

                RutaPunto puntos = new RutaPunto();
                DatosRutasReportesJson rutaSalida = new DatosRutasReportesJson();

                Date fechaInicio = (Date) obj[1];
                String nombreRecorrido = (String) obj[0];

                if (nombreRecorrido.isEmpty()) {
                    nombreRecorrido = "Recorrido_" + (i + 1);
                }

                rutaSalida.setNombre(nombreRecorrido);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                rutaSalida.setFechaHora(df.format(fechaInicio));
                //rutaSalida.setFechaHora(fechaInicio.toString());

                TiempoDistanciaInfo distRecorrido = obtenerTiempoDistanciaRecorrido(
                        (String) obj[2],
                        (Date) obj[1],
                        (String) obj[0]);
                rutaSalida.setDistancia(distRecorrido.getDistancia());
                rutaSalida.setTiempo(distRecorrido.getTiempo());

                ArrayList<DatosLugaresJson> lugares = obtenerPuntosRecorrido(
                        (String) obj[2],
                        (Date) obj[1],
                        (String) obj[0]);

                rutaSalida.setLugares(lugares);
                rta.add(rutaSalida);

            }

            res = ConstruyeRespuesta.construyeRespuestaOk();
            res.setDatos(rta);
        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
        }

        res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    @Override
    public RespuestaJson crearRutasUsuario(RutasUsuarioJson info) {
        return getRutasDAO().crearRutasUsuario(info);
    }

    @Override
    public ArrayList obtenerPuntosRutaUsuario(String nombreRuta) {
        return getRutasDAO().obtenerPuntosRutaUsuario(nombreRuta);
    }

    @Override
    public RespuestaJson crearRecorridoUsuario(RutasUsuarioJson info) {
        return getRutasDAO().crearRecorridoUsuario(info);
    }

    @Override
    public RespuestaJson obtenerMetricasUsuario(String usuario, String fechaIni, String fechaFin) {
        return getRutasDAO().obtenerMetricasUsuario(usuario,fechaIni,fechaFin);
    }

    @Override
    public RutaPunto obtenerPuntoRutaIndiceUsuario(String nombreRuta, int indice) {
        return getRutasDAO().obtenerPuntoRutaIndiceUsuario(nombreRuta,indice);
    }

    @Override
    public RespuestaJson obtenerRecorridosRuta(String usuario, String fechaIni, String fechaFin) {
        return getRutasDAO().obtenerRecorridosRuta(usuario,fechaIni,fechaFin);
    }
    
    public static int diferenciaEnMinutos(Date fechaMayor, Date fechaMenor) {

        long diff = fechaMayor.getTime();
        long diff2 = fechaMenor.getTime();

        diff = diff - diff2;
        long minutos = diff / (1000 * 60);
        return (int) minutos;
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
           System.out.println("error "+ex.getMessage());
        }

        return res;
    }

    
    @Override
    public RespuestaJson exportarRutasUsuario(String usuario, String fechaIni, String fechaFin, String archivo) { 
        Ruta r = new Ruta();
        
        RespuestaJson res = new RespuestaJson();
        res = getRutasDAO().exportarRutasUsuario(usuario,fechaIni,fechaFin,archivo);
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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.bicitools_unit");
            EntityManager ema = emf.createEntityManager();

            Query query = ema.createNamedQuery("Ruta.findByUsuarioFechas");
            query.setParameter("usuario", usuario);
            query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
            qresul = (Vector) query.getResultList();

            if (qresul.size() > 0) {
                int i = 0;
                ArrayList<DatosRutasReportesJson> rta = new ArrayList<DatosRutasReportesJson>();

                // Se crea el documento
                Document documento = new Document();

                // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
                FileOutputStream ficheroPdf = null;
                //ficheroPdf = new FileOutputStream("/Users/jhony/Documents/Uni Andes/Fabricas/Bicitools/bicitools/reporte.pdf");

                DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
                Date fechaReporte = new Date();

                ficheroPdf = new FileOutputStream(archivo + "/Reporte_Rutas_" + usuario + "_" + dateFormat.format(fechaReporte) + ".pdf");

                // Se asocia el documento al OutputStream y se indica que el espaciado entre
                // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

                // Se abre el documento.
                documento.open();

                documento.add(new Paragraph("Reporte de Rutas para " + usuario));
                documento.add(new Paragraph(" "));

                Date fechaSalida = fechaUno;

                // Create an instance of SimpleDateFormat used for formatting
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String reportDate = df.format(fechaSalida);
                documento.add(new Paragraph("Fecha Inicial: " + reportDate));
                fechaSalida = fechaDos;
                reportDate = df.format(fechaSalida);
                documento.add(new Paragraph("Fecha Final: " + reportDate));

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                PdfPTable tabla = new PdfPTable(4);
                tabla.addCell("Fecha");
                tabla.addCell("Nombre Ruta");
                tabla.addCell("Tiempo");
                tabla.addCell("Distancia");

                for (i = 0; i < qresul.size(); i++) {
                    Ruta miRuta = (Ruta) qresul.get(i);

                    fechaSalida = miRuta.getFechaCreacion();

                    // Create an instance of SimpleDateFormat used for formatting
                    df = new SimpleDateFormat("yyyy-MM-dd");

                    reportDate = df.format(fechaSalida);

                    tabla.addCell(reportDate);

                    RutaPunto puntos = new RutaPunto();
                    DatosRutasReportesJson rutaSalida = new DatosRutasReportesJson();
                    //ruta

                    tabla.addCell(miRuta.getNombre());

                    ArrayList<DatosLugaresJson> lugares = obtenerPuntosRutaUsuario(miRuta.getNombre());
                    rutaSalida.setLugares(lugares);

                    RutaPunto puntoUno = new RutaPunto();
                    RutaPunto puntoDos = new RutaPunto();

                    //ruta 1
                    rutaSalida.setNombre(miRuta.getNombre());

                    rutaSalida.setFechaHora(miRuta.getFechaCreacion().toString());

                    //obtiene primer y ultimo punto
                    puntoUno = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), 0);
                    puntoDos = obtenerPuntoRutaIndiceUsuario(miRuta.getNombre(), -1);

                    if (puntoUno != null && puntoDos != null) {

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

                            tabla.addCell(infoRuta.getTiempo().replace("\"", ""));
                            tabla.addCell(infoRuta.getDistancia().replace("\"", ""));
                            //rutaSalida.setDistancia(infoRuta.getDistancia());
                            //rutaSalida.setTiempo(infoRuta.getTiempo());

                            //rta.add(rutaSalida);
                            //res.setDatos(rta);
                        }
                    } else {
                        tabla.addCell("ND");
                        tabla.addCell("ND");
                    }
                }

                documento.add(tabla);
                
                documento.add(new Paragraph(" "));
                try {
                    Image foto = Image.getInstance("http://1.bp.blogspot.com/-fV-ThFg9bN0/UCr4VMFrJ-I/AAAAAAAAEYQ/-_vIDIYDLz8/s1600/dibujo-pintar-doki-bicicleta.jpg");
                    foto.scaleToFit(100, 100);
                    foto.setAlignment(Chunk.ALIGN_CENTER);
                    documento.add(foto);
                } catch (IOException | DocumentException e) {
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();

                documento.close();
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
            }

        } catch (FileNotFoundException | DocumentException | NumberFormatException ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla("error " + ex.getMessage());

        }

        //res.setDescripcion("numero de datos devueltos " + qresul.size());
        return res;
    }

    @Override
    public RespuestaJson exportarRecorridosUsuario(String usuario, String fechaIni, String fechaFin, String archivo) {
        
        RespuestaJson res = new RespuestaJson();
        res = getRutasDAO().exportarRecorridosUsuario(usuario,fechaIni,fechaFin,archivo);
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

        
        Query query = em.createNamedQuery("LogUsuario.findRecorridos");
        query.setParameter("usuario", usuario);
        query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            try {
                int i;
                ArrayList<DatosRutasReportesJson> rta = new ArrayList<>();

                Document documento = new Document();

                // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
                FileOutputStream ficheroPdf = null;
                //ficheroPdf = new FileOutputStream("/Users/jhony/Documents/Uni Andes/Fabricas/Bicitools/bicitools/reporte.pdf");

                DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
                Date fechaReporte = new Date();

                ficheroPdf = new FileOutputStream(archivo + "/Reporte_Recorridos_" + usuario + "_" + dateFormat.format(fechaReporte) + ".pdf");

                // Se asocia el documento al OutputStream y se indica que el espaciado entre
                // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

                // Se abre el documento.
                documento.open();

                documento.add(new Paragraph("Reporte de Recorridos para " + usuario));
                documento.add(new Paragraph(" "));

                Date fechaSalida = fechaUno;

                // Create an instance of SimpleDateFormat used for formatting
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String reportDate = df.format(fechaSalida);
                documento.add(new Paragraph("Fecha Inicial: " + reportDate));
                fechaSalida = fechaDos;
                reportDate = df.format(fechaSalida);
                documento.add(new Paragraph("Fecha Final: " + reportDate));

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                PdfPTable tabla = new PdfPTable(4);

                tabla.addCell("Fecha");
                tabla.addCell("Tiempo");
                tabla.addCell("Distancia");
                tabla.addCell("Ruta");

                for (i = 0; i < qresul.size(); i++) {
                    Object[] obj = (Object[]) qresul.get(i);
                    //LogUsuario datosRecorrido = (LogUsuario) qresul.get(i);

                    RutaPunto puntos = new RutaPunto();
                    //DatosRutasReportesJson rutaSalida = new DatosRutasReportesJson();

                    Date fechaInicio = (Date) obj[1];
                    String nombreRecorrido = (String) obj[0];

                    if (nombreRecorrido.isEmpty()) {
                        nombreRecorrido = "Recorrido_" + (i + 1);
                    }

                    //rutaSalida.setNombre(nombreRecorrido);
                    //rutaSalida.setFechaHora(fechaInicio.toString());
                    fechaSalida = fechaInicio;
                    // Create an instance of SimpleDateFormat used for formatting
                    df = new SimpleDateFormat("yyyy-MM-dd");
                    reportDate = df.format(fechaSalida);
                    tabla.addCell(reportDate);

                    TiempoDistanciaInfo distRecorrido = obtenerTiempoDistanciaRecorrido(
                            (String) obj[2],
                            (Date) obj[1],
                            (String) obj[0]);
                    //rutaSalida.setDistancia(distRecorrido.getDistancia());
                    //rutaSalida.setTiempo(distRecorrido.getTiempo());

                    tabla.addCell(distRecorrido.getTiempo().replace("\"", ""));
                    tabla.addCell(distRecorrido.getDistancia().replace("\"", ""));
                    tabla.addCell(nombreRecorrido);
                    /*ArrayList<DatosLugaresJson> lugares = obtenerPuntosRecorrido(
                     (String) obj[2],
                     (Date) obj[1],
                     (String) obj[0]);
                    
                    
                     rutaSalida.setLugares(lugares);
                     rta.add(rutaSalida);*/

                }

                documento.add(tabla);
                documento.add(new Paragraph(" "));
                try {
                    Image foto = Image.getInstance("http://1.bp.blogspot.com/-fV-ThFg9bN0/UCr4VMFrJ-I/AAAAAAAAEYQ/-_vIDIYDLz8/s1600/dibujo-pintar-doki-bicicleta.jpg");
                    foto.scaleToFit(100, 100);
                    foto.setAlignment(Chunk.ALIGN_CENTER);
                    documento.add(foto);
                } catch (IOException | DocumentException e) {
                }

                res = ConstruyeRespuesta.construyeRespuestaOk();
                documento.close();

                //res.setDatos(rta);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RutasDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(RutasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
        }

        res.setDescripcion("numero de datos devueltos " + qresul.size());
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
    public RespuestaJson exportarRecorridosRuta(String usuario, String fechaIni, String fechaFin, String archivo) {
        
        Ruta r = new Ruta();
        RespuestaJson res = new RespuestaJson();
        res = getRutasDAO().exportarRecorridosRuta(usuario,fechaIni,fechaFin,archivo);
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

                // Se crea el documento
                Document documento = new Document();

                // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
                FileOutputStream ficheroPdf = null;
                //ficheroPdf = new FileOutputStream("/Users/jhony/Documents/Uni Andes/Fabricas/Bicitools/bicitools/reporte.pdf");

                DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
                Date fechaReporte = new Date();

                ficheroPdf = new FileOutputStream(archivo + "/Reporte_RutasRecorridos_" + usuario + "_" + dateFormat.format(fechaReporte) + ".pdf");

                // Se asocia el documento al OutputStream y se indica que el espaciado entre
                // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

                // Se abre el documento.
                documento.open();

                documento.add(new Paragraph("Reporte de Recorridos por Ruta para " + usuario));
                documento.add(new Paragraph(" "));

                Date fechaSalida = fechaUno;

                // Create an instance of SimpleDateFormat used for formatting
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String reportDate = df.format(fechaSalida);
                documento.add(new Paragraph("Fecha Inicial: " + reportDate));
                fechaSalida = fechaDos;
                reportDate = df.format(fechaSalida);
                documento.add(new Paragraph("Fecha Final: " + reportDate));

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));

                for (i = 0; i < qresul.size(); i++) {
                    PdfPTable tabla = new PdfPTable(3);

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
                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

                        //rutaSalida.setDistancia(infoRuta.getDistancia().replace("\"",""));
                        //rutaSalida.setTiempo(infoRuta.getTiempo().replace("\"",""));
                        documento.add(new Paragraph("Nombre: " + miRuta.getNombre()));
                        documento.add(new Paragraph("Distancia calculada: " + infoRuta.getDistancia().replace("\"", "")));
                        documento.add(new Paragraph("Tiempo estimado: " + infoRuta.getTiempo().replace("\"", "")));
                        documento.add(new Paragraph("-------Recorridos-----"));
                        documento.add(new Paragraph(" "));
                        tabla.addCell(infoRuta.getDistancia().replace("\"", ""));
                        tabla.addCell(infoRuta.getTiempo().replace("\"", ""));

                        rutaSalida.setRecorridos(obtenerRecoRuta(usuario, fechaIni, fechaFin, miRuta.getNombre()));
                        documento.add(new Paragraph("---------------------"));
                        documento.add(new Paragraph(" "));
                        rta.add(rutaSalida);
                        res = ConstruyeRespuesta.construyeRespuestaOk();
                        //res.setDatos(rta);

                        documento.add(tabla);
                    }

                }
                documento.add(new Paragraph(" "));
                try {
                    Image foto = Image.getInstance("http://1.bp.blogspot.com/-fV-ThFg9bN0/UCr4VMFrJ-I/AAAAAAAAEYQ/-_vIDIYDLz8/s1600/dibujo-pintar-doki-bicicleta.jpg");
                    foto.scaleToFit(100, 100);
                    foto.setAlignment(Chunk.ALIGN_CENTER);
                    documento.add(foto);
                } catch (IOException | DocumentException e) {
                }
                documento.close();

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
    public RespuestaJson exportarReporteMetricasUsuario(String usuario, String fechaIni, String fechaFin, String archivo) {
        RespuestaJson res = new RespuestaJson();
        res = getRutasDAO().exportarReporteMetricasUsuario(usuario,fechaIni,fechaFin,archivo);
        
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

        
        Query query = em.createNamedQuery("Ruta.findMetricasByUsuario");
        query.setParameter("usuario", usuario);
        query.setParameter("fechaIni", fechaUno, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaDos, TemporalType.TIMESTAMP);
        List<Object[]> qresul = query.getResultList();

        if (qresul.size() > 0) {

            try {

                // Se crea el documento
                Document documento = new Document();

                // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
                FileOutputStream ficheroPdf = null;
                //ficheroPdf = new FileOutputStream("/Users/jhony/Documents/Uni Andes/Fabricas/Bicitools/bicitools/reporte.pdf");

                DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
                Date fechaReporte = new Date();

                ficheroPdf = new FileOutputStream(archivo + "/Reporte_" + usuario + "_" + dateFormat.format(fechaReporte) + ".pdf");

                // Se asocia el documento al OutputStream y se indica que el espaciado entre
                // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

                // Se abre el documento.
                documento.open();

                documento.add(new Paragraph("Reporte de Actividad para " + usuario));
                documento.add(new Paragraph(" "));
                /*documento.add(new Paragraph("Este es el segundo y tiene una fuente rara",
                 FontFactory.getFont("arial", // fuente
                 22, // tamaño
                 Font.ITALIC, // estilo
                 BaseColor.CYAN)));             // color
                 */

                documento.add(new Paragraph("Fecha Inicial: " + fechaUno));
                documento.add(new Paragraph("Fecha Final: " + fechaDos));

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                PdfPTable tabla = new PdfPTable(3);
                tabla.addCell("Fecha");
                tabla.addCell("Tiempo");
                tabla.addCell("Distancia");

                int distanciaTotal = 0, tiempoTotal = 0;
                ArrayList<DatosMetricasReportesJson> rta = new ArrayList<>();
                ArrayList<DatosMetricasUsuarioJson> lista = new ArrayList<>();
                DatosMetricasReportesJson salida = new DatosMetricasReportesJson();
                MetricasUsuario total = new MetricasUsuario();
                lista.clear();

                for (Object[] object : qresul) {
                    Date fechaSalida = (Date) object[2];

                    // Create an instance of SimpleDateFormat used for formatting
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String reportDate = df.format(fechaSalida);

                    tabla.addCell(reportDate);
                    tabla.addCell((String) object[1]);
                    tabla.addCell((String) object[0]);

                    distanciaTotal += Integer.parseInt((String) object[0]);
                    tiempoTotal += Integer.parseInt((String) object[1]);

                }

                documento.add(tabla);

                documento.add(new Paragraph(" "));

                documento.add(new Paragraph("Tiempo Total: " + tiempoTotal));
                documento.add(new Paragraph("Distancia Total: " + distanciaTotal));

                documento.add(new Paragraph(" "));
                try {
                    Image foto = Image.getInstance("http://1.bp.blogspot.com/-fV-ThFg9bN0/UCr4VMFrJ-I/AAAAAAAAEYQ/-_vIDIYDLz8/s1600/dibujo-pintar-doki-bicicleta.jpg");
                    foto.scaleToFit(100, 100);
                    foto.setAlignment(Chunk.ALIGN_CENTER);
                    documento.add(foto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                res = ConstruyeRespuesta.construyeRespuestaOk();

                documento.close();
                //res.setDatos(rta);

            } catch (DocumentException ex) {
                Logger.getLogger(RutasDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RutasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla("no hay datos " + qresul.size());
        }

        return res;
    }
    
    
}
