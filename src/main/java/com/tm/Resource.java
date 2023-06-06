package com.tm;

import com.tm.service.SiteParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class Resource {

    @Inject
    SiteParser siteParser;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return siteParser.addTMMarkTo6LetterWords();
    }
}
