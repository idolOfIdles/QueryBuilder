package crud;


import interfaces.CrudSet;
import query.util.Util;

public class SetUpdate implements CrudSet {

    private StringBuilder updateBuilder;

    public SetUpdate(StringBuilder insertBuilder) {
        this.updateBuilder = insertBuilder;
    }

    @Override
    public CrudSet set(String field, Object value) {
        updateBuilder.append("field = ").append(Util.toMysqlString(value)).append(",");
        return this;
    }

    @Override
    public String toString() {
        
    }
}
