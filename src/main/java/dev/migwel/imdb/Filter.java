package dev.migwel.imdb;

import java.util.Collection;

public class Filter {
    private final int start;
    private final Collection<String> genres;
    private final Collection<String> excludedGenres;
    private final Integer minimumDuration;
    private final Integer maximumDuration;
    private final Float minimumRating;
    private final int nbMovies;
    private final Ordering ordering;
    private final Integer minimumVotes;

    public Filter(int start, Collection<String> genres, Collection<String> excludedGenres, Integer minimumDuration, Integer maximumDuration, Float minimumRating, int nbMovies, Ordering ordering, Integer minimumVotes) {
        this.start = start;
        this.genres = genres;
        this.excludedGenres = excludedGenres;
        this.minimumDuration = minimumDuration;
        this.maximumDuration = maximumDuration;
        this.minimumRating = minimumRating;
        this.nbMovies = nbMovies;
        this.ordering = ordering;
        this.minimumVotes = minimumVotes;
    }

    public static Filter copyFilter(Filter filter) {
        return new Filter(filter.start, filter.genres, filter.excludedGenres, filter.minimumDuration, filter.maximumDuration, filter.minimumRating, filter.nbMovies, filter.ordering, filter.minimumVotes);
    }

    public int getStart() {
        return start;
    }

    public Collection<String> getGenres() {
        return genres;
    }

    public Collection<String> getExcludedGenres() {
        return excludedGenres;
    }

    public Integer getMinimumDuration() {
        return minimumDuration;
    }

    public Integer getMaximumDuration() {
        return maximumDuration;
    }

    public Float getMinimumRating() {
        return minimumRating;
    }

    public int getNbMovies() {
        return nbMovies;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Integer getMinimumVotes() {
        return minimumVotes;
    }
}
