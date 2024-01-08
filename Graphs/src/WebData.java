import java.util.*;
public class WebData {
    private static final Map<String, List<String>> urlMappings = new HashMap<>();
    private static final List<String> emptyList = createImmutableList();

    static {
        urlMappings.put("https://www.lwsd.org",
                createImmutableList(
                        "https://tesla.lwsd.org",
                        "https://ehs.lwsd.org",
                        "https://jhs.lwsd.org",
                        "https://lwhs.lwsd.org",
                        "https://rhs.lwsd.org"));
        urlMappings.put("https://tesla.lwsd.org",
                createImmutableList(
                        "https://www.google.com",
                        "https://www.microsoft.com"));
        urlMappings.put("https://ehs.lwsd.org",
                createImmutableList(
                        "https://www.yahoo.com",
                        "https://www.amazon.com"));
        urlMappings.put("https://rhs.lwsd.org",
                createImmutableList(
                        "https://news.google.com",
                        "https://www.cnn.com",
                        "https://www.bbc.com"));
        urlMappings.put("https://lwhs.lwsd.org",
                createImmutableList(
                        "https://www.lwsd.org"));
        urlMappings.put("https://www.google.com",
                createImmutableList(
                        "https://news.google.com",
                        "https://maps.google.com",
                        "https://www.google.org"));
        urlMappings.put("https://maps.google.com",
                createImmutableList(
                        "https://www.google.com"));
        urlMappings.put("https://www.amazon.com",
                createImmutableList(
                        "https://www.c9.io",
                        "https://www.yahoo.com"));
        urlMappings.put("https://www.microsoft.com",
                createImmutableList(
                        "https://www.hotmail.com",
                        "https://www.xbox.com"));
        urlMappings.put("https://www.xbox.com",
                createImmutableList(
                        "https://www.bungie.net"));
    }

    private static <T> List<T> createImmutableList(T... a) {
        return java.util.Collections.unmodifiableList(java.util.Arrays.asList(a));
    }

    static List<String> getUrlMappings(String url) {
        if (urlMappings.containsKey(url)) {
            return urlMappings.get(url);
        }
        return emptyList;
    }
}
