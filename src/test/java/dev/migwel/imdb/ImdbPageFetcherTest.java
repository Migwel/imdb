package dev.migwel.imdb;


import dev.migwel.imdb.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

class ImdbPageFetcherTest {

    @Test
    public void testFetchPage() throws IOException, URISyntaxException {
        ImdbPageFetcher pageFetcher = new ImdbPageFetcher("Horror", "0");
        String pageHtml = pageFetcher.fetchPage();
        Assertions.assertTrue(pageHtml.contains("Horror"));
    }

}