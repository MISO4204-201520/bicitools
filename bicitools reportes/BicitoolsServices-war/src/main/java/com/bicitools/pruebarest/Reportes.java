package com.bicitools.pruebarest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.dao.NotificacionesDAOLocal;
import com.bicitools.dao.RutasDAOLocal;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.validator.NullValidator;
import com.bicitools.entity.Ruta;
import com.bicitools.mjson.DatosRutasReportesJson;
import com.bicitools.mjson.InfoRepoCantNotificaJson;
import com.bicitools.mjson.InfoRepoExportarJson;
import com.bicitools.mjson.InfoRepoGenJson;
import com.bicitools.mjson.ValidaInfoReportesJson;
import com.bicitools.mjson.DatosLugaresJson;
import com.bicitools.mjson.DatosMetricasReportesJson;
import com.bicitools.mjson.DatosMetricasUsuarioJson;
import com.bicitools.mjson.DatosNotificacionesReportesJson;
import com.bicitools.mjson.DatosNuevaNotificacionJson;
import com.bicitools.mjson.MetricasUsuario;
import com.bicitools.mjson.RutasUsuarioJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * REST Web Service
 *
 * @author jhony
 */
@Path("/reportes")
@Stateless
public class Reportes {

    @EJB
    //public RutasFacadeImpl rutasFacade;
    RutasDAOLocal rutasSesion;

    @EJB
    //public RutasFacadeImpl rutasFacade;
    NotificacionesDAOLocal notificacionSesion;

