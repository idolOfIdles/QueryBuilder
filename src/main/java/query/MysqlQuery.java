package query;

/**
 * Created by safayat on 10/16/18.
 */



interface  MysqlQueryInterface {
    MysqlQuery getQuery();
}

public class MysqlQuery implements MysqlQueryInterface{

    private StringBuilder query;
    private StringBuilder queryFields;

    public MysqlQuery(String fields) {
        this.query = new StringBuilder();
        queryFields = new StringBuilder();
        queryFields.append(fields);
    }
    public static MysqlQuery get(){
        return new MysqlQuery("*");
    }

    public static MysqlQuery get(String fields){
        return new MysqlQuery(fields);
    }

    public MysqlQuery sum(String field){
        appendAggregateFunction(field, "sum");
        return this;
    }

    public MysqlQuery avg(String field){
        appendAggregateFunction(field, "avg");
        return this;
    }

    public MysqlQuery max(String field){
        appendAggregateFunction(field, "max");
        return this;
    }

    public MysqlQuery min(String field){
        appendAggregateFunction(field, "min");
        return this;
    }

    private void appendAggregateFunction(String field, String op){
        if(queryFields.length()>0) queryFields.append(",");
        queryFields.append(op).append("(").append(field).append(")");
    }

    public MysqlQuery getQuery() {
        return this;
    }

    public String toString() {
        return query.toString();
    }

    public MysqlQuery append(Object str) {
        query.append(str);
        return this;
    }

    public MysqlTable table(String tableName, String code){
        query.append("select " + queryFields.toString() + " from ");
        return new MysqlTable(this).table(tableName, code);
    }

    public MysqlTable table(String tableName){
        query.append("select " + queryFields.toString() + " from ");
        String[] splitted = tableName.trim().split(" ");
        String code = "";
        if(splitted.length > 1){
            code = splitted[1];
            return new MysqlTable(this).table(splitted[0], code);
        }
        return new MysqlTable(this).table(tableName,"");
    }

}


