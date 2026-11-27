package fluentquery.query.reader.reflect;

import java.util.List;

import fluentquery.exceptions.SelectQueryException;
import fluentquery.models.Column;

public class SelectReflector implements ClauseReflector<List<Column>> {

    @Override
    public String reflectClause(List<Column> clause) {
        var select = new StringBuilder();

        if (clause == null || clause.isEmpty()) {
            throw new SelectQueryException("No columns selected");
        }

        select.append("select ");

        if (clause.getFirst().column().trim().equals("*") && clause.size() == 1) {
            select.append("* ");
        } else {
            int c = clause.size();
            for (int i = 0; i < c; i++) {
                var isLast = i == c - 1;
                var e = clause.get(i);

                if (e.as() != null && !isLast) {
                    var s = e.column() + " as " + e.as() + ", ";
                    select.append(s);
                    continue;
                }

                select.append(e.column());

                if (!isLast) {
                    select.append(", ");
                }
            }
        }

        return select.append(' ').toString();
    }
}
