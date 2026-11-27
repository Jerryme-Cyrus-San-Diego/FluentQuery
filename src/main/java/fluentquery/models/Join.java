package fluentquery.models;

import fluentquery.models.enums.JoinType;

public record Join(
		JoinType joinType,
		Table table,
		On on
) {}
