package query;

/**
 * Created by safayat on 10/16/18.
 */

public class MysqlOrder implements MysqlQueryInterface{

    private MysqlQueryInterface mysqlQuery;
    boolean firstOrder = true;
    public MysqlOrder(MysqlQueryInterface mysqlQuery) {
        this.mysqlQuery = mysqlQuery;
    }

    public  MysqlOrder order(String orderKey, String sort){
        if(!firstOrder) mysqlQuery.getQuery().append(", ");
        else mysqlQuery.getQuery().append(" order by ");
        mysqlQuery.getQuery().append(orderKey);
        firstOrder=false;
        if(sort!= null && !sort.isEmpty()) mysqlQuery.getQuery().append(" ").append(sort);
        return this;
    }

    public String limit(int limit){
        return limit(limit, 0);
    }

    public String limit(int limit, int offset){
        mysqlQuery.getQuery().append(" limit ").append(limit).append(" offset ").append(offset);
        return mysqlQuery.getQuery().toString();
    }



    @Override
    public query.MysqlQuery getQuery() {
        return mysqlQuery.getQuery();
    }
}


