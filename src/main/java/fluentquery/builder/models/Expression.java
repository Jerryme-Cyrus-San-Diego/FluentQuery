package fluentquery.builder.models;

import fluentquery.builder.models.enums.CompOperator;
import fluentquery.builder.models.enums.LogicalOperator;

public sealed interface Expression
		permits Expression.Comparison, Expression.LogicalExpression {

	record Comparison(
			Column column,
			CompOperator operator,
			Value val
	) implements Expression {}

	record LogicalExpression(
			Expression left,
			LogicalOperator operator,
			Expression right
	) implements Expression {}

}
