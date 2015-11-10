package com.bicitools.pruebarest;

import com.bicitools.dao.RutaDAO;
import com.bicitools.dao.RutaDAOLocal;
import com.bicitools.dao.TemporalDAOLocal;
import com.bicitools.entity.Ruta;
import com.bicitools.entity.RutaPunto;
import com.bicitools.entity.UsuarioRuta;
import com.bicitools.mjson.Respuesta;
import com.bicitools.mjson.Student;
import com.bicitools.mjson.*;
import com.bicitools.mjson.googleJson.Geocoding;
import com.bicitools.mjson.googleJson.GoogleResponseRoute;
import com.bicitools.mjson.googleJson.Location;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import static com.sun.jersey.core.header.LinkHeader.uri;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
@Stateless
public class MyResource {

    @EJB
    private TemporalDAOLocal m;
    @EJB(name="RutaDAOLocal")
    private RutaDAOLocal r;

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
        Respuesta re = new Respuesta(0, "Info", "Ok");
        String output = dato.toString();
        // return new Respuesta(0,"Ok","Procesado");
        return Response.status(200).entity(re.toString()).build();
    }
//s7 ' 246685
    @POST
    @Path("/CrearRuta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CrearRuta(CrearRutaJson temporal) {
        System.out.println(temporal.getNombre());
        r.creaRuta((temporal.getNombre()), temporal.getUsuario());
        Respuesta re = new Respuesta(0, "Info", "Ok");
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
        //Response.status(200).entity(re).build();
    }
  @POST
    @Path("/AgregarPuntoARuta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AgregarPuntoARuta(AgregarPuntoARutaJson temporal) {
        r.creaRutaPunto((temporal.getNombre()), temporal.getLatitudOrigen(),temporal.getLongitudOrigen());
        Respuesta re = new Respuesta(0, "Info", "Ok");
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build(); 
        //Response.status(200).entity(re).build();
    }

    
  @POST
    @Path("/AgregarUsuarioARuta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AgregarUsuarioARuta(AgregarUsuarioARutaJson temporal) {
     App app = App.getInstance();
      Respuesta re = null;
      if (app.prop.getProperty("DesplazamientoGrupal")  == "Y" ) {
       
        System.out.println(temporal.getNombre());
        r.creaUsuarioARuta((temporal.getNombre()), temporal.getUsuario());
        re = new Respuesta(0, "Info", "Ok");
      }
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
        //Response.status(200).entity(re).build();
    }
    
    @POST
    @Path("/ObtenerDistanciaTiempoRuta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ObtenerDistanciaTiempoRuta(OrigenDestinoJson OriDest) {
         GoogleResponseRoute response2 = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(
                    UriBuilder.fromUri(
                            "http://maps.googleapis.com/maps/api/directions/json?origin=" + OriDest.getLatitudOrigen() + "," + OriDest.getLongitudOrigen() + "&destination=" + OriDest.getLatitudDestino() + "," + OriDest.getLongitudDestino() + "&sensor=false&units=metric&mode=driving"));
             response2 = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(GoogleResponseRoute.class);

             System.out.println("Output from Server 0 ." + response2);

      

        } catch (Exception e) {

            e.printStackTrace();

        }

        System.out.println("asd asd ");
        RespuestaObtenerDistanciaTiempoRuta re = new RespuestaObtenerDistanciaTiempoRuta(0, "Info", "Ok",new DistanciaTiempoRuta(response2.getRoutes()[0].getLegs()[0].getDistance().getText(),response2.getRoutes()[0].getLegs()[0].getDuration().getText()));
          ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
    @POST
    @Path("/ObtenerPuntosLatLongRuta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ObtenerPuntosLatLongRuta(OrigenDestinoJson OriDest) {
         GoogleResponseRoute response2 = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(
                    UriBuilder.fromUri(
                            "http://maps.googleapis.com/maps/api/directions/json?origin=" + OriDest.getLatitudOrigen() + "," + OriDest.getLongitudOrigen() + "&destination=" + OriDest.getLatitudDestino() + "," + OriDest.getLongitudDestino() + "&sensor=false&units=metric&mode=driving"));
             response2 = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(GoogleResponseRoute.class);

             System.out.println("Output from Server ." + response2);

      

        } catch (Exception e) {

            e.printStackTrace();

        }
        RespuestaObtenerPuntosLatLongRuta re = new RespuestaObtenerPuntosLatLongRuta(0, "Info", "Ok",response2.getRoutes()[0].getLegs()[0].getSteps());
          ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
    
    @POST
    @Path("/ObtenerCoordenadasPorSitioDireccion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ObtenerCoordenadasPorSitioDireccion(ObtenerCoordenadasJson obtCoord) {
         Geocoding response2 = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(
                    UriBuilder.fromUri(
                            "https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(obtCoord.getSitioDireccion(), "UTF-8")+"&region=co"));
             response2 = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get(Geocoding.class);

             System.out.println("Output from Server 2." + response2);/*
https://maps.googleapis.com/maps/api/geocode/json?address="+obtCoord.getSitioDireccion()+"&region=co */
      

        } catch (Exception e) {

            e.printStackTrace();

        }

          ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(response2.getResults()[0].getGeometry().getLocation());
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
 
    @GET
    @Path("/ObtenerRutas/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ObtenerRutas(@PathParam("usuario") String usuario) {
         
        List<Ruta> rutaByUsuario;
        rutaByUsuario = r.getRutaByUsuario(usuario);
        RespuestaObtenerRuta re = new RespuestaObtenerRuta(0, "Info", "Ok",rutaByUsuario.toArray());
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
    } 
    
     @GET
    @Path("/ObtenerPuntosRuta/{ruta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ObtenerPuntosRuta(@PathParam("ruta") String ruta) {
         
        List<RutaPunto> puntosRuta;
        puntosRuta = r.getPuntosRuta(ruta);
         System.out.println("sz" + puntosRuta.size());
        RespuestaObtenerRuta re = new RespuestaObtenerRuta(0, "Info", "Ok",puntosRuta.toArray());
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build(); 
    } 
    
      @GET
    @Path("/ObtenerUsuariosRuta/{ruta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ObtenerUsuariosRuta(@PathParam("ruta") String ruta) {
       App app = App.getInstance();
        List<UsuarioRuta> usuarioRuta = null;
        if (app.prop.getProperty("DesplazamientoGrupal")  == "Y" ) {
         
        usuarioRuta = r.getUsuarioRuta(ruta);
        }
        RespuestaObtenerRuta re = new RespuestaObtenerRuta(0, "Info", "Ok",usuarioRuta.toArray());
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build(); 
        
    } 
    @POST
    @Path("/AgregarLogUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AgregarPuntoARuta(AgregarLogUsuarioJson temporal) {
        r.creaRutaPunto((temporal.getNombre()), temporal.getLatitudOrigen(),temporal.getLongitudOrigen());
        Respuesta re = new Respuesta(0, "Info", "Ok");
        ResponseBuilder builder = new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build(); 
        //Response.status(200).entity(re).build();
    }
}
    