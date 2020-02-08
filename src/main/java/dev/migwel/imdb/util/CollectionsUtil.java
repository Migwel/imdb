package dev.migwel.imdb.util;

import java.util.Collection;

public class CollectionsUtil {

    private CollectionsUtil() {
        //Util
    }

    public static boolean isEmptyOrNull(Collection<?> c) {
        return c == null || c.isEmpty();
    }
}
