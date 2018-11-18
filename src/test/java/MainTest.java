package test.java;

import main.java.queryBuilder.MysqlCondition;
import main.java.queryBuilder.MysqlGroupBy;
import main.java.queryBuilder.MysqlQuery;
import main.java.queryBuilder.util.Util;

/**
 * Created by safayat on 11/18/18.
 */
public class MainTest {
    public static void main(String[] args){
        System.out.println(MysqlQuery.get()
                .table(Util.tableName(MysqlCondition.class))
                .table(Util.tableName(MysqlGroupBy.class))
                .filter("mcId =", 5)
                .filter("mcId = mgId")
                        .order("date","desc")
                .limit(5,3)
                );
    }
}
