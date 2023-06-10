package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jsoup.nodes.Document;


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

        Document doc = siteParser.addTMMarkTo6LetterWords(BASE_URL, path);
//        Element baseUrlElement = doc.select("base-uri").first();
//
//        String baseUrl = url.toString();
//        baseUrlElement.setBaseUri(baseUrl)
        return doc.toString();
    }
}
