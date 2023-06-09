package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;

@Path("/")
public class Resource {

    @Inject
    SiteParser siteParser;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnDocument() {
        URL url;
        try {
            url = new URL("https://quarkus.io/");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }
        Document doc = siteParser.addTMMarkTo6LetterWords(url);

//        Element baseUrlElement = doc.select("base-uri").first();
//
//        String baseUrl = url.toString();
//        baseUrlElement.setBaseUri(baseUrl);
        return doc.toString();
    }
}
