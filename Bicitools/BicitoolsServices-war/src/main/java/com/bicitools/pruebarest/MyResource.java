package com.bicitools.pruebarest;

import com.bicitools.dao.TemporalDAOLocal;
import com.bicitools.mjson.Respuesta;
import com.bicitools.mjson.Student;
import com.bicitools.mjson.TemporalJson;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
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

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
@Stateless
public class MyResource {

    @EJB
    private TemporalDAOLocal m;
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
        Respuesta re = new Respuesta(0,"Info","Ok");
        String output = dato.toString();
       // return new Respuesta(0,"Ok","Procesado");
        return Response.status(200).entity(re.toString()).build();
    }
    
    @POST
    @Path("/crea")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaTemporal(TemporalJson temporal) {
        m.creaTemp(new Integer(temporal.getPkColumn()), temporal.getEstring());
        Respuesta re = new Respuesta(0,"Info","Ok");
        ResponseBuilder builder=new ResponseBuilderImpl();
        builder.entity(re);
        builder.status(Status.OK);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
                //Response.status(200).entity(re).build();
    }
}
