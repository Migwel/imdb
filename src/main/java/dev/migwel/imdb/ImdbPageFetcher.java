package dev.migwel.imdb;

import org.jsoup.Jsoup;

import java.io.IOException;

public class ImdbPageFetcher implements PageFetcher {
    private final static String imdbLink = "https://www.imdb.com/search/title/?genres=%s&start=%s&ref_=adv_nxt";


    private final String url;

    public ImdbPageFetcher(String genres, int start) {
        this.url =  String.format(imdbLink, genres, start);
    }

    public String fetchPage() {
        try {
            return Jsoup.connect(url).get().html();
        } catch (IOException e) {
            return "";
        }
    }
}
