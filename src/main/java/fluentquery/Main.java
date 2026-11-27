package fluentquery;

import fluentquery.models.Column;
import fluentquery.query.SelectQuery;
import fluentquery.query.builder.SelectQueryBuilder;
import fluentquery.query.reader.AbstractQueryReader;
import fluentquery.query.reader.SelectQueryReader;
import fluentquery.query.reader.reflect.SelectReflector;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		AbstractQueryReader<SelectQuery> sqr = new SelectQueryReader<Package>();
		var sqb = SelectQueryBuilder
				.builder()
				.select(List.of("name", "age"))
				.from("people")
				.build();

		var s = sqr.toSqlQuery(sqb);

		System.out.println(s);
	}
}