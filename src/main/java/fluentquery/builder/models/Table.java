package fluentquery.builder.models;

public record Table(Class<?> clazz, String as) {

	public Table(Class<?> clazz) {
		this(clazz, null);
	}
}
