package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


@Path("")
public class Resource {

    private static String BASE_URL = "https://quarkus.io/";

    @Inject
    SiteParser siteParser;

    @GET
    @Path("{path:.*}")
    public byte[] returnDocument(@PathParam("path") String path) {

        URL url;
        try {
            url = new URL(BASE_URL + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }

        Connection.Response resp;
        try {
            resp = Jsoup.connect(url.toString())
                    .ignoreContentType(true).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if ((resp.contentType().startsWith("text/html"))) {
            return siteParser.addTMMarkTo6LetterWords(url.toString()).toString().getBytes();
        }

        return resp.bodyAsBytes();
    }
}
