package crud;


import interfaces.CrudSet;
import query.util.Util;

public class UpdateFilter{

    private StringBuilder updateBuilder;

    public UpdateFilter(StringBuilder stringBuilder) {
        this.updateBuilder = stringBuilder;
        stringBuilder.append(" WHERE 1=1");
    }

    public UpdateFilter filter(String expr, Object value){
        updateBuilder.append(" AND ").append(expr).append(Util.toMysqlString(value));
        return this;
    }

    @Override
    public String toString() {
        return updateBuilder.toString();
    }
}
