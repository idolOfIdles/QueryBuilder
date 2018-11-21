package crud;


import interfaces.CrudSet;
import query.util.Util;

import java.util.ArrayList;
import java.util.List;

public class SetInsert implements CrudSet {

    private StringBuilder insertBuilder;
    private List<String> fields;
    private List<Object> values;

    public SetInsert(StringBuilder insertBuilder) {
        this.insertBuilder = insertBuilder;
        fields = new ArrayList<String>();
        values = new ArrayList<Object>();
    }

    public SetInsert set(String field, Object value) {
        fields.add(field);
        values.add(value);
        return this;
    }

    @Override
    public String toString() {
        insertBuilder.append(" (");
        for(String f : fields){
            insertBuilder.append(f).append(",");
        }
        insertBuilder.setCharAt(insertBuilder.length()-1, ')');
        insertBuilder.append(" values(");
        for(Object o : values){
            insertBuilder.append(Util.toMysqlString(o)).append(",");
        }
        insertBuilder.setCharAt(insertBuilder.length()-1, ')');
        return insertBuilder.toString();
    }
}
