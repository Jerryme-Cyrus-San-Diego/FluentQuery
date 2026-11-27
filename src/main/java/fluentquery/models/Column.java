package fluentquery.models;

/**
 * A record that represents each table of your entity
 * @param column the name of the table
 */
public record Column(String column, String as) {

    public Column(String column) {
        this(column, null);
    }

}
