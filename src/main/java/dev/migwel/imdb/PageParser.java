package dev.migwel.imdb;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface PageParser {

    @Nonnull
    Collection<Film> parseFilms();
}
