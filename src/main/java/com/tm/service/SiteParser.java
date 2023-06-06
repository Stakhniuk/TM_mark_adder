package com.tm.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SiteParser {

    public SiteParser() throws IOException {
    }

    String url = "https://quarkus.io/";
    Document doc = Jsoup.connect(url).get();

    Elements body = doc.select("body");

    String text = body.text();

}
