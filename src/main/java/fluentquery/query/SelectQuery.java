package fluentquery.query;

import fluentquery.models.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class SelectQuery {

	private List<Column> columns;
	private Table table;
	private Expression where;
	private OrderBy order;
	private Pagination limit; //can be LIMIT or OFFSET
	private Pagination offset;

	public SelectQuery() {}

	public SelectQuery(List<Column> columns, Table table, Expression where, OrderBy order) {
		this.columns = columns;
		this.table = table;
		this.where = where;
		this.order = order;
	}

	public SelectQuery(List<Column> columns, Table table, Expression where, OrderBy order, Pagination limit) {
		this.columns = columns;
		this.table = table;
		this.where = where;
		this.order = order;
		this.limit = limit;
		this.offset = null;
	}

	public SelectQuery(List<Column> columns, Table table, Expression where, OrderBy order, Pagination limit, Pagination offset) {
		this.columns = columns;
		this.table = table;
		this.where = where;
		this.order = order;
		this.limit = limit;
		this.offset = offset;
	}
}
