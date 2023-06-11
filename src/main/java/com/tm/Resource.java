package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("")
public class Resource {

    private static String BASE_URL = "https://quarkus.io/";
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Client client;

    @Inject
    SiteParser siteParser;

    @GET
    @Path("{path:.*}")
    public String returnDocument(@PathParam("path") String path) throws URISyntaxException{

        URL url;
        try {
            url = new URL(BASE_URL + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }

        client = ClientBuilder.newBuilder()
//                .property("org.jboss.resteasy.jaxrs.client.proxy.host", "127.0.0.1")
//                .property("org.jboss.resteasy.jaxrs.client.proxy.port", "8080")
                .executorService(executorService)
                .build();

        Response response = client.target(url.toString())
                .request()
                .get();

        if((response.getHeaderString("Content-Type").startsWith("text/"))) {
           return siteParser.addTMMarkTo6LetterWords(url.toString()).toString();
        }

        return response.toString();
    }
}
