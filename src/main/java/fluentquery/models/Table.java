package fluentquery.models;

/**
 * Represents the table in a relational databases, whereas it treats a class as an Entity of your database
 * @param table the table
 * @param as the alias
 */
public record Table(String table, String as) {

	//use this as the default for no table aliasing
	public Table(String table) {
		this(table, null);
	}
}
