package crud;

/**
 * Created by safayat on 11/20/18.
 */


public class Crud {

    public Crud() {

    }

    public InsertQuery insert(String table){
        return new InsertQuery(table);
    }





}
