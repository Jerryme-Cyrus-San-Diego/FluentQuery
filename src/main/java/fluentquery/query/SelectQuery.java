package fluentquery.query;

import fluentquery.models.*;

import java.util.List;

//TODO: Read the null values in a specific class for query that is not included in the builder pattern
public record SelectQuery(
		List<Column> columns,
		Table table,
		List<Join> joins,
		Expression where,
		OrderBy order,
		Pagination.Limit limit,
		Pagination.Offset offset) {}
