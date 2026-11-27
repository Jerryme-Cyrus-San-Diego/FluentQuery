package fluentquery.query.reader.reflect;

import fluentquery.models.Table;

public class TableReflector implements ClauseReflector<Table> {

    @Override
    public String reflectClause(Table clause) {
        String column = clause.table();
        String as     = clause.as();

        return as != null
                ? " " + column + " as " + as + " "
                : " " + column + " ";
    }
}
