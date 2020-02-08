package dev.migwel.imdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImdbPageParserTest {

    @Test
    void testParseFilms() {
        PageParser pageParser = new ImdbPageParser(new MockImdbPageFetcher());
        assertEquals(50, pageParser.parseFilms().size());
    }

}