package fluentquery.query.reader;

import fluentquery.query.SelectQuery;
import fluentquery.query.reader.reflect.SelectReflector;
import fluentquery.query.reader.reflect.TableReflector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SelectQueryReader<R> extends AbstractQueryReader<SelectQuery> {

	@Override
	public String toSqlQuery(SelectQuery selectQuery) {
        var selectRef = new SelectReflector();
		var tableRef  = new TableReflector();

        return selectRef.reflectClause(selectQuery.columns())
				+ "from"
				+ tableRef.reflectClause(selectQuery.table());
	}

	public R getMapped(ResultSet rs, Class<R> cl) throws SQLException {
		Objects.requireNonNull(rs);
		Objects.requireNonNull(cl);

		return super.map(rs, cl);
	}

}
