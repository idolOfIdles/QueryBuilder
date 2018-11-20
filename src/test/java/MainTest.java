import crud.*;
import model.Category;
import query.MysqlCondition;
import query.MysqlGroupBy;
import query.MysqlQuery;
import query.util.Util;

/**
 * Created by safayat on 11/18/18.
 */
public class MainTest {
    public static void main(String[] args){
        Crud crud = new Crud();
        System.out.println(crud
                .insert(Util.tableName(Category.class))
                .values(new Category()));

        System.out.println(crud
                .insert(Util.tableName(Category.class))
                .set("id", 5)
                .set("description", "desc")
                .set("status", "published")

        );



    }
}
