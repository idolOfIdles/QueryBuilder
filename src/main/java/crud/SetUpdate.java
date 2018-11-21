package crud;


import interfaces.CrudSet;
import query.util.Util;

public class SetUpdate{

    private StringBuilder updateBuilder;

    public SetUpdate(StringBuilder insertBuilder) {
        this.updateBuilder = insertBuilder;
    }

    public SetUpdate set(String field, Object value) {
        updateBuilder.append(field).append(" = ").append(Util.toMysqlString(value)).append(",");
        return this;
    }

    public UpdateFilter filter(String expr, Object value) {
        Util.rightStripIfExists(updateBuilder, ',');
        return new UpdateFilter(updateBuilder).filter(expr, value);
    }

    @Override
    public String toString() {
        Util.rightStripIfExists(updateBuilder, ',');
        return updateBuilder.toString();
    }
}
