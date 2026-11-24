package fluentquery.models;

import fluentquery.models.enums.SortingDirection;

/**
 * Represents the ORDER BY clause
 * @param column the column to base the order
 * @param direction the direction of the sort (ascending or descending)
 */
public record OrderBy(Column column, SortingDirection direction) {

	public OrderBy(Column column) {
		this(column, SortingDirection.ASCENDING);
	}
}
