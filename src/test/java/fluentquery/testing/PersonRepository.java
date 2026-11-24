package fluentquery.testing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		try (var conn = connect()) {
			var select = """
						 SELECT name, age FROM people;
						 """;
			PreparedStatement ps = conn.prepareStatement(select);

			try (var rs = ps.executeQuery()) {
				while (!rs.next()) list.add(map(rs));
			}

		} catch (SQLException ignored) {

		}

		return list;
	}

}
