package fluentquery.query.builder;

import fluentquery.exceptions.QueryException;
import fluentquery.models.*;
import fluentquery.models.enums.JoinType;
import fluentquery.models.enums.SortingDirection;
import fluentquery.query.SelectQuery;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static fluentquery.models.enums.JoinType.*;

@Getter
public class SelectQueryBuilder {

	private final SelectQuery select;

	private SelectQueryBuilder(Builder builder) {
		this.select = new SelectQuery(
				new ArrayList<>(builder.columns),
				builder.table,
				new ArrayList<>(builder.joins),
				builder.where,
				builder.order,
				builder.limit,
				builder.offset
		);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<Column> columns;
		private Table table;
		private Expression where;
		private final List<Join> joins;
		private OrderBy order;
		private Pagination.Limit limit; //can be LIMIT or OFFSET
		private Pagination.Offset offset;

		private Builder() {
			this.joins = new ArrayList<>();
		}

		public Builder select(List<String> columns) {
			var cols = new ArrayList<Column>();
			columns.forEach(c -> {
				if (c == null) 
					throw new QueryException("Null column selection");

				cols.add(new Column(c));
			});

			this.columns = cols;
			return this;
		}

		public Builder from(String table) {
			if (table == null) throw new QueryException("There are no selected table");
			this.table = new Table(table);
			return this;
		}

		public Builder from(String table, String as) {
			this.table = new Table(table, as);
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
			if (limit < 0) throw new QueryException("LIMIT cannot be negative");

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

		public Builder join(JoinType join, String column, String as, Expression expression) {
			var table = new Table(column, as);
			var on    = new On(expression);

			var joinToAdd = switch (join) {
				case NORMAL  -> new Join(NORMAL,  table, on);
				case RIGHT   -> new Join(RIGHT,   table, on);
				case LEFT    -> new Join(LEFT, 	  table, on);
				case FULL    -> new Join(FULL,    table, on);
				case INNER   -> new Join(INNER,   table, on);
				case CROSS   -> new Join(CROSS,   table, on);
				case NATURAL -> new Join(NATURAL, table, on);
			};

			this.joins.add(joinToAdd);
			return this;
		}

		public SelectQuery build() {
			if (this.offset == null) this.offset = new Pagination.Offset();

			//TODO put reader here

			return new SelectQuery(
					this.columns, this.table,
					this.joins,   this.where,
					this.order,   this.limit,
					this.offset
			);
		}
	}

}
