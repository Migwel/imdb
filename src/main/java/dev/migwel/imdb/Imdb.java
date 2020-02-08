package dev.migwel.imdb;

import dev.migwel.imdb.exception.InvalidInputException;
import dev.migwel.imdb.util.CollectionsUtil;

import java.util.ArrayList;
import java.util.Collection;

public class Imdb {
    private static final int MAX_NB_ITERATIONS = 25;

    public static void main(String[] args) {
        InputReader inputReader = new InputReader(System.in);
        Filter filter;
        try {
            filter = inputReader.readInputs();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }

        Collection<Movie> relevantMovies = fetchRelevantMovies(filter);
        if(CollectionsUtil.isEmptyOrNull(relevantMovies)) {
            System.out.println("No movies could be found for the given criteria");
        }
        relevantMovies.forEach(System.out::println);
    }

    private static Collection<Movie> fetchRelevantMovies(Filter filter) {
        Ordering ordering = filter.getOrdering();
        String genresStr = String.join(",", filter.getGenres());
        int nbIterations = 0;
        Collection<Movie> relevantMovies = new ArrayList<>();
        int pageNumber = filter.getStart();
        while (nbIterations < MAX_NB_ITERATIONS) {
            Collection<Movie> movies = fetchMovies(genresStr, pageNumber, ordering);
            ImdbMovieValidator imdbMovieValidator = new ImdbMovieValidator(filter);
            for (Movie movie : movies) {
                if (!imdbMovieValidator.isMovieValid(movie)) {
                    continue;
                }
                relevantMovies.add(movie);
                if (relevantMovies.size() >= filter.getNbMovies()) {
                    return relevantMovies;
                }
            }
            nbIterations++;
            pageNumber += movies.size();
        }

        return relevantMovies;
    }

    private static Collection<Movie> fetchMovies(String genresStr, int start, Ordering ordering) {
        PageParser parser = new ImdbPageParser(new ImdbPageFetcher(genresStr, start, ordering));
        return parser.parseMovies();
    }
}
