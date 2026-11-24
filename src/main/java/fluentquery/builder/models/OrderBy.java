package fluentquery.builder.models;

import fluentquery.builder.models.enums.SortingDirection;

public record OrderBy(Column column, SortingDirection direction) {

	public OrderBy(Column column) {
		this(column, SortingDirection.ASCENDING);
	}
}
