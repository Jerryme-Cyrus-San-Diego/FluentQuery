package fluentquery.models;

/**
 * Represents the pagination clauses in SQL
 * @see	Pagination.Limit
 * @see	Pagination.Offset
 */
public sealed interface Pagination
		permits Pagination.Limit, Pagination.Offset {

	/**
	 * Represents the LIMIT clause in SQL
	 * @param limit the limit
	 */
	record Limit(Integer limit) implements Pagination {}

	/**
	 * Represents the OFFSET clause in SQL
	 * @param offset the offset (where to start)
	 */
	record Offset(Integer offset) implements Pagination {
		public Offset() {
			this(0);
		}
	}
}
