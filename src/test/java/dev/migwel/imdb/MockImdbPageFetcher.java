package dev.migwel.imdb;

import dev.migwel.imdb.util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockImdbPageFetcher implements PageFetcher {

    @Override
    public String fetchPage() {
        try {
            return FileUtil.loadFile("examplePage.html");
        } catch (URISyntaxException | IOException e) {
            return "";
        }
    }
}
