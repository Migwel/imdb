package dev.migwel.imdb;

import dev.migwel.imdb.util.TextUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ImdbPageParser implements PageParser {

    private final PageFetcher fetcher;
    //Sometimes, the format is (II) (2014). I think (II) means it's a sequel
    //Something else that can happen is (2014 Video)
    //And also, (2014-2015)
    private Pattern p = Pattern.compile("(\\([A-Za-z0-9\\s]+\\) )?\\((?<year>[0-9]+).*\\)");

    public ImdbPageParser(PageFetcher fetcher) {
        this.fetcher = fetcher;
    }

    @Nonnull
    public Collection<Movie> parseMovies() {
        String html = fetcher.fetchPage();
        Document doc = Jsoup.parse(html);
        Elements filmElements = doc.select("div.lister-item-content");
        return extractFilms(filmElements);

    }

    private Collection<Movie> extractFilms(Elements filmElements) {
        Collection<Movie> movies = new ArrayList<>();
        for (Element el : filmElements) {
            Float rating = extractRatings(el);
            String filmName = extractFilmName(el);
            int releaseYear = extractReleaseYear(el);
            Integer duration = extractDuration(el);
            Collection<String> genres = extractGenres(el);
            movies.add(new Movie(filmName, releaseYear, genres, rating, duration));
        }
        return movies;
    }

    private Collection<String> extractGenres(Element el) {
        String genresStr = el.select("p.text-muted").select("span.genre").text();
        if (TextUtil.isEmptyOrNull(genresStr)) {
            return Collections.emptyList();
        }
        List<String> genres = Arrays.asList(genresStr.split(","));
        genres = genres.stream().map(String::toLowerCase).collect(Collectors.toList());
        return genres;
    }

    private Integer extractDuration(Element el) {
        String durationWithMinStr = el.select("p.text-muted").select("span.runtime").text();
        String durationStr = durationWithMinStr.split(" ")[0];
        if (TextUtil.isEmptyOrNull(durationStr)) {
            return null;
        }
        return Integer.parseInt(durationStr);
    }

    private int extractReleaseYear(Element el) {
        String releaseYearsAndNumber = el.select("h3.lister-item-header").select("span.lister-item-year").text();
        Matcher m = p.matcher(releaseYearsAndNumber);
        if(m.find()) {
            return Integer.parseInt(m.group("year"));
        }
        return -1; //It can happen that release years are in the format "2014-2015"
    }

    private String extractFilmName(Element el) {
        return el.select("h3.lister-item-header").select("a").text();
    }

    private Float extractRatings(Element el) {
        String ratingsString = el.select("div.ratings-bar").select("strong").text();
        if (TextUtil.isEmptyOrNull(ratingsString)) {
            return null;
        }
        return Float.parseFloat(ratingsString);
    }
}
