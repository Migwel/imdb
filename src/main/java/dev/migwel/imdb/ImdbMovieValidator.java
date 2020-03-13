package dev.migwel.imdb;

import dev.migwel.imdb.util.CollectionsUtil;

import java.util.Collection;

public class ImdbMovieValidator implements MovieValidator {

    private final Filter filter;

    public ImdbMovieValidator(Filter filter) {
        this.filter = Filter.copyFilter(filter);
    }

    @Override
    public boolean isMovieValid(Movie movie) {
        if (! validDuration(movie, filter.getMinimumDuration(), filter.getMaximumDuration())) {
            return false;
        }
        if (! validRating(movie, filter.getMinimumRating())) {
            return false;
        }
        if (! validVoteNumber(movie, filter.getMinimumVotes())) {
            return false;
        }
        return !isExcluded(movie, filter.getExcludedGenres());
    }

    private boolean validVoteNumber(Movie movie, Integer minimumVotes) {
        if (minimumVotes == null || minimumVotes == 0) {
            return true;
        }
        return movie.getNbVotes() >= minimumVotes;
    }

    private boolean isExcluded(Movie movie, Collection<String> excludedGenres) {
        if (CollectionsUtil.isEmptyOrNull(excludedGenres) || CollectionsUtil.isEmptyOrNull(movie.getGenres())) {
            return false;
        }

        return movie.getGenres().stream().anyMatch(excludedGenres::contains);
    }

    private boolean validRating(Movie movie, Float minimumRating) {
        if (minimumRating == null || movie.getRating() == null) {
            return true;
        }
        return movie.getRating() >= minimumRating;
    }

    private boolean validDuration(Movie movie, Integer minimumDuration, Integer maximumDuration) {
        return checkMinimumDuration(movie, minimumDuration) && checkMaximumDuration(movie, maximumDuration);
    }

    private boolean checkMaximumDuration(Movie movie, Integer maximumDuration) {
        if(maximumDuration == null || movie.getDurationInMinutes() == null) {
            return true;
        }
        return movie.getDurationInMinutes() <= maximumDuration;
    }

    private boolean checkMinimumDuration(Movie movie, Integer minimumDuration) {
        if(minimumDuration == null || movie.getDurationInMinutes() == null) {
            return true;
        }
        return movie.getDurationInMinutes() >= minimumDuration;
    }

}
