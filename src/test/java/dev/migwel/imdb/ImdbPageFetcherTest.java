package dev.migwel.imdb;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ImdbPageFetcherTest {

    @Test
    public void testFetchPage() {
        ImdbPageFetcher pageFetcher = new ImdbPageFetcher("Horror", 0, Ordering.USERRATING_DESC);
        String pageHtml = pageFetcher.fetchPage();
        Assertions.assertTrue(pageHtml.contains("Horror"));
    }

}