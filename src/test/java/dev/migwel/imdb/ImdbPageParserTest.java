package dev.migwel.imdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImdbPageParserTest {

    @Test
    void testParseMovie() {
        PageParser pageParser = new ImdbPageParser(new MockImdbPageFetcher());
        assertEquals(50, pageParser.parseMovies().size());
    }

}