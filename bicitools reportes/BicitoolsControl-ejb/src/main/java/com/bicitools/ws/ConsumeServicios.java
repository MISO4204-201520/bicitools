/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.ws;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;
import com.bicitools.mjson.InfoRepoGenJson;
import com.bicitools.mjson.RespuestaJson;
import com.bicitools.mjson.RespuestaNuevoJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhony
 */
public class ConsumeServicios {

    public static RespuestaJson consumeTiempoDist(Object st) {
        RespuestaNuevoJson out = new RespuestaNuevoJson();
        RespuestaJson output = new RespuestaJson();
        try {

            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(
                    JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client
                    .resource("http://localhost:8080/bicitoolsRU/webresources/myresource/ObtenerDistanciaTiempoRuta");

            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, st);

            if (response.getStatus() != 200) {
                output = ConstruyeRespuesta.construyeRespuestaFalla("Fallo 200");

            }
            String json = response.getEntity(String.class);
            Gson gson = new Gson();

            JsonParser jParser = new JsonParser();
            JsonObject jObject = (JsonObject) jParser.parse(json);

            JsonElement elem = jObject.get("codigo");
            JsonElement elem2 = jObject.get("valor");
            JsonElement elem3 = jObject.get("data");
            JsonElement elem4 = jObject.get("descripcion");
            JsonObject jObjectInterno = (JsonObject) jParser.parse(elem3.toString());

            JsonElement elem5 = jObjectInterno.get("tiempo");
            JsonElement elem6 = jObjectInterno.get("distancia");

            TiempoDistanciaInfo t = new TiempoDistanciaInfo(elem5.toString(), elem6.toString());
            ArrayList datos = new ArrayList();

            //RespuestaNuevoJson info = gson.fromJson(json, RespuestaNuevoJson.class);
            //System.out.println("");
            output.setCodigo(Integer.parseInt(elem.toString()));
            output.setValor(elem2.toString());
            output.setDescripcion(elem4.toString());
            datos.add(t);
            output.setDatos(datos);
            output.setDescripcion(out.getDescripcion());

        } catch (Exception e) {
            output = ConstruyeRespuesta.construyeRespuestaFalla(e.getMessage());
            e.printStackTrace();
        }
        return output;
    }

}
