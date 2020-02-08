package dev.migwel.imdb;

import dev.migwel.imdb.util.CollectionsUtil;

import java.util.Collection;

public class ImdbMovieValidator implements MovieValidator {

    private final Filter filter;

    public ImdbMovieValidator(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean isMovieValid(Film film) {
        if (! validDuration(film, filter.getMinimumDuration(), filter.getMaximumDuration())) {
            return false;
        }
        if (! validRating(film, filter.getMinimumRating())) {
            return false;
        }
        return !isExcluded(film, filter.getExcludedGenres());
    }

    private boolean isExcluded(Film film, Collection<String> excludedGenres) {
        if (CollectionsUtil.isEmptyOrNull(excludedGenres) || CollectionsUtil.isEmptyOrNull(film.getGenres())) {
            return false;
        }

        return film.getGenres().stream().anyMatch(excludedGenres::contains);
    }

    private boolean validRating(Film film, Float minimumRating) {
        if (minimumRating == null || film.getRating() == null) {
            return true;
        }
        return film.getRating() >= minimumRating;
    }

    private boolean validDuration(Film film, Integer minimumDuration, Integer maximumDuration) {
        return checkMinimumDuration(film, minimumDuration) && checkMaximumDuration(film, maximumDuration);
    }

    private boolean checkMaximumDuration(Film film, Integer maximumDuration) {
        if(maximumDuration == null || film.getDurationInMinutes() == null) {
            return true;
        }
        return film.getDurationInMinutes() <= maximumDuration;
    }

    private boolean checkMinimumDuration(Film film, Integer minimumDuration) {
        if(minimumDuration == null || film.getDurationInMinutes() == null) {
            return true;
        }
        return film.getDurationInMinutes() >= minimumDuration;
    }

}
