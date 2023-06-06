package com.tm.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class SiteParser {

    Logger LOG = Logger.getLogger(SiteParser.class.getName());

    private final String url = "https://quarkus.io/";
    final String regex = "^\\w{6}$";

    public String addTMMarkTo6LetterWords() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("⟨™⟩ symbol cannot be added.");
        }

        Elements body = doc.select("body");
        String html = body.text();
        String resultHTML = html.replaceAll(regex, "⟨™⟩");

        LOG.info(resultHTML);

        return html;
    }
}
