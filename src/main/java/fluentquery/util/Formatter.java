package fluentquery.util;

import java.util.Locale;

public final class Formatter {

    private Formatter() {}

    /**
     * Transform a camelCase statement to snake_case
     *
     * @param s the camelCase input
     * @return the snake_case output
     */
    public static String camelCaseToSnakeCase(String s) {
        if (s.chars().mapToObj(f -> (char) f).allMatch(Character::isLowerCase)) return s;

        return s
                .replaceAll("(?<=[a-z0-9])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])", "_")
                .toLowerCase(Locale.ROOT);


    }

}
