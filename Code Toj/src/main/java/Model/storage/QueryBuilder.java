package Model.storage;

import java.util.StringJoiner;

//Questa classe è stata realizzata per generare query in maniera immediata

public class QueryBuilder {

    private final String table, alias;

    /*StringBuilder consente di costruire stringhe in maniera programmatica */
    private final StringBuilder query;
    private static final String QM = "?";

    public QueryBuilder(String table, String alias){
        this.table = table;
        this.alias = alias;
        this.query = new StringBuilder();
    }

    public String generateQuery(){
        String generatedQuery = query.toString();
        query.setLength(0);
        return generatedQuery;
    }

    public QueryBuilder select(String... fields){
        query.append("SELECT ");
        if(fields.length == 0)
            query.append("*");
        else {
            //Mette insieme più stringhe con un separatore specifico
            StringJoiner commaJoiner = new StringJoiner(",");
            for(String field: fields)
                commaJoiner.add(String.format("%s.%s", alias, field));
            query.append(commaJoiner.toString());
        }
        query.append(" FROM ").append(table).append(" AS ").append(alias);
        return this;
    }

    public QueryBuilder where(String condition){
        query.append(" WHERE ").append(condition);
        return this;
    }

    public QueryBuilder insert(String... fields){
        query.append("INSERT INTO ").append(table).append(' ');
        StringJoiner commaJoiner = new StringJoiner(",", "(", ")");
        for (String field: fields)
            commaJoiner.add(field);
        query.append(commaJoiner.toString());
        query.append(" VALUES ");
        int numberOfFields = fields.length;
        StringJoiner joiner = new StringJoiner(",", "(", ")");
        do{
            joiner.add(QM);
            numberOfFields--;
        }while (numberOfFields != 0);
        query.append(joiner.toString());
        return this;
    }

    public QueryBuilder delete(String... fields){
        query.append("DELETE FROM ").append(table);
        return this;
    }

    public QueryBuilder update(String... fields){
        query.append("UPDATE ").append(table);
        StringJoiner commaJoiner = new StringJoiner(",");
        for (String field: fields)
            commaJoiner.add(String.format("%s = %s", field, QM));
        query.append(commaJoiner.toString());
        return this;
    }

    public QueryBuilder innerJoin(String joinedTable, String joinedAlias){
        query.append(" INNER JOIN ").append(joinedTable).append(' ').append(joinedAlias);
        return this;
    }

    public QueryBuilder outerJoin(boolean isLeft, String joinedTable, String joinedAlias){
        String direction = isLeft ? " LEFT JOIN" : " RIGHT JOIN";
        query.append(direction).append(' ').append(joinedAlias).append(' ').append(joinedAlias);
        return this;
    }

    public QueryBuilder on(String condition){
        query.append(" ON ").append(condition);
        return this;
    }
}
