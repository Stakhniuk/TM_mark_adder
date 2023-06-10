package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


@Path("")
public class Resource {

    private static String BASE_URL = "https://quarkus.io/";

    @Inject
    SiteParser siteParser;

    @GET
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    public String returnAllOther() {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
//        client.target()
//        return doc.toString();
        return null;
    }

    @GET
    @Path("{path:.*}")
    @Consumes()
    @Produces(MediaType.TEXT_HTML)
    public String returnDocument(@PathParam("path") String path) {

        Document doc = getPage(BASE_URL, path);
        Document docWithTm = siteParser.addTMMarkTo6LetterWords(doc);
//        Element baseUrlElement = doc.select("base-uri").first();
//
//        String baseUrl = url.toString();
//        baseUrlElement.setBaseUri(baseUrl)
        return docWithTm.toString();
    }

    private Document getPage(String baseUrl, String path){
        URL url;
        try {
            url = new URL(baseUrl + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }

        Document doc;
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return doc;
    }
}
