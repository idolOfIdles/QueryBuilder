package query;

/**
 * Created by safayat on 10/16/18.
 */

public class MysqlGroupBy implements MysqlQueryInterface{

    private MysqlQueryInterface mysqlQuery;
    private MysqlOrder mysqlOrder;

    public MysqlGroupBy(MysqlQueryInterface mysqlQuery) {
        this.mysqlQuery = mysqlQuery;
    }

    public MysqlGroupBy groupBy(String groupByKey){
        mysqlQuery.getQuery().append(" group by ").append(groupByKey);
        return this;
    }

    public String limit(int limit){
        return limit(limit, 0);
    }

    public String limit(int limit, int offset){
        mysqlQuery.getQuery().append(" limit ").append(limit).append(" offset ").append(offset);
        return mysqlQuery.getQuery().toString();
    }


    public  MysqlOrder order(String orderKey, String sort){
        if(mysqlOrder == null) mysqlOrder = new MysqlOrder(this);
        return mysqlOrder.order(orderKey, sort);
    }


    @Override
    public MysqlQuery getQuery() {
        return mysqlQuery.getQuery();
    }
}


