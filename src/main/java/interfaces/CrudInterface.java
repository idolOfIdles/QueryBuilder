package interfaces;

/**
 * Created by safayat on 11/20/18.
 */

public interface CrudInterface {
    CrudSet set(String field, Object value);
    String values(Object value);
}
