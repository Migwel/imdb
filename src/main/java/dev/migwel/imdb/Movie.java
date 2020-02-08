package dev.migwel.imdb;

import java.util.Collection;

public class Movie {

    private final String name;
    private final int releaseYear;
    private final Collection<String> genres;
    private final Float rating;
    private final Integer durationInMinutes;

    public Movie(String name, int releaseYear, Collection<String> genres, Float rating, Integer durationInMinutes) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.rating = rating;
        this.durationInMinutes = durationInMinutes;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Collection<String> getGenres() {
        return genres;
    }

    public Float getRating() {
        return rating;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("name='").append(name).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", genres=").append(genres);
        sb.append(", rating=").append(rating);
        sb.append(", durationInMinutes=").append(durationInMinutes);
        sb.append('}');
        return sb.toString();
    }
}
