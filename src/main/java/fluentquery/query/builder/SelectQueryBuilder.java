package fluentquery.query.builder;

import fluentquery.models.*;
import fluentquery.models.enums.SortingDirection;
import fluentquery.query.SelectQuery;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SelectQueryBuilder {

	private final SelectQuery select;

	private SelectQueryBuilder(Builder builder) {
		this.select = new SelectQuery(builder.columns, builder.table, builder.where, builder.order, builder.limit, builder().offset);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<Column> columns;
		private Table table;
		private Expression where;
		private OrderBy order;
		private Pagination limit; //can be LIMIT or OFFSET
		private Pagination offset;

		private Builder() {
		}

		public Builder select(List<String> columns) {
			var cols = new ArrayList<Column>();
			columns.forEach(c -> cols.add(new Column(c)));

			this.columns = cols;
			return this;
		}

		public Builder from(Class<?> cl) {
			this.table = new Table(cl);
			return this;
		}

		public Builder from(Class<?> cl, String as) {
			this.table = new Table(cl, as);
			return this;
		}

		public Builder where(Expression where) {
			this.where = where;
			return this;
		}

		public Builder orderBy(Column column) {
			this.order = new OrderBy(column);
			return this;
		}

		public Builder orderBy(Column column, SortingDirection direction) {
			this.order = new OrderBy(column, direction);
			return this;
		}

		public Builder limit(Integer limit) {
			this.limit = new Pagination.Limit(limit);
			return this;
		}

		public Builder offset() {
			this.offset = new Pagination.Offset();
			return this;
		}

		public Builder offset(Integer offset) {
			this.offset = new Pagination.Offset(offset);
			return this;
		}

		public SelectQuery build() {
			if (this.offset == null) this.offset = new Pagination.Offset();

			return new SelectQuery(
					this.columns, this.table,
					this.where, this.order,
					this.limit, this.offset
			);
		}
	}

}
