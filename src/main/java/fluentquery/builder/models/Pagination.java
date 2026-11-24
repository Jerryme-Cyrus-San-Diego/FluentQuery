package fluentquery.builder.models;

public sealed interface Pagination
		permits Pagination.Limit, Pagination.Offset {

	record Limit(Integer limit) implements Pagination {}

	record Offset(Integer offset) implements Pagination {
		public Offset() {
			this(0);
		}
	}
}
