package Model.storage;

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
        QUERY.append("UPDATE ").append(TABLE);
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
}
