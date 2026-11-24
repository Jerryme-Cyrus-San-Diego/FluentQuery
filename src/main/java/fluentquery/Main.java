package fluentquery;

import fluentquery.models.enums.SortingDirection;
import fluentquery.query.SelectQuery;
import fluentquery.models.Column;
import fluentquery.models.Expression;
import fluentquery.models.Table;
import fluentquery.models.Value;
import fluentquery.models.enums.CompOperator;
import fluentquery.query.builder.SelectQueryBuilder;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		SelectQuery queryBuilder =
				SelectQueryBuilder
						.builder()
						.select(List.of("name", "age"))
						.from(Table.class)
						.where(new Expression.Comparison(
								new Column("age"),
								CompOperator.GREATER_THAN_OR_EQUAL,
								new Value(18)
						))
						.orderBy(new Column("age"))
						.limit(30)
						.build();

		System.out.println(queryBuilder);
	}
}