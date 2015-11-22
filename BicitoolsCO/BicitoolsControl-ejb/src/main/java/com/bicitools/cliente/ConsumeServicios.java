/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.cliente;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.common.MessagesBicitools;

import com.bicitools.mjson.RespuestaJson;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 *
 * @author Ludwing
 */
public class ConsumeServicios {
     public static RespuestaJson consume1(String endpoint, Object st) {
        RespuestaJson output;
        try {
            
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(
            JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            WebResource webResource = client
                    .resource(MessagesBicitools.getEndPoint(endpoint));
            
            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, st);

            if (response.getStatus() != 200) {
                output=ConstruyeRespuesta.construyeRespuestaFalla("Fallo 200");

            }
            output = response.getEntity(RespuestaJson.class);
           
           
        } catch (Exception e) {
            output=ConstruyeRespuesta.construyeRespuestaFalla("Fallo");
            e.printStackTrace();
        }
        return output;
    }
    
}
