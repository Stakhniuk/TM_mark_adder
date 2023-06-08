package com.tm.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Logger;

@ApplicationScoped
public class SiteParser {

    Logger LOG = Logger.getLogger(SiteParser.class.getName());

    final String regex = "\\b[A-Za-z0-9]{6}\\b";

    public String addTMMarkTo6LetterWords() {
        Document doc;
        try {
            String url = "https://quarkus.io/";
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("⟨™⟩ symbol cannot be added.");
        }

        Elements body = doc.select("body");
        String html = body.text();
        String resultHTML = html.replaceAll(regex, "⟨™⟩");

//        LOG.info(resultHTML);

        return resultHTML;
    }
}
