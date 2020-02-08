package dev.migwel.imdb;

import dev.migwel.imdb.exception.InvalidInputException;

import java.util.Collection;

public class Imdb {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader(System.in);
        Filter filter;
        try {
            filter = inputReader.readInputs();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }

        String genresStr = String.join(",", filter.getGenres());
        PageParser parser = new ImdbPageParser(new ImdbPageFetcher(genresStr, filter.getStart()));
        Collection<Film> films = parser.parseFilms();
        ImdbMovieValidator imdbMovieValidator = new ImdbMovieValidator(filter);
        for (Film film : films) {
            if (! imdbMovieValidator.isMovieValid(film)) {
                continue;
            }
            System.out.println(film);
        }
    }
}
