package fluentquery.testing;

import fluentquery.query.builder.SelectQueryBuilder;
import fluentquery.query.reader.SelectQueryReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fluentquery.DbConnection.connect;

public class PersonRepository {

	private static Person map(ResultSet rs) throws SQLException {
		return new Person(
				rs.getString("name"),
				rs.getInt("age")
		);
	}

	public List<Person> getPeople() {
		var list = new ArrayList<Person>();
		var sqr  = new SelectQueryReader<Person>();
		var sqb  = SelectQueryBuilder
				.builder()
				.select(List.of("age"))
				.from("people")
				.build();

		var select = sqr.toSqlQuery(sqb);
		System.out.println(select);
		try (
			var conn = connect();
			var ps   = conn.prepareStatement(select);
			var rs   = ps.executeQuery()
		) {
			while (rs.next()) list.add(sqr.getMapped(rs, Person.class));
		} catch (SQLException ignored) {

		}

		return list;
	}

}
