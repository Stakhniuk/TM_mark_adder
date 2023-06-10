package com.tm.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.util.logging.Logger;

@ApplicationScoped
public class SiteParser {

    Logger LOG = Logger.getLogger(SiteParser.class.getName());

    final String regex = "\\b[A-Za-z0-9]{6}\\b";

    public Document addTMMarkTo6LetterWords(String baseUrl, String path) {
        URL url;
        try {
            url = new URL("https://quarkus.io/" + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid/broken URL");
        }

        Document doc;
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            throw new RuntimeException("Page parsing error, ⟨™⟩ symbol cannot be added.");
        }

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        Elements els = doc.body().getAllElements();
        for (Element e : els) {
            for (Node child : e.childNodes()) {
                if (child instanceof TextNode && !((TextNode) child).isBlank()) {
                    Matcher matcher = pattern.matcher(((TextNode) child).text());
                    ((TextNode) child).text(matcher.replaceAll(word -> word.group() + "™"));
                }
            }
        }
        return doc;
    }
}
