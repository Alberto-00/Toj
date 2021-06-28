package Model.storage;

import Model.search.Condition;
import Model.search.Operator;

import java.util.List;
import java.util.StringJoiner;

//Questa classe è stata realizzata per generare query in maniera immediata

public class QueryBuilder {

    private final String TABLE, ALIAS_TABLE;

    /*StringBuilder consente di costruire stringhe in maniera programmatica */
    private final StringBuilder QUERY;
    private static final String QM = "?";

    public QueryBuilder(String table, String alias){
        this.TABLE = table;
        this.ALIAS_TABLE = alias;
        this.QUERY = new StringBuilder();
    }

    public String generateQuery(){
        String generatedQuery = QUERY.toString();
        QUERY.setLength(0);
        return generatedQuery;
    }

    public QueryBuilder select(String... fields){
        QUERY.append("SELECT ");
        if(fields.length == 0)
            QUERY.append("*");
        else {
            //Mette insieme più stringhe con un separatore specifico
            StringJoiner commaJoiner = new StringJoiner(",");
            for(String field: fields)
                commaJoiner.add(String.format("%s.%s", ALIAS_TABLE, field));
            QUERY.append(commaJoiner.toString());
        }
        QUERY.append(" FROM ").append(TABLE).append(" AS ").append(ALIAS_TABLE);
        return this;
    }

    public QueryBuilder where(String condition){
        QUERY.append(" WHERE ").append(condition);
        return this;
    }

    public QueryBuilder where(){
        QUERY.append(" WHERE ");
        return this;
    }

    public QueryBuilder insert(String... fields){
        QUERY.append("INSERT INTO ").append(TABLE).append(' ');
        StringJoiner commaJoiner = new StringJoiner(",", "(", ")");
        for (String field: fields)
            commaJoiner.add(field);
        QUERY.append(commaJoiner.toString());
        QUERY.append(" VALUES ");
        int numberOfFields = fields.length;
        StringJoiner joiner = new StringJoiner(",", "(", ")");
        do{
            joiner.add(QM);
            numberOfFields--;
        }while (numberOfFields != 0);
        QUERY.append(joiner.toString());
        return this;
    }

    public QueryBuilder delete(String... fields){
        QUERY.append("DELETE FROM ").append(TABLE);
        return this;
    }

    public QueryBuilder update(String... fields){
        QUERY.append("UPDATE ").append(TABLE).append(" SET ");
        StringJoiner commaJoiner = new StringJoiner(",");
        for (String field: fields)
            commaJoiner.add(String.format("%s = %s", field, QM));
        QUERY.append(commaJoiner.toString());
        return this;
    }

    public QueryBuilder innerJoin(String joinedTable, String joinedAlias){
        QUERY.append(" INNER JOIN ").append(joinedTable).append(' ').append(joinedAlias);
        return this;
    }

    public QueryBuilder outerJoin(boolean isLeft, String joinedTable, String joinedAlias){
        String direction = isLeft ? " LEFT JOIN" : " RIGHT JOIN";
        QUERY.append(direction).append(' ').append(joinedAlias).append(' ').append(joinedAlias);
        return this;
    }

    public QueryBuilder on(String condition){
        QUERY.append(" ON ").append(condition);
        return this;
    }

    /*public QueryBuilder search(List<Condition> conditions){
        StringJoiner searchJoiner = new StringJoiner(" AND ");
        for (Condition cn : conditions){
            searchJoiner.add(String.format("%s.%s%s", ALIAS_TABLE, cn.toString(), QM));
        }
        QUERY.append(searchJoiner);
        return this;
    }*/

    public QueryBuilder search(List<Condition> conditions) {
        int i = 0, j = 0, k = 0, z = 0;
        for (Condition cn : conditions) {

            switch (cn.getName()) {
                case "nome_categoria":
                    if (i == 0) {
                        QUERY.append(" ) AND ( ").append(String.format("%s.%s%s", "c2", cn.toString(), QM));
                        i++;
                    } else
                        QUERY.append(" OR ").append(String.format("%s.%s%s", "c2", cn.toString(), QM));
                    break;

                case "id_nome":
                    if (k == 0) {
                        QUERY.append(" ) AND ( ").append(String.format("%s.%s%s", "ta", cn.toString(), QM));
                        k++;
                    } else
                        QUERY.append(" OR ").append(String.format("%s.%s%s", "ta", cn.toString(), QM));
                    break;

                case "nome_colore":
                    if (z == 0) {
                        QUERY.append(" ) AND ( ").append(String.format("%s.%s%s", "c", cn.toString(), QM));
                        z++;
                    } else
                        QUERY.append(" OR ").append(String.format("%s.%s%s", "c", cn.toString(), QM));
                    break;

                default:
                    if (j == 0) {
                        QUERY.append(" ( ").append(String.format("%s.%s%s", ALIAS_TABLE, cn.toString(), QM));
                        j++;
                    } else
                        QUERY.append(" ) AND ( ").append(String.format("%s.%s%s", ALIAS_TABLE, cn.toString(), QM));
                    break;
            }
        }
        return this;
    }

    public void orderBy(String... fields){
        QUERY.append(") ORDER BY ");
        StringJoiner commaJoiner = new StringJoiner(",");
        for(String field: fields)
            commaJoiner.add(String.format("%s.%s", ALIAS_TABLE, field));
        QUERY.append(commaJoiner.toString());
    }

    public void asc(){
        QUERY.append(" ASC ");
    }

    public void desc(){
        QUERY.append(" DESC ");
    }
}
