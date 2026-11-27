package fluentquery.models;

import fluentquery.models.enums.CompOperator;
import fluentquery.models.enums.LogicalOperator;

/**
 * This class represents the boolean expression in the WHERE clause or any clauses
 * that need a boolean expression
 * @see ColToValComparison
 * @see Expression.LogicalExpression
 */
public sealed interface Expression
		permits Expression.ColumnComparison, Expression.ColToValComparison, Expression.LogicalExpression {

	/**
	 * Represents the single unit of boolean expression:
	 * {@snippet : WHERE a >= b}
	 * or
	 * {@snippet : WHERE a != b}
	 * @param column the table that will be compared
	 * @param operator the operator that will be used
	 * @param val the value to compare the table to
	 */
	record ColToValComparison(
			Column column,
			CompOperator operator,
			Value val
	) implements Expression {}

	record ColumnComparison(
			Column col1,
			CompOperator operator,
			Column col2
	) implements Expression {}

	/**
	 * Represents the entirety of a boolean expression:
	 * {@snippet : WHERE (a != b) || (a && b)}
	 * or
	 * {@snippet : WHERE !(a >= b) && (c != d)}
	 * @param left the left boolean expression
	 * @param operator the logical operator (AND, OR, NOT)
	 * @param right the right boolean expression
	 */
	record LogicalExpression(
			Expression left,
			LogicalOperator operator,
			Expression right
	) implements Expression {}

}
