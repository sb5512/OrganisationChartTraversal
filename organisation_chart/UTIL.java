package organisation_chart;

public class UTIL {
    public static String stringReform(String name) {
        // s is a regular expression that matches space, tab, newline.(+) denotes 'more' of these spaces or new lines..
        String reformedName = name.replaceAll("\\s+", " ");
        return reformedName.toLowerCase();
    }
}
