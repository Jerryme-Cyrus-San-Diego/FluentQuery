package fluentquery.models;

/**
 * Represents the table in a relational databases, whereas it treats a class as an Entity of your database
 * @param clazz the class
 * @param as the alias
 */
public record Table(Class<?> clazz, String as) {

	//use this as the default for no table aliasing
	public Table(Class<?> clazz) {
		this(clazz, null);
	}
}
