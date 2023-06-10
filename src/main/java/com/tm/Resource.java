package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


@Path("")
public class Resource {

    private static String BASE_URL = "https://quarkus.io/";

    @Inject
    SiteParser siteParser;

    @GET
    @Path("{path:.*}")
    public String returnDocument(@PathParam("path") String path) {

        URL url;
        try {
            url = new URL(BASE_URL + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }

        Connection.Response resp;
        try {
            resp = Jsoup.connect(url.toString()).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if((Arrays.asList("text/html; charset=utf-8").contains(resp.contentType()))) {
           return siteParser.addTMMarkTo6LetterWords(url.toString()).toString();
        }

        return resp.body().toString();
    }
}
