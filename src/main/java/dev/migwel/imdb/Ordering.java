package dev.migwel.imdb;

public enum Ordering {
    POPULARITY_ASC("moviemeter,asc"),
    POPULARITY_DESC("moviemeter,desc"),
    ALPHABETICAL_ASC("alpha,asc"),
    ALPHABETICAL_DESC("alpha,desc"),
    USERRATING_ASC("user_rating,asc"),
    USERRATING_DESC("user_rating,desc"),
    NBVOTES_ASC("num_votes,asc"),
    NBVOTES_DESC("num_votes,desc"),
    USBOXOFFICE_ASC("boxoffice_gross_us,asc"),
    USBOXOFFICE_DESC("boxoffice_gross_us,desc"),
    RUNTIME_ASC("runtime,asc"),
    RUNTIME_DESC("runtime,desc"),
    YEAR_ASC("year,asc"),
    YEAR_DESC("year,desc"),
    RELEASEDATE_ASC("release_date,asc"),
    RELEASEDATE_DESC("release_date,desc"),
    ;

    private final String imdbFilter;

    Ordering(String imdbFilter) {
        this.imdbFilter = imdbFilter;
    }

    public String getImdbFilter() {
        return imdbFilter;
    }
}
