package fluentquery.models;

/**
 * Represents the value for comparisons, and other clauses that needs a type literal, rather than table values
 * @param value the value for comparisons
 */
public record Value(Object value) {
}
