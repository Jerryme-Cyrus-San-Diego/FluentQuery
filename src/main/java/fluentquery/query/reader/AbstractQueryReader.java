package fluentquery.query.reader;

import fluentquery.exceptions.NotSupportedTypeException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static fluentquery.util.Formatter.camelCaseToSnakeCase;
import static java.sql.Types.*;

public abstract class AbstractQueryReader<T> {

	/**
	 * Returns the exact query to execute built clauses with the builder
	 * @param query the query that will be executed
	 * @return the string query to pass in PreparedStatement
	 */
	public  abstract String toSqlQuery(T query);

	/**
	 * Returns an array of field, then set those to accessible fields
	 * @param cl the class
	 * @return array of field
	 */
	protected final Field[] getFields(Class<?> cl) {
        List<Field> list = new ArrayList<>();
		Field[] fields   = cl.getDeclaredFields();

		Arrays
				.stream(fields)
				.forEach(field -> {
					field.setAccessible(true);
					list.add(field);
				});

		return list.toArray(Field[]::new);
	}

	/**
	 * Extract the column name and type number then mangle them in an Array
	 * @param data the metadata
	 * @return array of MetadataDetails
	 * @throws SQLException to access the columns from the metadata
	 */
	private MetadataDetails[] getMetadataNames(ResultSetMetaData data) throws SQLException {
		List<MetadataDetails> names = new ArrayList<>();

		for (int i = 1; i <= data.getColumnCount(); i++) {
			String name = data.getColumnName(i);
			int type = data.getColumnType(i);

			names.add(new MetadataDetails(name, type));
		}

		return names.toArray(MetadataDetails[]::new);
	}

	/**
	 * Maps the result set to an actual object
	 * @param rs the ResultSet to get the metadata from
	 * @param cl the class of the object to map to
	 * @return the mapped object
	 * @param <R> the type
	 * @throws SQLException when an error happened with getting particular columns from the ResultSet
	 */
	protected final <R> R map(ResultSet rs, Class<R> cl) throws SQLException {
		MetadataDetails[] details  = Arrays
				.stream(getMetadataNames(rs.getMetaData()))
				.sorted(Comparator.comparing(det -> camelCaseToSnakeCase(det.name())))
				.toArray(MetadataDetails[]::new);

		try {
			R instance = cl.getDeclaredConstructor().newInstance();
			Field[] fields = Arrays
					.stream(getFields(instance.getClass()))
					.sorted(Comparator.comparing(field -> camelCaseToSnakeCase(field.getName())))
					.toArray(Field[]::new);
			int b = 0;

			// iterate through fields (this is already accessible because the getFields()
			// already turns the accessible to true)
            for (Field field : fields) {
				// break, it means that all fields were already filled with
				// their respective values
                if (b == details.length) break;

                var detail = details[b];

                String fn = camelCaseToSnakeCase(field.getName());
                String dn = detail.name();

                if (fn.equals(dn)) {
                    field.set(instance, getAppropriateType(rs, dn, detail.type()));
                    b++; // increment b when fn == dn
                }
            }

			return instance;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected final Object getAppropriateType(ResultSet rs, String column, int type) throws SQLException {
		return switch (type) {
			case INTEGER -> rs.getInt(column);
			case VARCHAR -> rs.getString(column);
			case BIGINT  -> rs.getLong(column);
			case NUMERIC -> rs.getBigDecimal(column);
			case BOOLEAN -> rs.getBoolean(column);
			default 	 -> throw new NotSupportedTypeException("");
		};
	}
}
