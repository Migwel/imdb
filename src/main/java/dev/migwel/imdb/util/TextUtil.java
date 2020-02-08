package dev.migwel.imdb.util;

public class TextUtil {

    private TextUtil() {
        //Util
    }

    public static boolean isEmptyOrNull(String s) {
        return s == null || "".equals(s);
    }
}
