package com.bicitools.pruebarest;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.dao.DomiciliarioDAOLocal;
import com.bicitools.dao.ServicioDomicilioDAOLocal;
import com.bicitools.mjson.InfoHorarioJson;
import com.bicitools.mjson.InfoEntregaJson;
import com.bicitools.mjson.InfoDomiciliarioJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.DatosEntradaServicioJson;
import com.bicitools.mjson.DatosSalidaServicioJson;
import com.bicitools.mjson.DatosCalificacionServicioJson;
import com.bicitools.mjson.ServicioJson;
import com.bicitools.mjson.CalificacionServicioJson;
import com.bicitools.mjson.CalificacionUsuarioServicioJson;
import com.bicitools.mjson.TemporalJson;
import com.bicitools.mjson.UsuarioJson;
import com.bicitools.mjson.validator.ValidadorCampos;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/domicilios")
@Stateless
public class Domicilios {

    // DAO para el servicio de inscriir y actualizar a un domiciliario
    @EJB
    private DomiciliarioDAOLocal domiciliario;

    @EJB
    private ServicioDomicilioDAOLocal servicio;

    /**
     * Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     *
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("/print/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public TemporalJson produceJSON(@PathParam("name") String name) {
        TemporalJson st = new TemporalJson();
        st.setPkColumn(1);
        st.setEstring(name);
        return st;
    }

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(TemporalJson dato) {
        RespuestaJson re = new RespuestaJson(0, "Info", "Ok");
        String output = dato.toString();
        // return new Respuesta(0,"Ok","Procesado");
        return Response.status(200).entity(re.toString()).build();
    }

    @POST
    @Path("/inscribirDomiciliario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson inscribirDomiciliario(InfoDomiciliarioJson info) {
        String mensaje = null;

        RespuestaJson res;
        for (InfoHorarioJson dato : info.getHorario()) {

            if (!ValidadorCampos.esHoraValida(dato.getInicio())) {
                mensaje = MessagesBicitools.getMessage("erCampoInicio");
            }
            if (!ValidadorCampos.esHoraValida(dato.getFin())) {
                mensaje = MessagesBicitools.getMessage("erCampoFin");
            }
            if (!ValidadorCampos.esDiaValido(dato.getDia())) {
                mensaje = MessagesBicitools.getMessage("erCampoDia");
            }
            if (!ValidadorCampos.esEmailValido(info.getEmail())) {
                mensaje = MessagesBicitools.getMessage("erCampoEmail");
            }

        }
        if (mensaje == null) {

            res = domiciliario.creaDomiciliario(info);

        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
        }

        return res;
    }

    @POST
    @Path("/actualizarDomiciliario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson actualizarDomiciliario(InfoDomiciliarioJson info) {
        String mensaje = null;

        RespuestaJson res;
        for (InfoHorarioJson dato : info.getHorario()) {

            if (!ValidadorCampos.esHoraValida(dato.getInicio())) {
                mensaje = MessagesBicitools.getMessage("erCampoInicio");
            }
            if (!ValidadorCampos.esHoraValida(dato.getFin())) {
                mensaje = MessagesBicitools.getMessage("erCampoFin");
            }
            if (!ValidadorCampos.esDiaValido(dato.getDia())) {
                mensaje = MessagesBicitools.getMessage("erCampoDia");
            }
            if (!ValidadorCampos.esEmailValido(info.getEmail())) {
                mensaje = MessagesBicitools.getMessage("erCampoEmail");
            }
        }
        if (mensaje == null) {

            res = domiciliario.actualizaDomiciliario(info);

        } else {
            res = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
        }

        return res;
    }

    @POST
    @Path("/pedirServicioDomicilio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson pedirServicioDomicilio(InfoEntregaJson deliver) {
        RespuestaJson r = new RespuestaJson();
        try {
            r = servicio.creaServicio(deliver);
        } catch (Exception ex) {
            r = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return r;
    }

    @POST
    @Path("/enviarSolicitudDomiciliarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson enviarSolicitudDomiciliarios(ServicioJson deliver) {
        RespuestaJson r = new RespuestaJson();
        try {
            r = servicio.enviaSolicitud(deliver);
        } catch (Exception ex) {
            r = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return r;
    }

    @POST
    @Path("/aceptaServicioDomicilio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson aceptaServicioDomicilio(DatosEntradaServicioJson info) {
        String mensaje = null;
        RespuestaJson r;
        if (!ValidadorCampos.esHoraValida(info.getEstimado())) {
            mensaje = MessagesBicitools.getMessage("erCampoInicio");
        }

        if (mensaje == null) {

            r = servicio.aceptaServicio(info);

        } else {
            r = ConstruyeRespuesta.construyeRespuestaFalla(mensaje);
        }
        return r;
    }

    @POST
    @Path("/cancelarServicioDomicilio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson cancelarServicioDomicilio(ServicioJson deliver) {

        RespuestaJson r = new RespuestaJson();
        try {
            r = servicio.cancelaServicio(deliver);
        } catch (Exception ex) {
            r = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return r;
    }

    @POST
    @Path("/obtenerDetalleDomiciliario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerDetalleDomiciliario(UsuarioJson deliver
    ) {
        
        RespuestaJson r ;
        try {
            r = domiciliario.detalleDomiciliario(deliver);
        } catch (Exception ex) {
            r = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
        }
        return r;
    }

    @POST
    @Path("/registrarServicioDomicilio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson registrarServicioDomicilio(DatosSalidaServicioJson info) {
        RespuestaJson r = new RespuestaJson();
        if (!ValidadorCampos.esHoraValida(info.getReal())) {
            r = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erCampoReal"));

        } else {
            try {
                r = servicio.registraServicio(info);
            } catch (Exception ex) {
                r = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
            }
        }
        return r;

    }

    @POST
    @Path("/calificarDomiciliario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson calificarDomiciliario(CalificacionUsuarioServicioJson info) {
        RespuestaJson r = new RespuestaJson();
        if (!ValidadorCampos.esPuntosValido(info.getPuntos() )) {
            r = ConstruyeRespuesta.construyeRespuestaFalla(MessagesBicitools.getMessage("erCampoPunto"));

        } else {
            try {
                r = servicio.calificaServicio(info);
            } catch (Exception ex) {
                r = ConstruyeRespuesta.construyeRespuestaFalla(ex.getMessage());
            }
        }
        return r;
    }

}
