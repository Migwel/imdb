package dev.migwel.imdb;

import java.util.Collection;

public class Filter {
    private final String start;
    private final Collection<String> genres;
    private final Collection<String> excludedGenres;
    private final Integer minimumDuration;
    private final Integer maximumDuration;
    private final Float minimumRating;

    public Filter(String start, Collection<String> genres, Collection<String> excludedGenres, Integer minimumDuration, Integer maximumDuration, Float minimumRating) {
        this.start = start;
        this.genres = genres;
        this.excludedGenres = excludedGenres;
        this.minimumDuration = minimumDuration;
        this.maximumDuration = maximumDuration;
        this.minimumRating = minimumRating;
    }

    public String getStart() {
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
}
