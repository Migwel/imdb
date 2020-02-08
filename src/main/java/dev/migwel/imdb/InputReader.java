package dev.migwel.imdb;

import dev.migwel.imdb.exception.InvalidInputException;
import dev.migwel.imdb.util.CollectionsUtil;
import dev.migwel.imdb.util.TextUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class InputReader {
    
    private final BufferedReader reader;
    
    public InputReader(InputStream inputStream) {
        var inputStreamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(inputStreamReader);
    }

    public Filter readInputs() throws InvalidInputException {
        String start = askStart();
        Collection<String> genres = askGenres();
        Collection<String> excludedGenres = askExcludedGenres();
        if (CollectionsUtil.isEmptyOrNull(genres)) {
            throw new InvalidInputException("Genre cannot be null");
        }
        Integer minimumDuration = askMinimumDuration();
        Integer maximumDuration = askMaximumDuration();
        Float minimumRating = askMinimumRating();

        return new Filter(start, genres, excludedGenres, minimumDuration, maximumDuration, minimumRating);
    }

    public Collection<String> askGenres() {
        System.out.println("Which genre do you want to look for? (If multiple, give a comma-separated list)");
        String genre = readString();
        if (TextUtil.isEmptyOrNull(genre)) {
            System.out.println("Please give a genre to look for");
            return Collections.emptyList();
        }
        String[] genres = genre.replaceAll(" ", "").split(",");
        return Arrays.asList(genres);
    }

    public String askStart() {
        System.out.println("From which number do you want the search to start? (Default: 0)");
        String start = readString();
        if(TextUtil.isEmptyOrNull(start)) {
            start = "0";
        }
        return start;
    }

    public Integer askMinimumDuration() {
        System.out.println("Do you want a minimum duration (in minutes)?");
        String minimumDuration = readString();
        if(TextUtil.isEmptyOrNull(minimumDuration)) {
            return null;
        }
        return Integer.valueOf(minimumDuration);
    }

    public Integer askMaximumDuration() {
        System.out.println("Do you want a maximum duration (in minutes)?");
        String maximumDuration = readString();
        if(TextUtil.isEmptyOrNull(maximumDuration)) {
            return null;
        }
        return Integer.valueOf(maximumDuration);
    }

    public Float askMinimumRating() {
        System.out.println("Do you want a minimum rating (over 10)?");
        String minimumRating = readString();
        if(TextUtil.isEmptyOrNull(minimumRating)) {
            return null;
        }
        return Float.valueOf(minimumRating);
    }

    public Collection<String> askExcludedGenres() {
        System.out.println("Which genres do you want to exclude? (If multiple, give a comma-separated list)");
        String excludedGenres = readString();
        if (TextUtil.isEmptyOrNull(excludedGenres)) {
            System.out.println("Please give a genre to look for");
            return null;
        }
        excludedGenres = excludedGenres.replaceAll(" ", "");
        return Arrays.asList(excludedGenres.split(","));
    }

    private String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
