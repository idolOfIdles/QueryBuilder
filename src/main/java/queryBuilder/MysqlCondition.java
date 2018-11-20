package queryBuilder;

import queryBuilder.util.Util;

import java.util.List;

/**
 * Created by safayat on 10/16/18.
 */


public class MysqlCondition implements MysqlQueryInterface{

    private MysqlQueryInterface mysqlQuery;
    private MysqlOrder mysqlOrder;
    private MysqlGroupBy mysqlGroupBy;
    public MysqlCondition(MysqlQueryInterface mysqlQuery) {
        this.mysqlQuery = mysqlQuery;
        mysqlQuery.getQuery().append(" WHERE 1=1");
    }


    public MysqlCondition filter(String expression, Object value){
        mysqlQuery.getQuery().append(" AND ");
        mysqlQuery.getQuery().append(expression).append(Util.toMysqlString(value));
        return this;
    }

    public MysqlCondition orFilter(String expression, Object value){
        mysqlQuery.getQuery().append(" OR ");
        mysqlQuery.getQuery().append(expression).append(Util.toMysqlString(value));
        return this;
    }

    public MysqlCondition filter(String expression){
        mysqlQuery.getQuery().append(" AND ");
        mysqlQuery.getQuery().append(expression);
        return this;
    }

    public MysqlCondition orFilter(String expression){
        mysqlQuery.getQuery().append(" OR ");
        mysqlQuery.getQuery().append(expression);
        return this;
    }

    private void basicInOperation(String field, List<Object> values){
        mysqlQuery.getQuery().append(field).append(" in ").append("(");
        boolean firstElement = true;
        for(Object o : values){
            if(!firstElement) mysqlQuery.getQuery().append(",");
            firstElement = false;
            mysqlQuery.getQuery().append(Util.toMysqlString(o));
        }
        mysqlQuery.getQuery().append(")");
    }
    public MysqlCondition orIn(String field, List<Object> values){
        mysqlQuery.getQuery().append(" OR ");
        basicInOperation(field, values);
        return this;
    }
    public MysqlCondition in(String field, List<Object> values){
        mysqlQuery.getQuery().append(" AND ");
        basicInOperation(field, values);
        return this;
    }


    public  MysqlOrder order(String orderKey, String sort){
        if(mysqlOrder == null) mysqlOrder = new MysqlOrder(this);
        return mysqlOrder.order(orderKey,sort);
    }

    public String limit(int limit){
        return limit(limit, 0);
    }

    public String limit(int limit, int offset){
        mysqlQuery.getQuery().append(" limit ").append(limit).append(" offset ").append(offset);
        return mysqlQuery.getQuery().toString();
    }

    public MysqlGroupBy groupBy(String groupByKey){
        if(mysqlGroupBy == null) mysqlGroupBy = new MysqlGroupBy(this);
        return mysqlGroupBy.groupBy(groupByKey);
    }

    public MysqlQuery getQuery() {
        return mysqlQuery.getQuery();
    }
}


