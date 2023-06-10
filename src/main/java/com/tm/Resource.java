package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jsoup.nodes.Document;

import java.net.MalformedURLException;
import java.net.URL;

@Path("")
public class Resource {

    private static String BASE_URL = "https://quarkus.io/";

    @Inject
    SiteParser siteParser;

//    @GET
//    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
//    public String returnAllOther() {
//        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
////        client.target()
////        return doc.toString();
//    }

    @GET
    @Path("{path:.*}")
    @Produces(MediaType.TEXT_HTML)
    public String returnDocument(@PathParam("path") String path) {
        URL url;
        try {
            url = new URL("https://quarkus.io/" + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }
        Document doc = siteParser.addTMMarkTo6LetterWords(url.toString());
//        Element baseUrlElement = doc.select("base-uri").first();
//
//        String baseUrl = url.toString();
//        baseUrlElement.setBaseUri(baseUrl);
        return doc.toString();
    }
}
