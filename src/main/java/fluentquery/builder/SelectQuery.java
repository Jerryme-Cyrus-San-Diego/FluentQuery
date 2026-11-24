package fluentquery.builder;

import fluentquery.builder.models.*;

import java.util.List;

public record SelectQuery(
		List<Column> column,
		Table table,
		Expression where,
		OrderBy order,
		Pagination limit,
		Pagination.Offset offset
) {

	public SelectQuery(List<Column> column,
	                   Table table,
	                   Expression where,
	                   OrderBy order,
					   Pagination paginationClause) {
		this(column, table, where, order, paginationClause, null);
	}

	public SelectQuery(List<Column> column,
	                   Table table,
	                   Expression where,
	                   OrderBy order) {
		this(column, table, where, order, null, null);
	}

}
