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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {
    
    private final BufferedReader reader;
    private static final Ordering DEFAULT_ORDERING = Ordering.POPULARITY_ASC;

    public InputReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(inputStreamReader);
    }

    public Filter readInputs() throws InvalidInputException {
        int start = askStart();
        Collection<String> genres = askGenres();
        if (CollectionsUtil.isEmptyOrNull(genres)) {
            throw new InvalidInputException("Genre cannot be null");
        }
        Collection<String> excludedGenres = askExcludedGenres();
        Integer minimumDuration = askMinimumDuration();
        Integer maximumDuration = askMaximumDuration();
        Float minimumRating = askMinimumRating();
        int nbMovies = askNumberMovies();
        Ordering ordering = askOrdering();
        Integer minimumVotes = askMinimumVotes();

        return new Filter(start, genres, excludedGenres, minimumDuration, maximumDuration, minimumRating, nbMovies, ordering, minimumVotes);
    }

    private Integer askMinimumVotes() {
        System.out.println("[Optional] Minimum number of votes: ");
        String minimumVotes = readString();
        if(TextUtil.isEmptyOrNull(minimumVotes)) {
            return null;
        }
        return Integer.valueOf(minimumVotes);
    }

    private Ordering askOrdering() throws InvalidInputException {
        System.out.println("[Optional] Which ordering do you want to use?");
        System.out.println("Possible choices: "+ Stream.of(Ordering.values()).map(Enum::name).collect(Collectors.joining(", ")) +". Default is "+ DEFAULT_ORDERING);
        String orderingStr = readString();
        if (TextUtil.isEmptyOrNull(orderingStr)) {
            return DEFAULT_ORDERING;
        }

        try {
            return Ordering.valueOf(orderingStr);
        }
        catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid ordering "+ orderingStr);
        }
    }

    private int askNumberMovies() {
        System.out.println("How many movies do you want to retrieve? (default is 10, max allowed is 100)");
        String nbMoviesStr = readString();
        if (TextUtil.isEmptyOrNull(nbMoviesStr)) {
            return 10;
        }

        int nbMovies = Integer.parseInt(nbMoviesStr);
        if (nbMovies > 100) {
            nbMovies = 100;
        }
        return nbMovies;
    }

    public Collection<String> askGenres() {
        System.out.println("[Mandatory] Which genre do you want to look for? (If multiple, give a comma-separated list)");
        String genre = readString();
        if (TextUtil.isEmptyOrNull(genre)) {
            System.out.println("Please give a genre to look for");
            return Collections.emptyList();
        }
        String[] genres = genre.replaceAll(" ", "").toLowerCase().split(",");
        return Arrays.asList(genres);
    }

    public int askStart() {
        System.out.println("[Optional] From which number do you want the search to start? (Default: 0)");
        String startStr = readString();
        if(TextUtil.isEmptyOrNull(startStr)) {
            return 0;
        }
        return Integer.parseInt(startStr);
    }

    public Integer askMinimumDuration() {
        System.out.println("[Optional] Minimum duration (in minutes):");
        String minimumDuration = readString();
        if(TextUtil.isEmptyOrNull(minimumDuration)) {
            return null;
        }
        return Integer.valueOf(minimumDuration);
    }

    public Integer askMaximumDuration() {
        System.out.println("[Optional] Maximum duration (in minutes):");
        String maximumDuration = readString();
        if(TextUtil.isEmptyOrNull(maximumDuration)) {
            return null;
        }
        return Integer.valueOf(maximumDuration);
    }

    public Float askMinimumRating() {
        System.out.println("[Optional] Minimum rating (out of 10)?");
        String minimumRating = readString();
        if(TextUtil.isEmptyOrNull(minimumRating)) {
            return null;
        }
        return Float.valueOf(minimumRating);
    }

    public Collection<String> askExcludedGenres() {
        System.out.println("[Optional] Which genres do you want to exclude? (If multiple, give a comma-separated list)");
        String excludedGenres = readString();
        if (TextUtil.isEmptyOrNull(excludedGenres)) {
            return null;
        }
        excludedGenres = excludedGenres.replaceAll(" ", "").toLowerCase();
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