    /**
     * Retrieves representation of an instance of
     * com.bicitools.Reportes.servicios.Reportes
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/consultarRutasFrecuentes")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson consultarRutasFrecuentes(String json) {
        String mensaje = null;

        RespuestaJson res;

        try {

            Gson gson = new Gson();

            InfoRepoGenJson info = gson.fromJson(json, InfoRepoGenJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {
                String usuario = info.getUsuario();
                String datoIni = info.getInicio();
                String datoFin = info.getFin();

                try {

                    //consulta ruta                    
                    if (datoIni == "") {
                        datoIni = new Date().toString();
                    }
                    if (datoFin == "") {
                        datoFin = new Date().toString();
                    }
                    res = rutasSesion.obtenerRutasUsuarioFechas(usuario, datoIni, datoFin);

                } catch (Exception ex) {
                    mensaje = ex.getMessage();
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);

                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        //final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        //final String representacionBonita = prettyGson.toJson(res);
        return res;
    }

    @POST
    @Path("/consultarRecorridosUsuario")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson consultarRecorridos(String json) {
        String mensaje = null;

        RespuestaJson res;

        try {

            Gson gson = new Gson();

            InfoRepoGenJson info = gson.fromJson(json, InfoRepoGenJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {
                String usuario = info.getUsuario();
                String datoIni = info.getInicio();
                String datoFin = info.getFin();

                try {

                    //consulta ruta                    
                    if (datoIni == "") {
                        datoIni = new Date().toString();
                    }
                    if (datoFin == "") {
                        datoFin = new Date().toString();
                    }
                    res = rutasSesion.obtenerRecorridosUsuarioFechas(usuario, datoIni, datoFin);

                } catch (Exception ex) {
                    mensaje = ex.getMessage();
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);

                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        //final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        //final String representacionBonita = prettyGson.toJson(res);
        return res;
    }

 
    
    @POST
    @Path("/consultarRecorridosRuta")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson consultarRecorridosRuta(String json) {
        String mensaje = null;

        RespuestaJson res;

        try {

            Gson gson = new Gson();

            InfoRepoGenJson info = gson.fromJson(json, InfoRepoGenJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {
                String usuario = info.getUsuario();
                String datoIni = info.getInicio();
                String datoFin = info.getFin();

                try {

                    //consulta ruta                    
                    if (datoIni == "") {
                        datoIni = new Date().toString();
                    }
                    if (datoFin == "") {
                        datoFin = new Date().toString();
                    }
                    res = rutasSesion.obtenerRecorridosRuta(usuario, datoIni, datoFin);

                } catch (Exception ex) {
                    mensaje = ex.getMessage();
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);

                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        //final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        //final String representacionBonita = prettyGson.toJson(res);
        return res;
    }

    
    
    /**
     * Retrieves representation of an instance of
     * com.bicitools.Reportes.servicios.Reportes
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/RutaUsuario")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson InsertarRutaUsuario(String json) {
        String mensaje = null;

        RespuestaJson res;

        try {

            Gson gson = new Gson();

            RutasUsuarioJson info = gson.fromJson(json, RutasUsuarioJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {

                try {

                    //insertar como ruta
                    res = rutasSesion.crearRutasUsuario(info);

                } catch (Exception ex) {
                    mensaje = ex.getMessage();
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);

                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    /**
     * Retrieves representation of an instance of
     * com.bicitools.Reportes.servicios.Reportes
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/RecorridoUsuario")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson InsertarRecorridoUsuario(String json) {
        String mensaje = null;

        RespuestaJson res;

        try {

            Gson gson = new Gson();

            RutasUsuarioJson info = gson.fromJson(json, RutasUsuarioJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {

                try {

                    //insertar como recorrido
                    res = rutasSesion.crearRecorridoUsuario(info);

                } catch (Exception ex) {
                    mensaje = ex.getMessage();
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);

                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    
    @POST
    @Path("/metricasUsuario")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson consultarMEtricas(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
            InfoRepoGenJson info = gson.fromJson(json, InfoRepoGenJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {
                String usuario = info.getUsuario();
                String datoIni = info.getInicio();
                String datoFin = info.getFin();

                try {

                    //consulta ruta                    
                    if (datoIni == "") {
                        datoIni = new Date().toString();
                    }
                    if (datoFin == "") {
                        datoFin = new Date().toString();
                    }
                    res = rutasSesion.obtenerMetricasUsuario(usuario, datoIni, datoFin);
                    
                    
                    

                } catch (Exception ex) {
                    mensaje = ex.getMessage();
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
                }
            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    @POST
    @Path("/reporteLocal")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson exportarReporte(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
            InfoRepoExportarJson info = gson.fromJson(json, InfoRepoExportarJson.class);

            mensaje = NullValidator.validarCampos(info);

            if (mensaje == null) {
                
                if("1".equals(info.getTipoReporte()))
                res = rutasSesion.exportarRutasUsuario(info.getUsuario(),
                                                                info.getInicio(),
                                                                info.getFin(),
                                                                info.getRutaArchivo());
                else if("2".equals(info.getTipoReporte()))
                res = rutasSesion.exportarRecorridosUsuario(info.getUsuario(),
                                                                info.getInicio(),
                                                                info.getFin(),
                                                                info.getRutaArchivo());
                
                else if ("3".equals(info.getTipoReporte()))
                    res = rutasSesion.exportarRecorridosRuta(info.getUsuario(),
                            info.getInicio(),
                            info.getFin(),
                            info.getRutaArchivo());
                else if ("4".equals(info.getTipoReporte()))
                    res = rutasSesion.exportarReporteMetricasUsuario(info.getUsuario(),
                            info.getInicio(),
                            info.getFin(),
                            info.getRutaArchivo());

                else
                    res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
                    
                /*rutaArchivo = "c://miFile.txt";
                salida.add(rutaArchivo);
                res = new RespuestaJson();
                res.setDatos(salida);*/
                //res = domiciliario.creaDomiciliario(info);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    @POST
    @Path("/consultarCantNotificaciones")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson consultarCantNotificaciones(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {

            Gson gson = new Gson();
            InfoRepoCantNotificaJson info = gson.fromJson(json, InfoRepoCantNotificaJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {
                String usuario = info.getUsuario();
                String datoIni = info.getInicio();
                String datoFin = info.getFin();

                res = notificacionSesion.obtenerNotoficacionesUsuario(info);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    @POST
    @Path("/insertarNotificacion")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson insertarNotificacion(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {

            Gson gson = new Gson();
            DatosNuevaNotificacionJson info = gson.fromJson(json, DatosNuevaNotificacionJson.class);

            mensaje = NullValidator.validarCampos(info);
            if (mensaje == null) {
                String usuario = info.getUsuario();
                String tipo = info.getTipo();

                try {
                    Integer.parseInt(tipo);
                } catch (Exception ex) {
                    tipo = "1";
                }

                res = notificacionSesion.registrarNotificacion(usuario, tipo);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    /**
     * Retrieves representation of an instance of
     * com.bicitools.reportes.servicios.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        int i = 0;
        i++;
        return "Hola mundo";
        //throw new UnsupportedOperationException();
    }
    
    @POST
    //@Produces("application/json")
    public String getJson2() {
        //TODO return proper representation object
        int i = 0;
        i++;
        
                
        return "Hola mundo";
        //throw new UnsupportedOperationException();
    }
}
