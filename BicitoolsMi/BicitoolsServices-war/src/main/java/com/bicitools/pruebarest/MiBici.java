/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.pruebarest;

import com.bicitools.mjson.InfoInsertaProductoJson;
import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.dao.OrdenesDAOLocal;
import com.bicitools.dao.PartesDAOLocal;
import com.bicitools.dao.ProveedoresDAOLocal;
import com.bicitools.mibici.general.*;
import com.bicitools.mjson.InfoConfigMiBiciJson;
import com.bicitools.mjson.InfoConsultaPartesJson;
import com.bicitools.mjson.InfoInsertaProvJson;
import com.bicitools.mjson.InfoOrdenJson;
import com.bicitools.mjson.InfoRolProveedorJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.validator.NullValidator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jhony
 */
@Path("/miBici")
@Stateless




public class MiBici {
    @EJB
    PartesDAOLocal parteDAO;

    @EJB
    ProveedoresDAOLocal proveedorDAO;
    
    @EJB
    OrdenesDAOLocal ordenDAO;
    
    /**
     * Creates a new instance of MiMici
     */
    public MiBici() {
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //ya 2 opciones
    @Path("/verificarConfiguracion")
    public RespuestaJson VerificarConfiguracion(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();

            InfoConfigMiBiciJson info = gson.fromJson(json, InfoConfigMiBiciJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
                res = ordenDAO.validarOrden(info);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/consultarPartexTipo")
    public RespuestaJson consultarPartexTipo(String json) {
        String mensaje = null;
        RespuestaJson res;
          
        try {
            Gson gson = new Gson();

            InfoConsultaPartesJson info = gson.fromJson(json, InfoConsultaPartesJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
                res = parteDAO.obtenerPartesPorTipo(info);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }
    
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/consultarProveedorxParte")
    public RespuestaJson consultarProveedorxParte(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
                    
            InfoEntradaGeneralJson info = gson.fromJson(json, InfoEntradaGeneralJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
                         
            String usuario = info.getUsuario();
            String idProducto = info.getIdProducto();
            }
            /*if (!ValidaEntradaJson.esFechaHoraValida(datoIni)) {
             mensaje = MessagesBicitools.getMessage("erCampoInicio");
             }
             if (!ValidaInfoReportesJson.esFechaHoraValida(datoFin)) {
             mensaje = MessagesBicitools.getMessage("erCampoFin");
             }*/
            if (mensaje == null) {
                res = new RespuestaJson();
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
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertarProveedor")
    public RespuestaJson insertarProveedor(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
                    
            InfoInsertaProvJson info = gson.fromJson(json, InfoInsertaProvJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
                   
            String usuario = info.getUsuario();
            
            }

            if (mensaje == null) {
                res = new RespuestaJson();
                //res = domiciliario.creaDomiciliario(info);
                res = proveedorDAO.crearProveedor(info);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertarProducto")
    public RespuestaJson insertarProducto(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
                    
            InfoInsertaProductoJson info = gson.fromJson(json, InfoInsertaProductoJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
                res = parteDAO.crearParte(info);
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
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validarRolProveedor")
    public RespuestaJson validarRolProveedor(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
                    
            InfoRolProveedorJson info = gson.fromJson(json, InfoRolProveedorJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
            String usuario = info.getUsuario();

                res = new RespuestaJson();
                res = proveedorDAO.verificarProveedor(info);
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
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/solicitarProducto")
    public RespuestaJson solicitarProducto(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
                    
            InfoEntradaGeneralJson info = gson.fromJson(json, InfoEntradaGeneralJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
            String usuario = info.getUsuario();
            }
            /*if (!ValidaEntradaJson.esFechaHoraValida(datoIni)) {
             mensaje = MessagesBicitools.getMessage("erCampoInicio");
             }
             if (!ValidaInfoReportesJson.esFechaHoraValida(datoFin)) {
             mensaje = MessagesBicitools.getMessage("erCampoFin");
             }*/
            if (mensaje == null) {
                res = new RespuestaJson();
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
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/misOrdenes")
    public RespuestaJson obtenerOrdensPorUsuario(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
            
            InfoRolProveedorJson info = gson.fromJson(json, InfoRolProveedorJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {
                
                res = ordenDAO.obtenerMisOrdenes(info);

            } else {
                res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }

        return res;
    }
    
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/detalleOrden")
    public RespuestaJson obtenerDetalleOrden(String json) {
        String mensaje = null;

        RespuestaJson res;
        try {
            Gson gson = new Gson();
            
            InfoOrdenJson info = gson.fromJson(json, InfoOrdenJson.class);
            
            mensaje =  NullValidator.validarCampos(info);
            if(mensaje == null)
            {   
                res = ordenDAO.obtenerDetallesOrden(info);

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
        return "Hola mundo Mibici";
        //throw new UnsupportedOperationException();
    }
}
